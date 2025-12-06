package com.example.demo.service;

import com.example.demo.model.EmployeeInfo;
import com.example.demo.repository.EmployeeInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeInfoService {
    @Autowired
    private EmployeeInfoRepository employeeInfoRepository;

    public List<EmployeeInfo> findEmployeeInfoListByStatus(int status){
        List<EmployeeInfo> employeeInfoList = employeeInfoRepository.findEmployeeInfosByStatus(status);

        return employeeInfoList;
    }
}
