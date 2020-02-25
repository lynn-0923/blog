package com.wu.blog.service;

import com.wu.blog.domain.Question;
import com.wu.blog.domain.QuestionExample;
import com.wu.blog.domain.User;
import com.wu.blog.dto.PageDTO;
import com.wu.blog.dto.QuestionDTO;
import com.wu.blog.exception.CustomizeErrorCode;
import com.wu.blog.exception.CustomizeException;
import com.wu.blog.mapper.QuestionExtMapper;
import com.wu.blog.mapper.QuestionMapper;
import com.wu.blog.mapper.UserMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionExtMapper questionExtMapper;

    @Autowired
    private QuestionMapper questionMapper;
    public PageDTO list(Integer page, Integer size) {
        PageDTO pageDTO=new PageDTO();
        Integer totalPages;
        QuestionExample questionExample = new QuestionExample();
        Integer totalCount =(int) questionMapper.countByExample(questionExample);
        //计算总页数
        totalPages=totalCount % size ==0?(totalCount / size):(totalCount / size +1);
        if(page < 1){
            page=1;
        }
        if(page>totalPages){
            page=totalPages;
        }
        pageDTO.setPagination(totalPages,page);
        //size*(page-1)
        Integer offset=size*(page-1);
        List<QuestionDTO> questionDTOS=new ArrayList<>();
        QuestionExample example = new QuestionExample();
        example.setOrderByClause("gmt_create desc");
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(example, new RowBounds(offset, size));
        for (Question question:questions
             ) {
          User user=  userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }
        pageDTO.setQuestions(questionDTOS);
        return pageDTO;
    }

    public PageDTO listByUid(Long uid, Integer page, Integer size) {
        PageDTO pageDTO=new PageDTO();
        Integer totalPages;
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria()
                .andCreatorEqualTo(uid);
        Integer totalCount =(int) questionMapper.countByExample(questionExample);
        //计算总页数
        totalPages=totalCount % size ==0?(totalCount / size):(totalCount / size +1);
        if(page < 1){
            page=1;
        }
        if(page>totalPages){
            page=totalPages;
        }
        pageDTO.setPagination(totalPages,page);
        //size*(page-1)
        Integer offset=size*(page-1);
        List<QuestionDTO> questionDTOS=new ArrayList<>();
        QuestionExample example = new QuestionExample();
        example.createCriteria()
                .andCreatorEqualTo(uid);
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(example, new RowBounds(offset, size));
        for (Question question:questions
        ) {
            User user=  userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }
        pageDTO.setQuestions(questionDTOS);
        return pageDTO;
    }

    public QuestionDTO getById(Long id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        if(question == null){
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        QuestionDTO questionDTO=new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        User user=  userMapper.selectByPrimaryKey(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void createOrUpdate(Question question) {
        if(question.getId() == null){
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            question.setCommentCount(0);
            question.setPraiseCount(0);
            question.setViewCount(0);
            questionMapper.insert(question);
        }else{
            Question updateQuestion = new Question();
            updateQuestion.setGmtModified(System.currentTimeMillis());
            updateQuestion.setTitle(question.getTitle());
            updateQuestion.setDescription(question.getDescription());
            updateQuestion.setTag(question.getTag());
            QuestionExample questionExample = new QuestionExample();
            questionExample.createCriteria()
                    .andIdEqualTo(question.getId());
            int updated=questionMapper.updateByExampleSelective(updateQuestion,questionExample);
            if(updated !=1){
                throw  new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
        }
    }

    public void incView(Long id) {
        Question question = new Question();
        question.setId(id);
        question.setViewCount(1);
        questionExtMapper.incView(question);
    }

    public List<QuestionDTO> selectedRelated(QuestionDTO queryDTO) {
        if(StringUtils.isBlank(queryDTO.getTag())){
            return new ArrayList<>();
        }
        String[] tags = StringUtils.split(queryDTO.getTag(), ",");
        String regexTag = Arrays.stream(tags).collect(Collectors.joining("|"));
        Question question=new Question();
        question.setId(queryDTO.getId());
        question.setTag(regexTag);
//        String tags = StringUtils.replace(questionDTO.getTag(), ",", "|");
        List<Question> questions = questionExtMapper.selectRelated(question);
        List<QuestionDTO> questionDTOS = questions.stream().map(q -> {
            QuestionDTO questionDTO= new QuestionDTO();
            BeanUtils.copyProperties(q,questionDTO);
            return questionDTO;
        }).collect(Collectors.toList());
        return questionDTOS;
    }
}
