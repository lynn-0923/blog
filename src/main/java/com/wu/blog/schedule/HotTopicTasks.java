package com.wu.blog.schedule;

import com.wu.blog.cache.HotTopicCache;
import com.wu.blog.domain.Question;
import com.wu.blog.domain.QuestionExample;
import com.wu.blog.mapper.QuestionMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Slf4j
public class HotTopicTasks {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private HotTopicCache hotTopicCache;

    @Scheduled(fixedRate =1000*60*60*3)
//    @Scheduled(cron ="0 0 1 * * * ")
    public void hotTopicSchedule() {
        int offset=0;
        int limit=20;
        log.info("hotTopicSchedule start {}", new Date());
        List<Question> questions=new ArrayList<>();
        Map<String, Integer> priorities =new HashMap<>();
        while (offset == 0 || questions.size() ==limit){
            questions=questionMapper.selectByExampleWithRowbounds(new QuestionExample(),new RowBounds(offset,limit));
            for (Question question:questions
                 ) {
                String[] tags = StringUtils.split(question.getTag(), ",");
                for (String tag:tags
                     ) {
                       Integer priority =priorities.get(tag);
                       if(priority !=null){
                           priorities.put(tag,priority+5+question.getCommentCount());
                       }else {
                           priorities.put(tag,5+question.getCommentCount());
                       }
                }
            }
            offset+=limit;
        }
        hotTopicCache.updateTopics(priorities);
        log.info("hotTopicSchedule end {}", new Date());
    }
}