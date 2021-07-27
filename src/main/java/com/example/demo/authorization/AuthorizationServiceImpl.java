package com.example.demo.authorization;

import com.example.demo.bean.Token;
import com.example.demo.bean.User;
import com.example.demo.database.repository.UserRepository;
import com.example.demo.dataservice.RegistrationService;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationServiceImpl implements AuthorizationService
{
	public AuthorizationServiceImpl( EmailValidatorService emailValidatorService, UserRepository userRepository, RegistrationService registrationService )
	{
		_emailValidatorService = emailValidatorService;
		_registrationService = registrationService;
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
		
		Token token = generateUniqueToken();
		
		User user = User.builder().name( name ).email( email ).token( token ).build();
		
		return _registrationService.registerUser( user );
	}
	
	
	private void validateNameAndEmail( String name, String email ) throws IllegalArgumentException
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
	
	private void validateUserToken( Token token ) throws IllegalArgumentException
	{
		_registrationService.checkTokenExists( token.get_uuidString() );
	}
	
	private Token generateUniqueToken()
	{
		Token token;
		int safety = 1;
		
		do
		{
			token = new Token.TokenBuilder().setUuid().build();
			safety++;
		} while ( _registrationService.checkTokenExists( token.get_uuidString() ) && safety < 1000 );
		
		return token;
	}
	
	private final EmailValidatorService _emailValidatorService;
	private final RegistrationService _registrationService;
}
