package org.mybatis.generator.config.xml;

import com.seal.code.config.Config;
import org.mybatis.generator.config.*;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.ObjectFactory;
import org.mybatis.generator.internal.util.StringUtility;
import org.mybatis.generator.internal.util.messages.Messages;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Properties;

public class MyBatisGeneratorConfigurationParser {
    private Properties properties;

    public MyBatisGeneratorConfigurationParser(Properties properties) {
        if (properties == null) {
            this.properties = System.getProperties();
        } else {
            this.properties = properties;
        }

    }

    public Configuration parseConfiguration(Element rootNode) throws XMLParserException {
        Configuration configuration = new Configuration();
        NodeList nodeList = rootNode.getChildNodes();

        for (int i = 0; i < nodeList.getLength(); ++i) {
            Node childNode = nodeList.item(i);
            if (childNode.getNodeType() == 1) {
                if ("properties".equals(childNode.getNodeName())) {
                    this.parseProperties(configuration, childNode);
                } else if ("classPathEntry".equals(childNode.getNodeName())) {
                    this.parseClassPathEntry(configuration, childNode);
                } else if ("context".equals(childNode.getNodeName())) {
                    this.parseContext(configuration, childNode);
                }
            }
        }

        return configuration;
    }

    private void parseProperties(Configuration configuration, Node node) throws XMLParserException {
        Properties attributes = this.parseAttributes(node);
        String resource = attributes.getProperty("resource");
        String url = attributes.getProperty("url");
        if (!StringUtility.stringHasValue(resource) && !StringUtility.stringHasValue(url)) {
            throw new XMLParserException(Messages.getString("RuntimeError.14"));
        } else if (StringUtility.stringHasValue(resource) && StringUtility.stringHasValue(url)) {
            throw new XMLParserException(Messages.getString("RuntimeError.14"));
        } else {
            try {
                URL resourceUrl;
                if (StringUtility.stringHasValue(resource)) {
                    resourceUrl = ObjectFactory.getResource(resource);
                    if (resourceUrl == null) {
                        throw new XMLParserException(Messages.getString("RuntimeError.15", resource));
                    }
                } else {
                    resourceUrl = new URL(url);
                }

                InputStream inputStream = resourceUrl.openConnection().getInputStream();
                this.properties.load(inputStream);
                inputStream.close();
            } catch (IOException var8) {
                if (StringUtility.stringHasValue(resource)) {
                    throw new XMLParserException(Messages.getString("RuntimeError.16", resource));
                } else {
                    throw new XMLParserException(Messages.getString("RuntimeError.17", url));
                }
            }
        }
    }

    private void parseContext(Configuration configuration, Node node) {
        Properties attributes = this.parseAttributes(node);
        String defaultModelType = attributes.getProperty("defaultModelType");
        String targetRuntime = attributes.getProperty("targetRuntime");
        String introspectedColumnImpl = attributes.getProperty("introspectedColumnImpl");
        String id = attributes.getProperty("id");
        ModelType mt = defaultModelType == null ? null : ModelType.getModelType(defaultModelType);
        Context context = new Context(mt);
        context.setId(id);
        if (StringUtility.stringHasValue(introspectedColumnImpl)) {
            context.setIntrospectedColumnImpl(introspectedColumnImpl);
        }

        if (StringUtility.stringHasValue(targetRuntime)) {
            context.setTargetRuntime(targetRuntime);
        }

        configuration.addContext(context);
        NodeList nodeList = node.getChildNodes();

        for (int i = 0; i < nodeList.getLength(); ++i) {
            Node childNode = nodeList.item(i);
            if (childNode.getNodeType() == 1) {
                if ("property".equals(childNode.getNodeName())) {
                    this.parseProperty(context, childNode);
                } else if ("plugin".equals(childNode.getNodeName())) {
                    this.parsePlugin(context, childNode);
                } else if ("commentGenerator".equals(childNode.getNodeName())) {
                    this.parseCommentGenerator(context, childNode);
                } else if ("jdbcConnection".equals(childNode.getNodeName())) {
                    this.parseJdbcConnection(context, childNode);
                } else if ("javaModelGenerator".equals(childNode.getNodeName())) {
                    this.parseJavaModelGenerator(context, childNode);
                } else if ("javaTypeResolver".equals(childNode.getNodeName())) {
                    this.parseJavaTypeResolver(context, childNode);
                } else if ("sqlMapGenerator".equals(childNode.getNodeName())) {
                    this.parseSqlMapGenerator(context, childNode);
                } else if ("javaClientGenerator".equals(childNode.getNodeName())) {
                    this.parseJavaClientGenerator(context, childNode);
                } else if ("table".equals(childNode.getNodeName()) && !Config.tableConfig_flag) {
                    this.parseTable(context, childNode);
                }
            }
        }
        if (Config.tableConfig_flag) {
            this.parseTable(context);
        }
    }

