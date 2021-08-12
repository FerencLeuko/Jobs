package com.example.demo.controller;

import java.util.List;

import com.example.demo.authorization.AuthorizationService;
import com.example.demo.bean.Job;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@PostMapping( "/clients" )
	public String postClient( @RequestParam String name, @RequestParam String email ) throws RuntimeException
	{
		return _authorizationService.createTokenForUser( name, email );
	}
	
	@PostMapping( "/positions" )
	public String postPosition(@RequestBody Job job, @RequestParam( value = "token" ) String tokenString ) throws RuntimeException
	{
		return _authorizationService.postJob( tokenString, job );
	}
	
	@GetMapping( "/positions" )
	@ResponseBody
	public List<Job> getPosition(@RequestParam( value = "token" ) String tokenString, @RequestParam String position,
			@RequestParam String location ) throws RuntimeException
	{
		return _authorizationService.getJob( tokenString, position, location );
	}
	
	private final AuthorizationService _authorizationService;
}
