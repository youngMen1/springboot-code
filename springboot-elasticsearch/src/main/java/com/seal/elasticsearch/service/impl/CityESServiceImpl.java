package com.seal.elasticsearch.service.impl;

import com.seal.elasticsearch.entity.Employee;
import com.seal.elasticsearch.entity.Entity;
import com.seal.elasticsearch.service.CityESService;
import com.seal.elasticsearch.service.EmployeeRepository;
import com.seal.elasticsearch.service.EntityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2018/10/15 21:31
 * @description TODO
 **/
@Slf4j
@Service
public class CityESServiceImpl implements CityESService {

    /**
     * 默认分页大小
     */
    int PAGE_SIZE = 15;
    /**
     * 默认当前分页
     */
    int PAGE_NUMBER = 0;
    /**
     * 权重分求和模式
     */
    String SCORE_MODE_SUM = "sum";
    /**
     * 由于无相关性的分值默认为1， 设置权重分最小值为10
     */
    Float MIN_SCORE = 10.0F;

    @Autowired
    EntityRepository entityRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    /**
     * 保存内容到ES
     */
    @Override
    public Long saveEntity(Entity entity) {
        Entity entityResult = entityRepository.save(entity);
        return entityResult.getId();
    }

    @Override
    public String saveEmployee(Employee employee) {
        Employee e = employeeRepository.save(employee);
        return e.getId();
    }


    /**
     * 在ES中搜索内容
     */
/*    public List<entity> searchEntity(int pageNumber, int pageSize, String searchContent){
        if(pageSize==0) {
            pageSize = PAGE_SIZE;
        }
        if(pageNumber<0) {
            pageNumber = PAGE_NUMBER;
        }

        SearchQuery searchQuery = getEntitySearchQuery(pageNumber,pageSize,searchContent);

        LOGGER.info("\n searchCity: searchContent [" + searchContent + "] \n DSL  = \n "
                + searchQuery.getQuery().toString());


        Page<entity> cityPage = entityRepository.search(searchQuery);
        return cityPage.getContent();
    }*/


    /**
     * 组装搜索Query对象
     * @param pageNumber
     * @param pageSize
     * @param searchContent
     * @return
     */
  /*  private SearchQuery getEntitySearchQuery(int pageNumber, int pageSize, String searchContent) {
        FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery()
                        .add(QueryBuilders.matchPhraseQuery("name", searchContent),
                        ScoreFunctionBuilders.weightFactorFunction(1000))
                //.add(QueryBuilders.matchPhraseQuery("other", searchContent),
                //ScoreFunctionBuilders.weightFactorFunction(1000))
                .scoreMode(SCORE_MODE_SUM).setMinScore(MIN_SCORE);
        //设置分页，否则只能按照ES默认的分页给
        Pageable pageable = new PageRequest(pageNumber, pageSize);
        return new NativeSearchQueryBuilder().withPageable(pageable).withQuery(functionScoreQueryBuilder).build();
    }*/


}

