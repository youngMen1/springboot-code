package com.seal.log.model;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "seal_user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "t_id")
    private int id ;

    @Column(name = "t_name")
    private String name ;

    @Column(name = "t_age")
    private int age ;

    @Column(name = "t_address")
    private String address ;

    @Column(name = "t_pwd")
    private String password ;
}
