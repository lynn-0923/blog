package com.wu.blog.service;

import com.wu.blog.domain.*;
import com.wu.blog.dto.NotificationDTO;
import com.wu.blog.dto.PageDTO;
import com.wu.blog.dto.QuestionDTO;
import com.wu.blog.enums.NotificationStatusEnum;
import com.wu.blog.enums.NotificationTypeEnum;
import com.wu.blog.exception.CustomizeErrorCode;
import com.wu.blog.exception.CustomizeException;
import com.wu.blog.mapper.NotificationMapper;
import com.wu.blog.mapper.UserMapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class NotificationService {
    @Autowired
    private NotificationMapper notificationMapper;

    public PageDTO list(Long uid, Integer page, Integer size) {
        PageDTO<NotificationDTO> pageDTO=new PageDTO<>();
        Integer totalPages;
        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria()
                .andReceiverEqualTo(uid);
        Integer totalCount =(int) notificationMapper.countByExample(notificationExample);
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
        NotificationExample example = new NotificationExample();
        example.createCriteria()
                .andReceiverEqualTo(uid);
        example.setOrderByClause("gmt_create desc");
        List<Notification> notifications = notificationMapper.selectByExampleWithRowbounds(example, new RowBounds(offset, size));
        if(notifications.size() == 0){
            return pageDTO;
        }
        List<NotificationDTO> notificationDTOS=new ArrayList<>();
        for (Notification notification:notifications
             ) {
            NotificationDTO notificationDTO = new NotificationDTO();
            BeanUtils.copyProperties(notification, notificationDTO);
            notificationDTO.setTypeName(NotificationTypeEnum.nameOfType(notification.getType()));
            notificationDTOS.add(notificationDTO);
        }
        pageDTO.setData(notificationDTOS);
        return pageDTO;
    }

    public Long unreadCount(Long userId) {
        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria()
                .andReceiverEqualTo(userId)
                .andStatusEqualTo(NotificationStatusEnum.UNREAD.getStatus());
        return notificationMapper.countByExample(notificationExample);
    }

    public NotificationDTO read(Long id, User user) {
        Notification notification = notificationMapper.selectByPrimaryKey(id);
        if(notification ==null){
             throw new CustomizeException(CustomizeErrorCode.NOTIFICATION_NOT_FOUND);
        } if(notification.getReceiver() !=user.getId()){
             throw new CustomizeException(CustomizeErrorCode.READ_NOTIFICATION_FAILURE);
        }
        notification.setStatus(NotificationStatusEnum.READ.getStatus());
        notificationMapper.updateByPrimaryKey(notification);
        NotificationDTO notificationDTO = new NotificationDTO();
        BeanUtils.copyProperties(notification, notificationDTO);
        notificationDTO.setTypeName(NotificationTypeEnum.nameOfType(notification.getType()));
      return notificationDTO;
    }
}