    private void parseTable(Context context) {
        List<Config.TableConfig> tableConfigList = Config.tableConfigList;
        for (Config.TableConfig tableConfig : tableConfigList) {
            TableConfiguration tc = new TableConfiguration(context);
            context.addTableConfiguration(tc);
            tc.setTableName(tableConfig.tableName);
            tc.setDomainObjectName(tableConfig.domainObjectName);
            if (Config.ignore_example_flag) {
                tc.setSelectByExampleStatementEnabled(false);
                tc.setDeleteByExampleStatementEnabled(false);
                tc.setUpdateByExampleStatementEnabled(false);
                tc.setSelectByExampleStatementEnabled(false);
                tc.setCountByExampleStatementEnabled(false);
            }
        }
    }

    private void parseSqlMapGenerator(Context context, Node node) {
        SqlMapGeneratorConfiguration sqlMapGeneratorConfiguration = new SqlMapGeneratorConfiguration();
        context.setSqlMapGeneratorConfiguration(sqlMapGeneratorConfiguration);
        Properties attributes = this.parseAttributes(node);
        String targetPackage = attributes.getProperty("targetPackage");
        String targetProject = attributes.getProperty("targetProject");
        sqlMapGeneratorConfiguration.setTargetPackage(targetPackage);
        sqlMapGeneratorConfiguration.setTargetProject(targetProject);
        NodeList nodeList = node.getChildNodes();

        for (int i = 0; i < nodeList.getLength(); ++i) {
            Node childNode = nodeList.item(i);
            if (childNode.getNodeType() == 1 && "property".equals(childNode.getNodeName())) {
                this.parseProperty(sqlMapGeneratorConfiguration, childNode);
            }
        }

    }

