package com.xiaohe.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {

    @NotNull
    private Integer id;

    private String username;
    @JsonIgnore//让SpringMVC在将当前对象转成json时，忽略该变量，
    private String password;

    @NotEmpty
    @Pattern(regexp = "^\\S{1,10}$")
    private String nickname;

    @NotEmpty
    @Email
    private String email;

    private String userPic;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

}
