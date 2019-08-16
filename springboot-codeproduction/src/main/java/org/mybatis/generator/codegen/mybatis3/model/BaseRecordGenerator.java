package org.mybatis.generator.codegen.mybatis3.model;

import com.seal.code.config.Config;
import com.seal.code.util.StringUtil;
import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.FullyQualifiedTable;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.Plugin;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.codegen.AbstractJavaGenerator;
import org.mybatis.generator.codegen.RootClassInfo;
import org.mybatis.generator.internal.util.messages.Messages;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BaseRecordGenerator extends AbstractJavaGenerator {

    public BaseRecordGenerator() {
    }

    public static void main(String[] args) {
        String str = "com.utech.cms.Images";
        int i = str.lastIndexOf(".entity");
        System.out.println(str.substring(0, i) + ".model." + str.substring(i + 7, str.length()) + "Model");
    }

    @Override
    public List<CompilationUnit> getCompilationUnits() {
        FullyQualifiedTable table = this.introspectedTable.getFullyQualifiedTable();
        this.progressCallback.startTask(Messages.getString("Progress.8", table.toString()));
        Plugin plugins = this.context.getPlugins();
        CommentGenerator commentGenerator = this.context.getCommentGenerator();

        FullyQualifiedJavaType type = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());
        if (Config.model_flag) {
            type = new FullyQualifiedJavaType(StringUtil.importTran(introspectedTable));
        }
        TopLevelClass topLevelClass = new TopLevelClass(type);
        topLevelClass.setVisibility(JavaVisibility.PUBLIC);
        commentGenerator.addJavaFileComment(topLevelClass);
        if (Config.lombok_flag) {
            topLevelClass.addAnnotation("@Data");
            topLevelClass.addImportedType("lombok.Data");
        }

        FullyQualifiedJavaType superClass = this.getSuperClass();
        if (superClass != null) {
            topLevelClass.setSuperClass(superClass);
            topLevelClass.addImportedType(superClass);
        }

        List<IntrospectedColumn> introspectedColumns = this.getColumnsInThisClass();
        if (this.introspectedTable.isConstructorBased()) {
            this.addParameterizedConstructor(topLevelClass);
            if (!this.introspectedTable.isImmutable()) {
                this.addDefaultConstructor(topLevelClass);
            }
        }

        String rootClass = this.getRootClass();
        Iterator i$ = introspectedColumns.iterator();

        while (i$.hasNext()) {
            IntrospectedColumn introspectedColumn = (IntrospectedColumn) i$.next();
            if (!RootClassInfo.getInstance(rootClass, this.warnings).containsProperty(introspectedColumn)) {
                Field field = this.getJavaBeansField(introspectedColumn);
                if (plugins.modelFieldGenerated(field, topLevelClass, introspectedColumn, this.introspectedTable, Plugin.ModelClassType.BASE_RECORD)) {
                    topLevelClass.addField(field);
                    topLevelClass.addImportedType(field.getType());
                }
                if (!Config.lombok_flag) {
                    Method method = this.getJavaBeansGetter(introspectedColumn);
                    if (plugins.modelGetterMethodGenerated(method, topLevelClass, introspectedColumn, this.introspectedTable, Plugin.ModelClassType.BASE_RECORD)) {
                        topLevelClass.addMethod(method);
                    }

                    if (!this.introspectedTable.isImmutable()) {
                        method = this.getJavaBeansSetter(introspectedColumn);
                        if (plugins.modelSetterMethodGenerated(method, topLevelClass, introspectedColumn, this.introspectedTable, Plugin.ModelClassType.BASE_RECORD)) {
                            topLevelClass.addMethod(method);
                        }
                    }
                }
            }
        }

        List<CompilationUnit> answer = new ArrayList();
        if (this.context.getPlugins().modelBaseRecordClassGenerated(topLevelClass, this.introspectedTable)) {
            answer.add(topLevelClass);
        }

        return answer;
    }

    private FullyQualifiedJavaType getSuperClass() {
        FullyQualifiedJavaType superClass;
        if (this.introspectedTable.getRules().generatePrimaryKeyClass()) {
            superClass = new FullyQualifiedJavaType(this.introspectedTable.getPrimaryKeyType());
        } else {
            String rootClass = this.getRootClass();
            if (rootClass != null) {
                superClass = new FullyQualifiedJavaType(rootClass);
            } else {
                superClass = null;
            }
        }

        return superClass;
    }

    private boolean includePrimaryKeyColumns() {
        return !this.introspectedTable.getRules().generatePrimaryKeyClass() && this.introspectedTable.hasPrimaryKeyColumns();
    }

    private boolean includeBLOBColumns() {
        return !this.introspectedTable.getRules().generateRecordWithBLOBsClass() && this.introspectedTable.hasBLOBColumns();
    }

    private void addParameterizedConstructor(TopLevelClass topLevelClass) {
        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setConstructor(true);
        method.setName(topLevelClass.getType().getShortName());
        this.context.getCommentGenerator().addGeneralMethodComment(method, this.introspectedTable);
        List<IntrospectedColumn> constructorColumns = this.includeBLOBColumns() ? this.introspectedTable.getAllColumns() : this.introspectedTable.getNonBLOBColumns();
        Iterator i$ = constructorColumns.iterator();

        while (i$.hasNext()) {
            IntrospectedColumn introspectedColumn = (IntrospectedColumn) i$.next();
            method.addParameter(new Parameter(introspectedColumn.getFullyQualifiedJavaType(), introspectedColumn.getJavaProperty()));
        }

        StringBuilder sb = new StringBuilder();
        Iterator iterator;
        IntrospectedColumn introspectedColumn;
        if (this.introspectedTable.getRules().generatePrimaryKeyClass()) {
            boolean comma = false;
            sb.append("super(");

            for (iterator = this.introspectedTable.getPrimaryKeyColumns().iterator(); iterator.hasNext(); sb.append(introspectedColumn.getJavaProperty())) {
                introspectedColumn = (IntrospectedColumn) iterator.next();
                if (comma) {
                    sb.append(", ");
                } else {
                    comma = true;
                }
            }

            sb.append(");");
            method.addBodyLine(sb.toString());
        }

        List<IntrospectedColumn> introspectedColumns = this.getColumnsInThisClass();
        iterator = introspectedColumns.iterator();

        while (iterator.hasNext()) {
            introspectedColumn = (IntrospectedColumn) iterator.next();
            sb.setLength(0);
            sb.append("this.");
            sb.append(introspectedColumn.getJavaProperty());
            sb.append(" = ");
            sb.append(introspectedColumn.getJavaProperty());
            sb.append(';');
            method.addBodyLine(sb.toString());
        }

        topLevelClass.addMethod(method);
    }

    private List<IntrospectedColumn> getColumnsInThisClass() {
        List introspectedColumns;
        if (this.includePrimaryKeyColumns()) {
            if (this.includeBLOBColumns()) {
                introspectedColumns = this.introspectedTable.getAllColumns();
            } else {
                introspectedColumns = this.introspectedTable.getNonBLOBColumns();
            }
        } else if (this.includeBLOBColumns()) {
            introspectedColumns = this.introspectedTable.getNonPrimaryKeyColumns();
        } else {
            introspectedColumns = this.introspectedTable.getBaseColumns();
        }

        return introspectedColumns;
    }
}
