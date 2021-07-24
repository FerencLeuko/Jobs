package com.example.demoMaven.controller;

import com.example.demoMaven.bean.Uuid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoApiController
{
	@GetMapping("/getToken")
	@ResponseBody
	public String getToken(@RequestParam String name, @RequestParam String email ) throws RuntimeException
	{
		validateNameAndEmail( name, email );
		return createTokenForUser( name, email );
	}
	
	private String createTokenForUser( String name , String email )
	{
		Uuid token = new Uuid.UuidBuilder().setUuid().build();
		// TODO check if unique and save all user info to database
		return token.get_uuidString();
	}
	
	private void validateNameAndEmail( String name, String email )
	{
		// TODO implement regex validation
		if ( name.equals( "" ) || email.equals( "" )){
			throw new IllegalArgumentException( "Parameters are not accepted" );
		}
	}
}
