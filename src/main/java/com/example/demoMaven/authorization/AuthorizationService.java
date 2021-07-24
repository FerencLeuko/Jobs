package com.example.demoMaven.authorization;

import com.example.demoMaven.bean.Uuid;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService
{
	public AuthorizationService( EmailValidatorService emailValidatorService )
	{
		_emailValidatorService = emailValidatorService;
	}
	
	public String createTokenForUser( String name, String email )
	{
		try
		{
			validateNameAndEmail( name, email );
		}
		catch( IllegalArgumentException e )
		{
			return "Incorrect parameters : " + e.getMessage();
		}
		
		Uuid token = new Uuid.UuidBuilder().setUuid().build();
		
		// TODO check if unique and save all user info to database
		
		return token.get_uuidString();
	}
	
	public void validateNameAndEmail( String name, String email ) throws IllegalArgumentException
	{
		if ( name.length() < 2 )
		{
			throw new IllegalArgumentException( "Name is not accepted" );
		}
		if ( !_emailValidatorService.validateEmail( email ) )
		{
			throw new IllegalArgumentException( "Email is not accepted" );
		}
	}
	
	private final EmailValidatorService _emailValidatorService;
}
