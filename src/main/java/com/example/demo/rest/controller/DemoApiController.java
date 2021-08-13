package com.example.demo.rest.controller;

import java.util.List;

import com.example.demo.authorization.AuthorizationService;
import com.example.demo.bean.Job;
import com.example.demo.exception.ErrorResponse;
import com.example.demo.exception.IllegalParameterException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
	
	@ExceptionHandler(  { IllegalParameterException.class } )
	public ResponseEntity<ErrorResponse> handleArgumentExceptions( IllegalParameterException e )
	{
		return new ResponseEntity<ErrorResponse>( new ErrorResponse( e.getMessage() ), HttpStatus.BAD_REQUEST );
	}
	
	@ExceptionHandler(  { RuntimeException.class } )
	public ResponseEntity<ErrorResponse> handleRuntimeExceptions( RuntimeException e )
	{
		return new ResponseEntity<ErrorResponse>( new ErrorResponse( e.getMessage() ), HttpStatus.INTERNAL_SERVER_ERROR );
	}
	
	private final AuthorizationService _authorizationService;
}
