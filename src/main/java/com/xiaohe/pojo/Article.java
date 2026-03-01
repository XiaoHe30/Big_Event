package com.xiaohe.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xiaohe.anno.State;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;

@Data
public class Article {

    @NotNull(groups = {Detail.class, Update.class})
    private Integer id;

    @NotEmpty(groups = {Update.class})
    @Pattern(regexp = "^\\S{1,10}$")
    private String title;

    @NotEmpty(groups = {Update.class})
    private String content;

    @NotEmpty(groups = {Update.class})
    @URL
    private String coverImg;

    @State(groups = {Update.class})
    private String state;

    @NotNull(groups = {Update.class})
    private Integer categoryId;

    @JsonIgnore
    private Integer createUser;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    public interface Detail{}

    public interface Update{}
}
