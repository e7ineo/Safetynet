package com.e7i.safetynetapi.controller;

import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class  ErrorController extends AbstractErrorController  {

    public ErrorController(ErrorAttributes errorAttributes) {
		super(errorAttributes);
	}

	@RequestMapping("/error")
    public ResponseEntity<String> error() {
    	return ResponseEntity.badRequest().body("Invalid URL");
    }
}
 