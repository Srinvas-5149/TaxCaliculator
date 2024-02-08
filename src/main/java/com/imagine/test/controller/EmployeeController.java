package com.imagine.test.controller;

import java.util.List;

import javax.validation.Valid;

//EmployeeController.java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imagine.test.entity.Employee;
import com.imagine.test.exception.EmployeeNotFoundException;
import com.imagine.test.repository.EmployeeRepository;
import com.imagine.test.response.EmployeeTaxResponse;
import com.imagine.test.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

 @Autowired
 private EmployeeRepository employeeRepository;
 
 @Autowired
 private EmployeeService employeeService;

 @PostMapping("/add")
 public ResponseEntity<?> createEmployee(@Valid @RequestBody Employee employee, BindingResult result) {
     if (result.hasErrors()) {
         return new ResponseEntity<>("Invalid request", HttpStatus.BAD_REQUEST);
     }
     Employee savedEmployee = employeeRepository.save(employee);
     return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
 }

 
 @GetMapping("/taxByFinancialYear/{financialYear}")
 public ResponseEntity<List<EmployeeTaxResponse>> getAllEmployeesTaxInFinancialYear(@PathVariable Integer financialYear) {
     return new ResponseEntity<>(employeeService.findAllTaxes(financialYear), HttpStatus.OK);
 }

}
