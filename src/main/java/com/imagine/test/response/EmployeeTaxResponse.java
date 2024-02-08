package com.imagine.test.response;

import java.time.LocalDate;

public class EmployeeTaxResponse {
	
	private Long id;
	private Double tax;
	private LocalDate financialYear;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Double getTax() {
		return tax;
	}
	public void setTax(Double tax) {
		this.tax = tax;
	}
	public LocalDate getFinancialYear() {
		return financialYear;
	}
	public void setFinancialYear(LocalDate financialYear) {
		this.financialYear = financialYear;
	}
	
	

}
