package com.seal.code.service;

import org.mybatis.generator.api.IntrospectedTable;

public interface InitService {

    /**
     *
     * @param introspectedTable
     */
    void init(IntrospectedTable introspectedTable);
}
