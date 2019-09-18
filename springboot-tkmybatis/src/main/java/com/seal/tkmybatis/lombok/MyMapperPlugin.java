package com.seal.tkmybatis.lombok;

import org.mybatis.generator.api.GeneratedXmlFile;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import tk.mybatis.mapper.generator.MapperPlugin;

import java.lang.reflect.Field;


/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/8/13 13:54
 * @description 整合Lombok
 **/
public class MyMapperPlugin extends MapperPlugin {

    private FullyQualifiedJavaType dataAnnotation;
    private FullyQualifiedJavaType builderAnnotation;
    private FullyQualifiedJavaType noArgsConstructorAnnotation;
    private FullyQualifiedJavaType allArgsConstructorAnnotation;

    public MyMapperPlugin() {
        dataAnnotation = new FullyQualifiedJavaType("lombok.Data");
        builderAnnotation = new FullyQualifiedJavaType("lombok.Builder");
        noArgsConstructorAnnotation = new FullyQualifiedJavaType("lombok.NoArgsConstructor");
        allArgsConstructorAnnotation = new FullyQualifiedJavaType("lombok.AllArgsConstructor");
    }

    /**
     * Adds the @Data lombok import and annotation to the class
     *
     * @param topLevelClass
     */
    protected void addDataAnnotation(TopLevelClass topLevelClass) {
        topLevelClass.addImportedType(dataAnnotation);
        topLevelClass.addImportedType(builderAnnotation);
        topLevelClass.addImportedType(noArgsConstructorAnnotation);
        topLevelClass.addImportedType(allArgsConstructorAnnotation);
        topLevelClass.addAnnotation("@Data");
        topLevelClass.addAnnotation("@Builder");
        topLevelClass.addAnnotation("@NoArgsConstructor");
        topLevelClass.addAnnotation("@AllArgsConstructor");
    }


    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        addDataAnnotation(topLevelClass);
        super.modelBaseRecordClassGenerated(topLevelClass, introspectedTable);
        return true;
    }

    @Override
    public boolean modelPrimaryKeyClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        addDataAnnotation(topLevelClass);

        super.modelPrimaryKeyClassGenerated(topLevelClass, introspectedTable);
        return true;
    }

    @Override
    public boolean modelRecordWithBLOBsClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        addDataAnnotation(topLevelClass);
        super.modelRecordWithBLOBsClassGenerated(topLevelClass, introspectedTable);
        return false;
    }

    @Override
    public boolean modelGetterMethodGenerated(Method method,
                                              TopLevelClass topLevelClass,
                                              IntrospectedColumn introspectedColumn,
                                              IntrospectedTable introspectedTable,
                                              ModelClassType modelClassType) {
        return false;
    }

    @Override
    public boolean modelSetterMethodGenerated(Method method,
                                              TopLevelClass topLevelClass,
                                              IntrospectedColumn introspectedColumn,
                                              IntrospectedTable introspectedTable,
                                              ModelClassType modelClassType) {
        return false;
    }

    @Override
    public boolean sqlMapGenerated(GeneratedXmlFile sqlMap, IntrospectedTable introspectedTable) {
        try {
            Field field = sqlMap.getClass().getDeclaredField("isMergeable");
            field.setAccessible(true);
            field.setBoolean(sqlMap, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
