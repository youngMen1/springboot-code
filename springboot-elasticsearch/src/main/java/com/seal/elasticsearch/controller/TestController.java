package com.seal.elasticsearch.controller;

import com.google.gson.Gson;
import com.seal.elasticsearch.entity.Employee;
import com.seal.elasticsearch.entity.Entity;
import com.seal.elasticsearch.entity.Region;
import com.seal.elasticsearch.service.CityESService;
import com.seal.elasticsearch.service.EmployeeRepository;
import com.seal.elasticsearch.service.EntityRepository;
import com.seal.elasticsearch.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2018/10/16 10:21
 * @description ES测试
 **/
@RestController
@RequestMapping("/es/")
public class TestController {

    /**
     * 业务接口
     */
    @Autowired
    private CityESService cityESService;

    /**
     * entity实体es
     */
    @Autowired
    private EntityRepository entityRepository;

    /**
     * Employee雇员实体es
     */
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private RegionService regionService;


    /**
     * 添加
     * 正确:http://localhost:7081/es/add
     * 可以成功
     *
     * @return
     */
    @RequestMapping("add")
    public String addEntity() {
        Entity entity = new Entity();
        entity.setId(100222222223L);
        entity.setName("测试");
        Long result = cityESService.saveEntity(entity);
        System.out.print(result);
        System.err.println("add a obj");
        return "success";
    }

    /**
     * 添加雇员信息
     *
     * @return
     */
    @RequestMapping("addEmployee")
    public String addEmployee() {
        Employee employee = new Employee();
        employee.setId("1");
        employee.setFirstName("fengzhiqiang");
        employee.setLastName("zh");
        employee.setAge(26);
        employee.setAbout("i am in peking");
        String result = cityESService.saveEmployee(employee);
        System.out.print(result);
        System.err.println("add a obj");
        return "success";
    }

    /**
     * 删除
     *
     * @return
     */
    @RequestMapping("delete")
    public String delete() {
        Employee employee = employeeRepository.queryEmployeeById("1");
        employeeRepository.delete(employee);
        return "success";
    }

    /**
     * 局部更新
     *
     * @return
     */
    @RequestMapping("update")
    public String update() {
        Employee employee = employeeRepository.queryEmployeeById("1");
        employee.setFirstName("哈哈");
        employeeRepository.save(employee);
        System.err.println("update a obj");
        return "success";
    }

    /**
     * 查询雇员信息
     *
     * @return
     */
    @RequestMapping("query")
    public Employee query() {
        Employee accountInfo = employeeRepository.queryEmployeeById("1");
        System.err.println(new Gson().toJson(accountInfo));
        return accountInfo;
    }

    /**
     * 添加region
     *
     * @return
     */
    @RequestMapping("addRegion")
    public String addRegion() {
        Region region = new Region();
        region.setId("6048419");
        List<String> list = new ArrayList();
        list.add("23520561");
        list.add("23520562");
        list.add("23520563");
        list.add("23520564");
        list.add("23520566");
        region.setPropertyId(list);
        regionService.save(region);

        return "success";
    }

    /**
     * 查询region
     *
     * @return
     */
    @RequestMapping("queryRegion")
    public Region queryRegion() {
        Region region = regionService.queryRegionById("6048419");
        return region;
    }

    /**
     * 删除
     *
     * @return
     */
    @RequestMapping("deleteRegion")
    public String deleteRegion() {
        Region region = regionService.queryRegionById("1");
        regionService.delete(region);
        return "success";
    }


}
