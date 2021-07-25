package com.example.demoMaven.authorization;

import com.example.demoMaven.bean.Token;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationServiceImpl implements AuthorizationService
{
	public AuthorizationServiceImpl( EmailValidatorService emailValidatorService )
	{
		_emailValidatorService = emailValidatorService;
	}
	
	@Override
	public String createTokenForUser( String name, String email ) throws IllegalArgumentException
	{
		try
		{
			validateNameAndEmail( name, email );
		}
		catch( IllegalArgumentException e )
		{
			throw new IllegalArgumentException ( "Incorrect parameters : " + e.getMessage() );
		}
		
		Token token = new Token.TokenBuilder().setUuid().build();
		
		// TODO check if unique and save all user info to database
		
		return token.get_uuidString();
	}
	
	@Override
	public void validateNameAndEmail( String name, String email ) throws IllegalArgumentException
	{
		if ( name.length() > 100 )
		{
			throw new IllegalArgumentException( "Name is not accepted" );
		}
		if ( !_emailValidatorService.validateEmail( email ) )
		{
			throw new IllegalArgumentException( "Email is not accepted" );
		}
	}
	
	@Override
	public void validateUserToken( Token token )
	{
		// TODO check if token is valid
	}
	
	private final EmailValidatorService _emailValidatorService;
}
