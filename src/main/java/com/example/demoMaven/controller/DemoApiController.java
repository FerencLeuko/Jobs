package com.example.demoMaven.controller;

import com.example.demoMaven.authorization.AuthorizationService;
import com.example.demoMaven.authorization.EmailValidatorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoApiController
{
	public DemoApiController( AuthorizationService authorizationService )
	{
		_authorizationService = authorizationService;
	}
	
	@GetMapping("/getToken")
	@ResponseBody
	public String getToken(@RequestParam String name, @RequestParam String email ) throws RuntimeException
	{
		return _authorizationService.createTokenForUser( name, email );
	}
	
	private final AuthorizationService _authorizationService;
}
