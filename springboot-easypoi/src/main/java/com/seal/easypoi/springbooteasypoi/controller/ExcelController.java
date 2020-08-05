package com.seal.easypoi.springbooteasypoi.controller;

import com.seal.easypoi.springbooteasypoi.dto.PersonExportDTO;
import com.seal.easypoi.springbooteasypoi.utils.ExcelUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author fengzhiqiang
 * @date 2020/8/5 17:42
 **/
@RestController
@RequestMapping("/api/excel/")
public class ExcelController {

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
        List<PersonExportDTO> list = null;
        try {
            list = ExcelUtils.importExcel(file, PersonExportDTO.class);
            System.out.println(list.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list.toString();
    }
}
