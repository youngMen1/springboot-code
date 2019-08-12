package com.seal.elasticsearch.service;

import com.seal.elasticsearch.entity.Employee;
import com.seal.elasticsearch.entity.Entity;

public interface CityESService {

    Long saveEntity(Entity entity);

    String saveEmployee(Employee employee);
}
