package com.example.demo.controller;

import java.util.List;

import com.example.demo.authorization.AuthorizationService;
import com.example.demo.bean.Position;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	@ResponseBody
	public String postClient( @RequestParam String name, @RequestParam String email ) throws RuntimeException
	{
		return _authorizationService.createTokenForUser( name, email );
	}
	
	@PostMapping( "/positions" )
	@ResponseBody
	public String postPosition(@RequestParam String tokenString, @RequestParam String job,
			@RequestParam String location ) throws RuntimeException
	{
		return null;
	}
	
	@GetMapping( "/positions" )
	@ResponseBody
	public List<Position> getPosition(@RequestParam String tokenString, @RequestParam String job,
			@RequestParam String location ) throws RuntimeException
	{
		return null;
	}
	
	private final AuthorizationService _authorizationService;
}
