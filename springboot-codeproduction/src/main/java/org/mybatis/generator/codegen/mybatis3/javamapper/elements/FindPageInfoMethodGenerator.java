//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.mybatis.generator.codegen.mybatis3.javamapper.elements;

import com.seal.code.config.Config;
import com.seal.code.util.StringUtil;
import org.mybatis.generator.api.dom.java.*;

import java.util.Set;
import java.util.TreeSet;

public class FindPageInfoMethodGenerator extends AbstractJavaMapperMethodGenerator {

    private boolean isSimple;

    public FindPageInfoMethodGenerator(boolean isSimple) {
        this.isSimple = isSimple;
    }

    @Override
    public void addInterfaceElements(Interface interfaze) {
        Set<FullyQualifiedJavaType> importedTypes = new TreeSet();
        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        FullyQualifiedJavaType returnType1 = this.introspectedTable.getRules().calculateAllFieldsClass();
        if (Config.model_flag) {
            returnType1 = new FullyQualifiedJavaType(StringUtil.importTran(introspectedTable));
        }
        String str = returnType1.getShortName();
        FullyQualifiedJavaType returnType = new FullyQualifiedJavaType("java.util.List<" + str + ">");

        method.setReturnType(returnType);
        importedTypes.add(returnType);
        method.setName("findPageInfo");
        method.addParameter(new Parameter(returnType1, StringUtil.lowerFirstWord(str)));
        this.addMapperAnnotations(interfaze, method);
        this.context.getCommentGenerator().addGeneralMethodComment(method, this.introspectedTable);
        if (this.context.getPlugins().clientSelectByPrimaryKeyMethodGenerated(method, interfaze, this.introspectedTable)) {
            interfaze.addImportedTypes(importedTypes);
            interfaze.addMethod(method);
        }

    }

    public void addMapperAnnotations(Interface interfaze, Method method) {
    }
}
