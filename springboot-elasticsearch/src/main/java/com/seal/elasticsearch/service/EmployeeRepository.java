package com.seal.elasticsearch.service;

import com.seal.elasticsearch.entity.Employee;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface EmployeeRepository extends ElasticsearchRepository<Employee,String> {

    /**
     * 查询雇员信息
     * @param id
     * @return
     */
    Employee queryEmployeeById(String id);
}
