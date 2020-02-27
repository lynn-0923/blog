package com.wu.blog.dto;

import lombok.Data;

@Data
public class HotTopicDTO  implements Comparable{
    private String name;
    private Integer priority;

    @Override
    public int compareTo(Object o) {
        return this.getPriority()-((HotTopicDTO) o).getPriority();
    }
}
