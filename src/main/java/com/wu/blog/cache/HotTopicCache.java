package com.wu.blog.cache;

import com.wu.blog.dto.HotTopicDTO;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Data
public class HotTopicCache {
    private List<String>  hots=new ArrayList<>();
    public void updateTopics( Map<String,Integer> topics){
        int max=8;
        PriorityQueue<HotTopicDTO> priorityQueue=new PriorityQueue<>(max);
        topics.forEach((name,priority)->{
            HotTopicDTO hotTopicDTO = new HotTopicDTO();
            hotTopicDTO.setName(name);
            hotTopicDTO.setPriority(priority);
            if(priorityQueue.size() < max){
                priorityQueue.add(hotTopicDTO);
            }else {
                HotTopicDTO minHot=priorityQueue.peek();
                if(hotTopicDTO.compareTo(minHot) > 0){
                    priorityQueue.poll();
                    priorityQueue.add(hotTopicDTO);
                }
            }
        });
        List<String> sortedTopics=new ArrayList<>();
        HotTopicDTO poll = priorityQueue.poll();
        while (poll != null){
            sortedTopics.add(0,poll.getName());
            poll=priorityQueue.poll();
        }
      hots=sortedTopics;
    }
}
