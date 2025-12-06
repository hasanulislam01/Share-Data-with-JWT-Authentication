package com.example.demo.repository;

import com.example.demo.model.EmployeeInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeInfoRepository extends JpaRepository<EmployeeInfo, Long> {
    Optional<EmployeeInfo> findEmployeeInfoById(Long id);

    List<EmployeeInfo> findEmployeeInfosByStatus(Integer status);
}
