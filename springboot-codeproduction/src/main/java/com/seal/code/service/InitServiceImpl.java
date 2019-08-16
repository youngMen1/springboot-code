package com.seal.code.service;

import com.seal.code.config.Config;
import com.seal.code.util.FileUtil;
import com.seal.code.util.StringUtil;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;

public class InitServiceImpl implements InitService {

    private static final String baseTempPath = "src/main/java/com/seal/code/temp";

    @Override
    public void init(IntrospectedTable introspectedTable) {
        createService(introspectedTable);
        createServiceImpl(introspectedTable);

        createBO(introspectedTable);
        createResultBO(introspectedTable);

        createReqParam(introspectedTable);
        createRespParam(introspectedTable);

        createController(introspectedTable);
    }

    private void createController(IntrospectedTable introspectedTable) {
        String Entity = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType()).getShortName();
        String entity = StringUtil.lowerFirstWord(Entity);
        System.out.println(Entity+"---------------->"+entity);
        String basePackageName = Config.modelPackage;
        // 模版路径
        String tempPath = baseTempPath + "/controller.temp";
        String newContent = FileUtil.readFile(tempPath)
                .replaceAll("@basePackage", basePackageName)
                .replaceAll("@Entity", Entity)
                .replaceAll("@entity", entity);
        FileUtil.writeFile(FileUtil.getCodePath() + "/controller/" + Entity + "Controller.java", newContent);
    }

    private void createRespParam(IntrospectedTable introspectedTable) {

    }

    private void createReqParam(IntrospectedTable introspectedTable) {
        //add
    }


    private void createResultBO(IntrospectedTable introspectedTable) {

    }

    private void createBO(IntrospectedTable introspectedTable) {

    }

    private void createServiceImpl(IntrospectedTable introspectedTable) {
        String Entity = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType()).getShortName();
        String entity = StringUtil.lowerFirstWord(Entity);
        String basePackageName = Config.modelPackage;
        // 模版路径
        String tempPath = baseTempPath + "/serviceImpl.temp";
        String newContent = FileUtil.readFile(tempPath)
                .replaceAll("@basePackage", basePackageName)
                .replaceAll("@Entity", Entity)
                .replaceAll("@entity", entity);
        FileUtil.writeFile(FileUtil.getCodePath() + "/service/impl/" + Entity + "Service.java", newContent);

    }

    private void createService(IntrospectedTable introspectedTable) {
        String Entity = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType()).getShortName();
        String entity = StringUtil.lowerFirstWord(Entity);
        String basePackageName = Config.modelPackage;
        // 模版路径
        String tempPath = baseTempPath + "/service.temp";
        String newContent = FileUtil.readFile(tempPath)
                .replaceAll("@basePackage", basePackageName)
                .replaceAll("@Entity", Entity)
                .replaceAll("@entity", entity);
        FileUtil.writeFile(FileUtil.getCodePath() + "/service/" + Entity + "Service.java", newContent);
    }
}
