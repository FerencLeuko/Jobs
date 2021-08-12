package com.example.demo.authorization;

import java.util.List;

import com.example.demo.bean.Job;
import com.example.demo.bean.Token;
import com.example.demo.bean.User;
import com.example.demo.dataservice.JobService;
import com.example.demo.dataservice.RegistrationService;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationServiceImpl implements AuthorizationService
{
	public AuthorizationServiceImpl( EmailValidatorService emailValidatorService, RegistrationService registrationService, JobService jobService )
	{
		_emailValidatorService = emailValidatorService;
		_registrationService = registrationService;
		_jobService = jobService;
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
	
	@Override
	public String postJob( String token, Job job )
	{
		validateUserToken( token );
		return _jobService.postJob( job );
	}
	
	@Override
	public List<Job> getJob( String token, String keyWord, String location )
	{
		validateUserToken( token );
		return _jobService.getJob( keyWord, location );
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
	
	private void validateUserToken( String token ) throws IllegalArgumentException
	{
		if (!_registrationService.checkTokenExists( token ))
		{
			throw new IllegalArgumentException( "Token is not valid" );
		}
	}
	
	private Token generateUniqueToken()
	{
		Token token;
		int safety = 1;
		
		do
		{
			token = new Token.TokenBuilder().setUuid().build();
			if ( safety++ > 1000 )
			{
				throw new RuntimeException( "Unable to create user token, please try later." );
			}
		}
		while ( _registrationService.checkTokenExists( token.get_uuidString() ) );
		
		return token;
	}
	
	private final EmailValidatorService _emailValidatorService;
	private final RegistrationService _registrationService;
	private final JobService _jobService;
}
