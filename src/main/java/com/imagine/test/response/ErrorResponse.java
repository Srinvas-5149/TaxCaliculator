package com.imagine.test.response;

import lombok.Data;

@Data
public class ErrorResponse {
	
	private Integer status;
    private String message;

}
