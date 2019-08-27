package com.seal.code.util;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/6/24 12:57
 * @description 字符串工具类
 **/
public class StringUtil {

    public static boolean isBlank(String str) {
        if (str == null || "".equals(str)) {
            return true;
        }
        return false;
    }

    /**
     * 导入实体 转换包
     *
     * @return
     */
    public static String importTran(IntrospectedTable introspectedTable) {
        String packages = introspectedTable.getBaseRecordType();
        return packages;
        // return importTran(packages);
    }

    /**
     * utech
     * 导入实体 转换包
     *
     * @return
     */
    public static String importTran(String packages) {
        int i = packages.lastIndexOf(".entity");
        StringBuffer sb = new StringBuffer();
        sb.append(packages.substring(0, i));
        sb.append(".entity.model.");
        sb.append(packages.substring(i + 8, packages.length()));
        sb.append("Model");
//        String modelPath = packages.substring(0, i) + ".entity.model." + packages.substring(i + 8, packages.length()) + "Model";
        System.out.println("----------------导入实体-------------" + sb.toString());
        return sb.toString();
    }

    /**
     * 首个单词小写
     *
     * @return
     */
    public static String lowerFirstWord(String str) {
        StringBuffer sb = new StringBuffer();
        sb.append(str.substring(0, 1).toLowerCase());
        sb.append(str.substring(1));
//        String result = str.substring(0, 1).toLowerCase() + str.substring(1);
        return sb.toString();
    }

    /**
     * 转化为Xml的参数
     *
     * @return
     */
    public static String xmlParam(IntrospectedColumn introspectedColumn) {
        String javaProperty = introspectedColumn.getJavaProperty();
        StringBuffer sb = new StringBuffer();
        sb.append("#{");
        sb.append(javaProperty);
        sb.append("}");
        return sb.toString();
    }


    /**
     * 包含记录信息
     *
     * @return
     */
    public static boolean containRecord(String field) {
        if ("create_datetime".equals(field) || "update_datetime".equals(field) ||
                "create_by".equals(field) || "update_by".equals(field) ||
                "record_state".equals(field)) {
            return true;
        }
        return false;
    }
}