    private void parseTable(Context context, Node node) {
        TableConfiguration tc = new TableConfiguration(context);
        context.addTableConfiguration(tc);
        Properties attributes = this.parseAttributes(node);
        String catalog = attributes.getProperty("catalog");
        String schema = attributes.getProperty("schema");
        String tableName = attributes.getProperty("tableName");
        String domainObjectName = attributes.getProperty("domainObjectName");
        String alias = attributes.getProperty("alias");
        String enableInsert = attributes.getProperty("enableInsert");
        String enableSelectByPrimaryKey = attributes.getProperty("enableSelectByPrimaryKey");
        String enableUpdateByPrimaryKey = attributes.getProperty("enableUpdateByPrimaryKey");
        String enableDeleteByPrimaryKey = attributes.getProperty("enableDeleteByPrimaryKey");
        String enableSelectByExample = attributes.getProperty("enableSelectByExample");
        String enableDeleteByExample = attributes.getProperty("enableDeleteByExample");
        String enableCountByExample = attributes.getProperty("enableCountByExample");
        String enableUpdateByExample = attributes.getProperty("enableUpdateByExample");
        String selectByExampleQueryId = attributes.getProperty("selectByExampleQueryId");
        if (Config.ignore_example_flag) {
            enableSelectByExample = "false";
            enableDeleteByExample = "false";
            enableCountByExample = "false";
            enableUpdateByExample = "false";
            selectByExampleQueryId = "false";
        }
        String selectByPrimaryKeyQueryId = attributes.getProperty("selectByPrimaryKeyQueryId");
        String modelType = attributes.getProperty("modelType");
        String escapeWildcards = attributes.getProperty("escapeWildcards");
        String delimitIdentifiers = attributes.getProperty("delimitIdentifiers");
        String delimitAllColumns = attributes.getProperty("delimitAllColumns");
        if (StringUtility.stringHasValue(catalog)) {
            tc.setCatalog(catalog);
        }

        if (StringUtility.stringHasValue(schema)) {
            tc.setSchema(schema);
        }

        if (StringUtility.stringHasValue(tableName)) {
            tc.setTableName(tableName);
        }

        if (StringUtility.stringHasValue(domainObjectName)) {
            tc.setDomainObjectName(domainObjectName);
        }

        if (StringUtility.stringHasValue(alias)) {
            tc.setAlias(alias);
        }

        if (StringUtility.stringHasValue(enableInsert)) {
            tc.setInsertStatementEnabled(StringUtility.isTrue(enableInsert));
        }

        if (StringUtility.stringHasValue(enableSelectByPrimaryKey)) {
            tc.setSelectByPrimaryKeyStatementEnabled(StringUtility.isTrue(enableSelectByPrimaryKey));
        }

        if (StringUtility.stringHasValue(enableSelectByExample)) {
            tc.setSelectByExampleStatementEnabled(StringUtility.isTrue(enableSelectByExample));
        }

        if (StringUtility.stringHasValue(enableUpdateByPrimaryKey)) {
            tc.setUpdateByPrimaryKeyStatementEnabled(StringUtility.isTrue(enableUpdateByPrimaryKey));
        }

        if (StringUtility.stringHasValue(enableDeleteByPrimaryKey)) {
            tc.setDeleteByPrimaryKeyStatementEnabled(StringUtility.isTrue(enableDeleteByPrimaryKey));
        }

        if (StringUtility.stringHasValue(enableDeleteByExample)) {
            tc.setDeleteByExampleStatementEnabled(StringUtility.isTrue(enableDeleteByExample));
        }

        if (StringUtility.stringHasValue(enableCountByExample)) {
            tc.setCountByExampleStatementEnabled(StringUtility.isTrue(enableCountByExample));
        }

        if (StringUtility.stringHasValue(enableUpdateByExample)) {
            tc.setUpdateByExampleStatementEnabled(StringUtility.isTrue(enableUpdateByExample));
        }

        if (StringUtility.stringHasValue(selectByPrimaryKeyQueryId)) {
            tc.setSelectByPrimaryKeyQueryId(selectByPrimaryKeyQueryId);
        }

        if (StringUtility.stringHasValue(selectByExampleQueryId)) {
            tc.setSelectByExampleQueryId(selectByExampleQueryId);
        }

        if (StringUtility.stringHasValue(modelType)) {
            tc.setConfiguredModelType(modelType);
        }

        if (StringUtility.stringHasValue(escapeWildcards)) {
            tc.setWildcardEscapingEnabled(StringUtility.isTrue(escapeWildcards));
        }

        if (StringUtility.stringHasValue(delimitIdentifiers)) {
            tc.setDelimitIdentifiers(StringUtility.isTrue(delimitIdentifiers));
        }

        if (StringUtility.stringHasValue(delimitAllColumns)) {
            tc.setAllColumnDelimitingEnabled(StringUtility.isTrue(delimitAllColumns));
        }

        NodeList nodeList = node.getChildNodes();

        for (int i = 0; i < nodeList.getLength(); ++i) {
            Node childNode = nodeList.item(i);
            if (childNode.getNodeType() == 1) {
                if ("property".equals(childNode.getNodeName())) {
                    this.parseProperty(tc, childNode);
                } else if ("columnOverride".equals(childNode.getNodeName())) {
                    this.parseColumnOverride(tc, childNode);
                } else if ("ignoreColumn".equals(childNode.getNodeName())) {
                    this.parseIgnoreColumn(tc, childNode);
                } else if ("generatedKey".equals(childNode.getNodeName())) {
                    this.parseGeneratedKey(tc, childNode);
                } else if ("columnRenamingRule".equals(childNode.getNodeName())) {
                    this.parseColumnRenamingRule(tc, childNode);
                }
            }
        }

    }

    private void parseColumnOverride(TableConfiguration tc, Node node) {
        Properties attributes = this.parseAttributes(node);
        String column = attributes.getProperty("column");
        String property = attributes.getProperty("property");
        String javaType = attributes.getProperty("javaType");
        String jdbcType = attributes.getProperty("jdbcType");
        String typeHandler = attributes.getProperty("typeHandler");
        String delimitedColumnName = attributes.getProperty("delimitedColumnName");
        ColumnOverride co = new ColumnOverride(column);
        if (StringUtility.stringHasValue(property)) {
            co.setJavaProperty(property);
        }

        if (StringUtility.stringHasValue(javaType)) {
            co.setJavaType(javaType);
        }

        if (StringUtility.stringHasValue(jdbcType)) {
            co.setJdbcType(jdbcType);
        }

        if (StringUtility.stringHasValue(typeHandler)) {
            co.setTypeHandler(typeHandler);
        }

        if (StringUtility.stringHasValue(delimitedColumnName)) {
            co.setColumnNameDelimited(StringUtility.isTrue(delimitedColumnName));
        }

        NodeList nodeList = node.getChildNodes();

        for (int i = 0; i < nodeList.getLength(); ++i) {
            Node childNode = nodeList.item(i);
            if (childNode.getNodeType() == 1 && "property".equals(childNode.getNodeName())) {
                this.parseProperty(co, childNode);
            }
        }

        tc.addColumnOverride(co);
    }

