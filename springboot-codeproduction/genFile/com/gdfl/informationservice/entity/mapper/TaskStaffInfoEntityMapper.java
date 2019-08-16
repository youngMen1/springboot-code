package com.gdfl.informationservice.entity.mapper;

import com.gdfl.informationservice.entity.entity.model.TaskStaffInfoEntityModel;

public interface TaskStaffInfoEntityMapper {
    int deleteById(Integer id);

    int insert(TaskStaffInfoEntityModel taskStaffInfoEntityModel);

    TaskStaffInfoEntityModel findById(Integer id);

    int update(TaskStaffInfoEntityModel taskStaffInfoEntityModel);
}