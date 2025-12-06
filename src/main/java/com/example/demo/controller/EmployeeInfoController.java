package com.example.demo.controller;

import com.example.demo.model.EmployeeInfo;
import com.example.demo.service.EmployeeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/employeeInfo")
public class EmployeeInfoController {

    @Autowired
    EmployeeInfoService employeeInfoService;

    @GetMapping("/findEmployeeInfoList")
    public ResponseEntity<List<EmployeeInfo>> findEmployeeInfoListByStatus() throws Exception{
        System.out.println("Rest request for get data.");
        List<EmployeeInfo> employeeInfoList = employeeInfoService.findEmployeeInfoListByStatus(1);

        return ResponseEntity.ok(employeeInfoList);
    }
}
