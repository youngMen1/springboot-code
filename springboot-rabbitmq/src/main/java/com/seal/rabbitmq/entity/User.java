package com.seal.rabbitmq.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    private Integer id;
    private String username;
    private String password;

}
