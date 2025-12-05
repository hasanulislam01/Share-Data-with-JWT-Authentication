package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "employee_info")
public class EmployeeInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
