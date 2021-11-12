package com.cos.photogramstart.handler;

import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.util.Script;

@RestController
@ControllerAdvice
public class ControllerExceptionHandler {
	
	@ExceptionHandler(CustomValidationException.class)
	public String validationException(CustomValidationException e) {
		// CMRespDto 와 Script 비교
		// 1. 클라이언트에게 응답할때는 Script 가 좋음
		// 2. Ajax , Android 통신 - CMRespDto 
		return Script.back(e.getErrorMap().toString());
	}
}
