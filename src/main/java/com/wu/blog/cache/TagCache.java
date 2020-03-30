package com.wu.blog.cache;

import com.wu.blog.dto.TagDTO;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TagCache {
    public static List<TagDTO> get() {
        List<TagDTO> tagDTOS = new ArrayList<>();
        TagDTO program = new TagDTO();
        program.setCategoryName("developmentLanguage");
        program.setTags(Arrays.asList("java", "javascript", "php", "css", "html", "algorithms", "design pattern", "html5", "node.js", "python", "c++", "c", "golang", "objective-c", "typescript", "shell", "swift", "c#", "sass", "ruby", "bash", "less", "asp.net", "lua", "scala", "coffeescript", "actionscript", "rust", "erlang", "perl"));
        tagDTOS.add(program);

        TagDTO framework = new TagDTO();
        framework.setCategoryName("platformFramework");
        framework.setTags(Arrays.asList("springboot", "spring", "springcloud", "springmvc", "javaweb", "springData", "mybatis", "mybatis-plus", "shiro", "hibernate", "struts", "jpa"));
        tagDTOS.add(framework);


        TagDTO server = new TagDTO();
        server.setCategoryName("theServer");
        server.setTags(Arrays.asList("linux", "nginx", "docker", "apache", "ubuntu", "centos", "tomcat", "ngnix", "unix", "dubbo", "hadoop", "windows-server"));
        tagDTOS.add(server);

        TagDTO db = new TagDTO();
        db.setCategoryName("database");
        db.setTags(Arrays.asList("mysql", "redis", "mongodb", "sql", "oracle", "nosql memcached", "sqlserver", "postgresql", "sqlite"));
        tagDTOS.add(db);

        TagDTO tool = new TagDTO();
        tool.setCategoryName("developmentTools");
        tool.setTags(Arrays.asList("git", "github","剑指offer","leetcode", "svn", "vim", "maven", "idea", "eclipse", "visual-studio", "atom emacs", "textmate", "hg"));
        tagDTOS.add(tool);
        return tagDTOS;
    }

    public static String filterInvalid(String tags) {
        String[] split = StringUtils.split(tags, ",");
        List<TagDTO> tagDTOS = get();

        List<String> tagList = tagDTOS.stream().flatMap(tag -> tag.getTags().stream()).collect(Collectors.toList());
        String invalid = Arrays.stream(split).filter(t -> StringUtils.isBlank(t) || !tagList.contains(t)).collect(Collectors.joining(","));
        return invalid;
    }
}
