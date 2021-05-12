package com.seal.easypoi.springbooteasypoi.controller;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.seal.easypoi.springbooteasypoi.dao.AreaBaseMapper;
import com.seal.easypoi.springbooteasypoi.dao.AreaMapper;
import com.seal.easypoi.springbooteasypoi.dao.ArrivalMapper;
import com.seal.easypoi.springbooteasypoi.dao.BankAreaMapper;
import com.seal.easypoi.springbooteasypoi.dto.ContractDto;
import com.seal.easypoi.springbooteasypoi.dto.PersonExportDTO;
import com.seal.easypoi.springbooteasypoi.entity.Area;
import com.seal.easypoi.springbooteasypoi.utils.ExcelUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * @author fengzhiqiang
 * @date 2020/8/5 17:42
 **/
@RestController
@RequestMapping("/api/excel/")
public class ExcelController {

    @Resource
    private AreaMapper areaMapper;
    @Resource
    private ArrivalMapper arrivalMapper;
    @Resource
    private BankAreaMapper bankAreaMapper;
    @Resource
    private AreaBaseMapper areaBaseMapper;

    @GetMapping("exportInfo")
    public void exportExcel(HttpServletResponse response) {
        List<PersonExportDTO> personList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            PersonExportDTO personVo = new PersonExportDTO();
            personVo.setName("张三" + i);
            personVo.setUsername("张三" + i);
            personVo.setPhoneNumber("18888888888");
            personVo.setImageUrl("/static/user1-128x128.jpg");
            personList.add(personVo);
        }
        try {
            ExcelUtils.exportExcel(personList, "员工信息表", "员工信息", PersonExportDTO.class, "员工信息", response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 导出excel
     *
     * @return 结果
     */
    @GetMapping("exportToFile")
    public Map<String, Object> export() {
        List<PersonExportDTO> personList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            PersonExportDTO personVo = new PersonExportDTO();
            personVo.setName("张三" + i);
            personVo.setUsername("张三" + i);
            personVo.setPhoneNumber("18888888888");
            personVo.setImageUrl("/static/user1-128x128.jpg");
            personList.add(personVo);
        }
        String fileName = ExcelUtils.exportExcelToFile(personList, "员工信息", PersonExportDTO.class);
        Map<String, Object> map = new HashMap<>(16);
        map.put("fileName", fileName);
        return map;
    }

    /**
     * 导入
     *
     * @param file
     */
    @PostMapping("import")
    public Object importExcel(@RequestParam("file") MultipartFile file) {
        List<Area> list;
        int i = 0;
        try {
            list = ExcelUtils.importExcel(file, Area.class);
            int total = areaMapper.batchInsertMember(list);
            System.out.println("保存条数:=" + total);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return i;
    }


    /**
     * 导入区域信息
     *
     * @param file
     */
    @PostMapping("importArea")
    public Object importArea(@RequestParam("file") MultipartFile file) {
        List<Area> list;
        int total = 0;
        try {
            list = ExcelUtils.importExcel(file, Area.class);
            for (Area area : list) {
                List<String> stringList = Arrays.asList(area.getAreaName().split(","));
                // 最后一条
                String areaName = stringList.get(stringList.size() - 1);
                // 最后第二条
                String parentName = stringList.get(stringList.size() - 2);
                List<Area> areaParentNames = areaMapper.selectList(Wrappers.lambdaQuery(Area.class)
                        .eq(Area::getAreaName, parentName));
                if (!CollectionUtils.isEmpty(areaParentNames)) {
                    if (areaParentNames.size() > 1) {
                        for (Area area1 : areaParentNames) {
                            if (area.getAreaCode().substring(0, 2).equals(area1.getAreaCode().substring(0, 2))) {
                                Area area2 = new Area();
                                area2.setAreaCode(area.getAreaCode());
                                area2.setAreaName(areaName);
                                area2.setLevel(stringList.size() - 1);
                                area2.setParentCode(area1.getAreaCode());
                                areaMapper.insert(area2);
                                total++;
                            }
                        }
                    } else {
                        Area area2 = new Area();
                        area2.setAreaCode(area.getAreaCode());
                        area2.setAreaName(areaName);
                        area2.setLevel(stringList.size() - 1);
                        area2.setParentCode(areaParentNames.get(0).getAreaCode());
                        areaMapper.insert(area2);
                        total++;
                    }
                }
            }
            System.out.println("保存条数:=" + total);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return total;
    }

    @PostMapping("test")
    public Object test(@Validated @RequestBody ContractDto contractDto) {
        return null;
    }
}
