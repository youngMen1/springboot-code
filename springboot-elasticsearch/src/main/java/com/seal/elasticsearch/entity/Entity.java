package com.seal.elasticsearch.entity;

import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2018/10/15 21:22
 * @description TODO
 **/
@Document(indexName="index_entity", type="tstype")
public class Entity implements Serializable {

    private static final long serialVersionUID = -763638353551774166L;

    public static final String INDEX_NAME = "index_entity";

    public static final String TYPE = "tstype";

    private Long id;

    private String name;

    public Entity() {
        super();
    }

    public Entity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}