    private void parseGeneratedKey(TableConfiguration tc, Node node) {
        Properties attributes = this.parseAttributes(node);
        String column = attributes.getProperty("column");
        boolean identity = StringUtility.isTrue(attributes.getProperty("identity"));
        String sqlStatement = attributes.getProperty("sqlStatement");
        String type = attributes.getProperty("type");
        GeneratedKey gk = new GeneratedKey(column, sqlStatement, identity, type);
        tc.setGeneratedKey(gk);
    }

    private void parseIgnoreColumn(TableConfiguration tc, Node node) {
        Properties attributes = this.parseAttributes(node);
        String column = attributes.getProperty("column");
        String delimitedColumnName = attributes.getProperty("delimitedColumnName");
        IgnoredColumn ic = new IgnoredColumn(column);
        if (StringUtility.stringHasValue(delimitedColumnName)) {
            ic.setColumnNameDelimited(StringUtility.isTrue(delimitedColumnName));
        }

        tc.addIgnoredColumn(ic);
    }

    private void parseColumnRenamingRule(TableConfiguration tc, Node node) {
        Properties attributes = this.parseAttributes(node);
        String searchString = attributes.getProperty("searchString");
        String replaceString = attributes.getProperty("replaceString");
        ColumnRenamingRule crr = new ColumnRenamingRule();
        crr.setSearchString(searchString);
        if (StringUtility.stringHasValue(replaceString)) {
            crr.setReplaceString(replaceString);
        }

        tc.setColumnRenamingRule(crr);
    }

    private void parseJavaTypeResolver(Context context, Node node) {
        JavaTypeResolverConfiguration javaTypeResolverConfiguration = new JavaTypeResolverConfiguration();
        context.setJavaTypeResolverConfiguration(javaTypeResolverConfiguration);
        Properties attributes = this.parseAttributes(node);
        String type = attributes.getProperty("type");
        if (StringUtility.stringHasValue(type)) {
            javaTypeResolverConfiguration.setConfigurationType(type);
        }

        NodeList nodeList = node.getChildNodes();

        for (int i = 0; i < nodeList.getLength(); ++i) {
            Node childNode = nodeList.item(i);
            if (childNode.getNodeType() == 1 && "property".equals(childNode.getNodeName())) {
                this.parseProperty(javaTypeResolverConfiguration, childNode);
            }
        }

    }

    private void parsePlugin(Context context, Node node) {
        PluginConfiguration pluginConfiguration = new PluginConfiguration();
        context.addPluginConfiguration(pluginConfiguration);
        Properties attributes = this.parseAttributes(node);
        String type = attributes.getProperty("type");
        pluginConfiguration.setConfigurationType(type);
        NodeList nodeList = node.getChildNodes();

        for (int i = 0; i < nodeList.getLength(); ++i) {
            Node childNode = nodeList.item(i);
            if (childNode.getNodeType() == 1 && "property".equals(childNode.getNodeName())) {
                this.parseProperty(pluginConfiguration, childNode);
            }
        }

    }

    private void parseJavaModelGenerator(Context context, Node node) {
        JavaModelGeneratorConfiguration javaModelGeneratorConfiguration = new JavaModelGeneratorConfiguration();
        context.setJavaModelGeneratorConfiguration(javaModelGeneratorConfiguration);
        Properties attributes = this.parseAttributes(node);
        String targetPackage = attributes.getProperty("targetPackage");
        String targetProject = attributes.getProperty("targetProject");
        javaModelGeneratorConfiguration.setTargetPackage(targetPackage);
        javaModelGeneratorConfiguration.setTargetProject(targetProject);
        NodeList nodeList = node.getChildNodes();

        for (int i = 0; i < nodeList.getLength(); ++i) {
            Node childNode = nodeList.item(i);
            if (childNode.getNodeType() == 1 && "property".equals(childNode.getNodeName())) {
                this.parseProperty(javaModelGeneratorConfiguration, childNode);
            }
        }

    }

