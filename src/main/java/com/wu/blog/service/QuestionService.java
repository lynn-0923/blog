package com.wu.blog.service;

import com.wu.blog.domain.Question;
import com.wu.blog.domain.User;
import com.wu.blog.dto.PageDTO;
import com.wu.blog.dto.QuestionDTO;
import com.wu.blog.mapper.QuestionMapper;
import com.wu.blog.mapper.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.NullLiteral;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;
    public PageDTO list(Integer page, Integer size) {
        PageDTO pageDTO=new PageDTO();
        Integer totalPages;
        Integer totalCount = questionMapper.count();
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
        List<Question> questions = questionMapper.list(offset,size);
        for (Question question:questions
             ) {
          User user=  userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }
        pageDTO.setQuestions(questionDTOS);
        return pageDTO;
    }

    public PageDTO listByUid(Integer uid, Integer page, Integer size) {
        PageDTO pageDTO=new PageDTO();
        Integer totalPages;
        Integer totalCount = questionMapper.countByUid(uid);
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
        List<Question> questions = questionMapper.listByUid(uid,offset,size);
        for (Question question:questions
        ) {
            User user=  userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }
        pageDTO.setQuestions(questionDTOS);
        return pageDTO;
    }

    public QuestionDTO getById(Integer id) {
        Question question = questionMapper.getById(id);
        QuestionDTO questionDTO=new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        User user=  userMapper.findById(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void createOrUpdate(Question question) {
        if(question.getId() == null){
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.create(question);
        }else{
            questionMapper.update(question);
        }
    }
}
