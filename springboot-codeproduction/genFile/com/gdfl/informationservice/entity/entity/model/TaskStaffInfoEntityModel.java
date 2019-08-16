package com.gdfl.informationservice.entity.entity.model;

import java.util.Date;
import lombok.Data;

@Data
public class TaskStaffInfoEntityModel {
    /**
     * id
     */
    private Integer id;

    /**
     * 任务编号
     */
    private String taskNumber;

    /**
     * 任务类型
     */
    private String taskType;

    /**
     * 员工id
     */
    private String staffId;

    /**
     * 岗位id
     */
    private String postId;

    /**
     * 岗位名称
     */
    private String postName;

    /**
     * 组织id
     */
    private String orgId;

    /**
     * 组织名称
     */
    private String orgName;

    /**
     * 任务完成状态(0.任务未完成(默认)、1.任务完成)
     */
    private String completeStatus;

    /**
     * 创建时间
     */
    private Date createTime;
}