    private void parseJavaClientGenerator(Context context, Node node) {
        JavaClientGeneratorConfiguration javaClientGeneratorConfiguration = new JavaClientGeneratorConfiguration();
        context.setJavaClientGeneratorConfiguration(javaClientGeneratorConfiguration);
        Properties attributes = this.parseAttributes(node);
        String type = attributes.getProperty("type");
        String targetPackage = attributes.getProperty("targetPackage");
        String targetProject = attributes.getProperty("targetProject");
        String implementationPackage = attributes.getProperty("implementationPackage");
        javaClientGeneratorConfiguration.setConfigurationType(type);
        javaClientGeneratorConfiguration.setTargetPackage(targetPackage);
        javaClientGeneratorConfiguration.setTargetProject(targetProject);
        javaClientGeneratorConfiguration.setImplementationPackage(implementationPackage);
        NodeList nodeList = node.getChildNodes();

        for (int i = 0; i < nodeList.getLength(); ++i) {
            Node childNode = nodeList.item(i);
            if (childNode.getNodeType() == 1 && "property".equals(childNode.getNodeName())) {
                this.parseProperty(javaClientGeneratorConfiguration, childNode);
            }
        }

    }

    private void parseJdbcConnection(Context context, Node node) {
        JDBCConnectionConfiguration jdbcConnectionConfiguration = new JDBCConnectionConfiguration();
        context.setJdbcConnectionConfiguration(jdbcConnectionConfiguration);
        Properties attributes = this.parseAttributes(node);
        String driverClass = attributes.getProperty("driverClass");
        String connectionURL = attributes.getProperty("connectionURL");
        String userId = attributes.getProperty("userId");
        String password = attributes.getProperty("password");
        jdbcConnectionConfiguration.setDriverClass(driverClass);
        jdbcConnectionConfiguration.setConnectionURL(connectionURL);
        if (StringUtility.stringHasValue(userId)) {
            jdbcConnectionConfiguration.setUserId(userId);
        }

        if (StringUtility.stringHasValue(password)) {
            jdbcConnectionConfiguration.setPassword(password);
        }

        NodeList nodeList = node.getChildNodes();

        for (int i = 0; i < nodeList.getLength(); ++i) {
            Node childNode = nodeList.item(i);
            if (childNode.getNodeType() == 1 && "property".equals(childNode.getNodeName())) {
                this.parseProperty(jdbcConnectionConfiguration, childNode);
            }
        }

    }

    private void parseClassPathEntry(Configuration configuration, Node node) {
        Properties attributes = this.parseAttributes(node);
        configuration.addClasspathEntry(attributes.getProperty("location"));
    }

    private void parseProperty(PropertyHolder propertyHolder, Node node) {
        Properties attributes = this.parseAttributes(node);
        String name = attributes.getProperty("name");
        String value = attributes.getProperty("value");
        propertyHolder.addProperty(name, value);
    }

    private Properties parseAttributes(Node node) {
        Properties attributes = new Properties();
        NamedNodeMap nnm = node.getAttributes();

        for (int i = 0; i < nnm.getLength(); ++i) {
            Node attribute = nnm.item(i);
            String value = this.parsePropertyTokens(attribute.getNodeValue());
            attributes.put(attribute.getNodeName(), value);
        }

        return attributes;
    }

    private String parsePropertyTokens(String string) {
        String OPEN = "${";
        String CLOSE = "}";
        String newString = string;
        if (string != null) {
            int start = string.indexOf("${");

            for (int end = string.indexOf("}"); start > -1 && end > start; end = newString.indexOf("}", end)) {
                String prepend = newString.substring(0, start);
                String append = newString.substring(end + "}".length());
                String propName = newString.substring(start + "${".length(), end);
                String propValue = this.properties.getProperty(propName);
                if (propValue != null) {
                    newString = prepend + propValue + append;
                }

                start = newString.indexOf("${", end);
            }
        }

        return newString;
    }

    private void parseCommentGenerator(Context context, Node node) {
        CommentGeneratorConfiguration commentGeneratorConfiguration = new CommentGeneratorConfiguration();
        context.setCommentGeneratorConfiguration(commentGeneratorConfiguration);
        Properties attributes = this.parseAttributes(node);
        String type = attributes.getProperty("type");
        if (StringUtility.stringHasValue(type)) {
            commentGeneratorConfiguration.setConfigurationType(type);
        }

        NodeList nodeList = node.getChildNodes();

        for (int i = 0; i < nodeList.getLength(); ++i) {
            Node childNode = nodeList.item(i);
            if (childNode.getNodeType() == 1 && "property".equals(childNode.getNodeName())) {
                this.parseProperty(commentGeneratorConfiguration, childNode);
            }
        }

    }
}
