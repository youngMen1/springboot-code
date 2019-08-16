package org.mybatis.generator.codegen.mybatis3.javamapper.elements;

import com.seal.code.config.Config;
import com.seal.code.util.StringUtil;
import org.mybatis.generator.api.dom.java.*;

import java.util.Set;
import java.util.TreeSet;

public class InsertSelectiveMethodGenerator extends
        AbstractJavaMapperMethodGenerator {

    public InsertSelectiveMethodGenerator() {
        super();
    }

    @Override
    public void addInterfaceElements(Interface interfaze) {
        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
        Method method = new Method();

        method.setReturnType(FullyQualifiedJavaType.getIntInstance());
        method.setVisibility(JavaVisibility.PUBLIC);
        String methodName = introspectedTable.getInsertSelectiveStatementId();
        if (Config.mapper_method_name_flag) {
            methodName = "insert";
        }
        method.setName(methodName);

        FullyQualifiedJavaType parameterType = introspectedTable.getRules()
                .calculateAllFieldsClass();
        if (Config.model_flag) {
            parameterType = new FullyQualifiedJavaType(StringUtil.importTran(introspectedTable));
        }
        importedTypes.add(parameterType);
        // $NON-NLS-1$
        method.addParameter(new Parameter(parameterType, StringUtil.lowerFirstWord(parameterType.getShortName())));

        context.getCommentGenerator().addGeneralMethodComment(method,
                introspectedTable);

        addMapperAnnotations(interfaze, method);

        if (context.getPlugins().clientInsertSelectiveMethodGenerated(
                method, interfaze, introspectedTable)) {
            interfaze.addImportedTypes(importedTypes);
            interfaze.addMethod(method);
        }
    }

    public void addMapperAnnotations(Interface interfaze, Method method) {
        return;
    }
}
