package org.mybatis.generator.codegen.mybatis3.xmlmapper.elements;


import com.seal.code.config.Config;
import com.seal.code.service.InitService;
import com.seal.code.service.InitServiceImpl;
import com.seal.code.util.StringUtil;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.MyBatis3FormattingUtilities;

import java.util.*;



public class FindPageInfoElementGenerator extends
        AbstractXmlElementGenerator {
    private static final String IS_DELETE = "is_delete";

    public FindPageInfoElementGenerator() {
        super();
    }

    @Override
    public void addElements(XmlElement parentElement) {
        //自定义生成类
        InitService initService = new InitServiceImpl();
        initService.init(introspectedTable);


        XmlElement answer = new XmlElement("select");
        answer.addAttribute(new Attribute("id", "findPageInfo"));
        if (this.introspectedTable.getRules().generateResultMapWithBLOBs()) {
            answer.addAttribute(new Attribute("resultMap", this.introspectedTable.getResultMapWithBLOBsId()));
        } else {
            answer.addAttribute(new Attribute("resultMap", this.introspectedTable.getBaseResultMapId()));
        }
        this.context.getCommentGenerator().addComment(answer);
        FullyQualifiedJavaType parameterType = this.introspectedTable.getRules().calculateAllFieldsClass();
        if (Config.model_flag) {
            parameterType = new FullyQualifiedJavaType(StringUtil.importTran(introspectedTable));
        }
        answer.addAttribute(new Attribute("parameterType", parameterType.getFullyQualifiedName()));
        StringBuilder sb = new StringBuilder();
        sb.append("select ");

        answer.addElement(new TextElement(sb.toString()));
        sb.setLength(0);
        List<IntrospectedColumn> list = introspectedTable.getAllColumns();
        for (int i = 0; i < list.size(); i++) {
            String javaProperty = list.get(i).getActualColumnName();
//            if (StringUtil.containRecord(javaProperty)) {
//                continue;
//            }
            sb.append(javaProperty);
            if (i != list.size() - 1) {
                sb.append(" ,");
            }
        }
        if (sb.toString().endsWith(" ,")) {
            answer.addElement(new TextElement(sb.toString().substring(0, sb.toString().length() - 1)));
        } else {
            answer.addElement(new TextElement(sb.toString()));
        }

        sb.setLength(0);
        sb.append("from ");
        sb.append(this.introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime());
        System.out.print(this.introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime() + "->" + parameterType.getShortName());
        answer.addElement(new TextElement(sb.toString()));
        sb.setLength(0);
        sb.append("where 1=1");
        answer.addElement(new TextElement(sb.toString()));

        List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();//
        Iterator iter = this.introspectedTable.getAllColumns().iterator();
        int fieldNum = 0;
        while (iter.hasNext()) {
            IntrospectedColumn introspectedColumn = (IntrospectedColumn) iter.next();
            if (!introspectedColumn.isIdentity()) {
                fieldNum++;
                String sqlColumn = MyBatis3FormattingUtilities.getEscapedColumnName(introspectedColumn);
                String sqlTypeColumn = MyBatis3FormattingUtilities.getParameterClause(introspectedColumn);
                if (Config.ignore_jdbcType_flag) {
                    sqlTypeColumn = StringUtil.xmlParam(introspectedColumn);
                }
                String sqlTypeName = introspectedColumn.getJdbcTypeName();
                String beanColumn = introspectedColumn.getJavaProperty();
                XmlElement condition = new XmlElement("if");
                if ("id".equals(beanColumn) || "createTime".equals(beanColumn)) {
                    continue;
                }
                if (StringUtil.containRecord(sqlColumn)) {
                    continue;
                }
                Map<String, String> map = new HashMap<String, String>();//
                map.put("field", beanColumn);//
                map.put("remark", introspectedColumn.getRemarks());//
                map.put("sqlTypeName", sqlTypeName);
                mapList.add(map);//
                if ("VARCHAR".equals(sqlTypeName) || "LONGVARCHAR".equals(sqlTypeName)) {
                    sb.setLength(0);
                    sb.append(introspectedColumn.getJavaProperty());
                    sb.append(" != null and ");
                    sb.append(introspectedColumn.getJavaProperty());
                    sb.append(".trim() != ''");
                    condition.addAttribute(new Attribute("test", sb.toString()));
                    sb.setLength(0);
                    sb.append("and ");
                    sb.append(sqlColumn);
                    sb.append(" like concat('%', ");
                    sb.append(sqlTypeColumn);
                    sb.append(", '%')");
                    condition.addElement(new TextElement(sb.toString()));
                    answer.addElement(condition);
                } else {
                    sb.setLength(0);
                    sb.append(introspectedColumn.getJavaProperty());
                    sb.append(" != null");
                    condition.addAttribute(new Attribute("test", sb.toString()));
                    sb.setLength(0);
                    sb.append("and ");
                    sb.append(sqlColumn);
                    sb.append(" = ");
                    sb.append(sqlTypeColumn);
                    condition.addElement(new TextElement(sb.toString()));
                    answer.addElement(condition);
                }
            }
        }
        System.out.println("  " + fieldNum + " 个字段");
//        sb.setLength(0);
//        sb.append("order by id desc");
//        answer.addElement(new TextElement(sb.toString()));
        if (this.context.getPlugins().sqlMapSelectByPrimaryKeyElementGenerated(answer, this.introspectedTable)) {
            parentElement.addElement(answer);
        }

    }

}