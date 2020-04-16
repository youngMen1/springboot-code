package com.seal.rabbitmq.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Mail {

    /**
     * 目标邮箱
     */
    @Pattern(regexp = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$", message = "邮箱格式不正确")
    private String to;

    /**
     * 邮件标题
     */
    @NotBlank(message = "标题不能为空")
    private String title;

    /**
     * 邮件正文
     */
    @NotBlank(message = "正文不能为空")
    private String content;

    /**
     * 消息id
     */
    private String msgId;

}
