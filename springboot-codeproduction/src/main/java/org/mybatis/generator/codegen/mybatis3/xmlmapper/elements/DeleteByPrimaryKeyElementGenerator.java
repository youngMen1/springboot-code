/*
 *  Copyright 2009 The Apache Software Foundation
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.mybatis.generator.codegen.mybatis3.xmlmapper.elements;

import com.seal.code.config.Config;
import com.seal.code.util.StringUtil;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.MyBatis3FormattingUtilities;


public class DeleteByPrimaryKeyElementGenerator extends
        AbstractXmlElementGenerator {

    private boolean isSimple;

    public DeleteByPrimaryKeyElementGenerator(boolean isSimple) {
        super();
        this.isSimple = isSimple;
    }

    @Override
    public void addElements(XmlElement parentElement) {
        // $NON-NLS-1$
        XmlElement answer = new XmlElement("update");
        String methodName = introspectedTable.getDeleteByPrimaryKeyStatementId();
        if (Config.mapper_method_name_flag) {
            methodName = "deleteById";
        }
        answer.addAttribute(new Attribute(
                "id", methodName)); //$NON-NLS-1$
        String parameterClass;
        if (!isSimple && introspectedTable.getRules().generatePrimaryKeyClass()) {
            parameterClass = introspectedTable.getPrimaryKeyType();
        } else {
            // PK fields are in the base class. If more than on PK
            // field, then they are coming in a map.
            if (introspectedTable.getPrimaryKeyColumns().size() > 1) {
                parameterClass = "map"; //$NON-NLS-1$
            } else {
                parameterClass = introspectedTable.getPrimaryKeyColumns()
                        .get(0).getFullyQualifiedJavaType().toString();
            }
        }
        // $NON-NLS-1$
        answer.addAttribute(new Attribute("parameterType",
                parameterClass));

        context.getCommentGenerator().addComment(answer);

        StringBuilder sb = new StringBuilder();
        // $NON-NLS-1$
        sb.append("update ");
        sb.append(introspectedTable.getFullyQualifiedTableNameAtRuntime());
        answer.addElement(new TextElement(sb.toString()));
        sb.setLength(0);
        // $NON-NLS-1$
        sb.append("record_state = 0");
        answer.addElement(new TextElement(sb.toString()));
        boolean and = false;
        for (IntrospectedColumn introspectedColumn : introspectedTable
                .getPrimaryKeyColumns()) {
            sb.setLength(0);
            if (and) {
                // $NON-NLS-1$
                sb.append("  and ");
            } else {
                // $NON-NLS-1$
                sb.append("where ");
                and = true;
            }

            sb.append(MyBatis3FormattingUtilities
                    .getEscapedColumnName(introspectedColumn));
            sb.append(" = "); //$NON-NLS-1$
            String param = MyBatis3FormattingUtilities
                    .getParameterClause(introspectedColumn);
            if (Config.ignore_jdbcType_flag) {
                param = StringUtil.xmlParam(introspectedColumn);
            }
            sb.append(param);
            answer.addElement(new TextElement(sb.toString()));
        }

        if (context.getPlugins()
                .sqlMapDeleteByPrimaryKeyElementGenerated(answer,
                        introspectedTable)) {
            parentElement.addElement(answer);
        }
    }
}
