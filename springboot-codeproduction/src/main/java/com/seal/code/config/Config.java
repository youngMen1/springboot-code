package com.seal.code.config;

import com.seal.code.util.StringUtil;
import sun.nio.cs.Surrogate;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * 获取全局配置
 */
public class Config {
    /**
     * lombok：true，false
     */
    public final static boolean lombok_flag = true;

    /**
     * 不要Example
     */
    public final static boolean ignore_example_flag = true;

    /**
     * 不要没有判断条件的insert和update
     */
    public final static boolean ignore_condition_flag = true;

    /**
     * utech特殊包，不用entity，换model
     */
    public final static boolean model_flag = true;

    /**
     * u-tech mapper的名称格式
     */
    public final static boolean mapper_method_name_flag = true;

    /**
     * 包含分页查询
     */
    public final static boolean include_findPageInfo_flag = false;

    /**
     * 包含通过id查对象
     */
    public final static boolean include_findById_flag = true;

    /**
     * 包含真实删除（非逻辑删除）
     */
    public final static boolean include_delete_flag = false;

    /**
     * utech,忽略XML文件中的jdbcType
     */
    public final static boolean ignore_jdbcType_flag = true;

    /**
     * table的配置用properties，而不是xml
     */
    public final static boolean tableConfig_flag = true;

    public static String filePath = "genFile";
    public static String modelPackage;
    public static List<TableConfig> tableConfigList;

    static {
        // 读取配置文件
        InputStream inputStream = Surrogate.Generator.class.getResourceAsStream("/application.properties");
        try {
            ResourceBundle resource = new PropertyResourceBundle(inputStream);
            String filePathStr = resource.getString("file.path");
            if (!StringUtil.isBlank(filePathStr)) {
                filePath = filePathStr;
            }
            String modelPackageStr = resource.getString("model.package");
            if (!StringUtil.isBlank(modelPackageStr)) {
                modelPackage = modelPackageStr;
            } else {
                System.out.println("初始化项目的根目录model.package");
            }
            //初始化表->实体
            Enumeration<String> keys = resource.getKeys();
            tableConfigList = new ArrayList<TableConfig>();
            while (keys.hasMoreElements()) {
                String tableName = keys.nextElement();
                String domainName = resource.getString(tableName);
                if (!tableName.matches("table\\..+")) {
                    continue;
                }
                if (StringUtil.isBlank(domainName)) {
                    continue;
                }
                TableConfig tableConfig = new TableConfig();
                tableConfig.tableName = tableName.substring(tableName.indexOf(".") + 1);
                tableConfig.domainObjectName = domainName;
                tableConfigList.add(tableConfig);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class TableConfig {
        public String tableName;
        public String domainObjectName;
    }
}
