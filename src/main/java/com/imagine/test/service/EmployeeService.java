package com.imagine.test.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imagine.test.entity.Employee;
import com.imagine.test.repository.EmployeeRepository;
import com.imagine.test.response.EmployeeTaxResponse;
import com.imagine.test.utils.EmployeeUtils;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	public LocalDate getFinancialYear(Integer year) {
		return LocalDate.of(year, 4, 1);
	}

	public List<EmployeeTaxResponse> findAllTaxes(Integer year) {
		List<Employee> employees = employeeRepository.findAll();
		List<EmployeeTaxResponse> employeeTaxList = new ArrayList<>();
		for (Employee employee : employees) {
			EmployeeTaxResponse employeeTaxResponse = new EmployeeTaxResponse();
			employeeTaxResponse.setId(employee.getId());
			employeeTaxResponse.setFinancialYear(getFinancialYear(year));
			employeeTaxResponse.setTax(caliculateTax(caliculateSalaryInFinYear(year, employee.getSalary(), employee.getDoj())));
			employeeTaxList.add(employeeTaxResponse);
		}
		return employeeTaxList;
	}

	public Double caliculateSalaryInFinYear(Integer financialYearEnd, Double salary, LocalDate doj) {
		Double ctc = salary * 12;
//		int financialYearEnd = getFinancialYear(year).getYear();
		int financialYearStart = financialYearEnd - 1;
		if (doj.getYear() == financialYearStart) {
			return ctc - ctc / caliculateDaysInFinYear(doj);
		}
		return 0.0;
	}

	public static long caliculateDaysInFinYear(LocalDate doj) {
		return ChronoUnit.DAYS.between(doj, LocalDate.of(doj.getYear() + 1, 3, 31)) + 1; // Add 1 to include both start
																							// and end dates
	}

	public Double caliculateTax(Double salary) {
		if (salary > 250000 && salary <= 500000.0) {
			return taxBySlab(salary, 5);
		}
		if (salary > 500000 && salary <= 1000000) {
			return taxBySlab(salary, 5) + taxBySlab(salary-500000, 10);
		}
		if (salary > 1000000) {
			if (salary > 2500000) {
				return taxBySlab(salary, 5) + taxBySlab(250000.0, 10) + + taxBySlab(2500000.0-750000.0, 20) + taxBySlab(salary-2500000, 2);
			}
			return taxBySlab(salary, 5) + taxBySlab(250000.0, 10) + + taxBySlab(2500000.0-750000.0, 20);
		}
		return 0.0;
	}

	public Double taxBySlab(Double salary, int percentage) {
		return EmployeeUtils.calculateValue(5, salary);
	}
}
