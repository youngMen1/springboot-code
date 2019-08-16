package org.mybatis.generator.codegen.mybatis3.javamapper.elements;

import com.seal.code.config.Config;
import com.seal.code.util.StringUtil;
import org.mybatis.generator.api.dom.java.*;

import java.util.Set;
import java.util.TreeSet;

public class UpdateByPrimaryKeySelectiveMethodGenerator extends
        AbstractJavaMapperMethodGenerator {

    public UpdateByPrimaryKeySelectiveMethodGenerator() {
        super();
    }

    @Override
    public void addInterfaceElements(Interface interfaze) {
        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
        FullyQualifiedJavaType parameterType;

        if (introspectedTable.getRules().generateRecordWithBLOBsClass()) {
            parameterType = new FullyQualifiedJavaType(introspectedTable
                    .getRecordWithBLOBsType());
        } else {
            parameterType = new FullyQualifiedJavaType(introspectedTable
                    .getBaseRecordType());
        }
        if (Config.model_flag) {
            parameterType = new FullyQualifiedJavaType(StringUtil.importTran(introspectedTable));
        }
        importedTypes.add(parameterType);

        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setReturnType(FullyQualifiedJavaType.getIntInstance());
        String methodName = introspectedTable.getUpdateByPrimaryKeySelectiveStatementId();
        if (Config.mapper_method_name_flag) {
            methodName = "update";
        }
        method.setName(methodName);
        method.addParameter(new Parameter(parameterType, StringUtil.lowerFirstWord(parameterType.getShortName()))); //$NON-NLS-1$

        context.getCommentGenerator().addGeneralMethodComment(method,
                introspectedTable);

        addMapperAnnotations(interfaze, method);

        if (context.getPlugins()
                .clientUpdateByPrimaryKeySelectiveMethodGenerated(method,
                        interfaze, introspectedTable)) {
            interfaze.addImportedTypes(importedTypes);
            interfaze.addMethod(method);
        }
    }

    public void addMapperAnnotations(Interface interfaze, Method method) {
        return;
    }
}
