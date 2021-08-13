package com.example.demo.authorization;

import java.util.List;

import com.example.demo.bean.Job;
import com.example.demo.bean.Token;
import com.example.demo.bean.User;
import com.example.demo.exception.IllegalParameterException;
import com.example.demo.operation.JobService;
import com.example.demo.operation.RegistrationService;
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
		catch( IllegalParameterException e )
		{
			throw new IllegalParameterException( "Incorrect user parameters : " + e.getMessage() );
		}
		
		Token token = generateUniqueToken();
		
		User user = User.builder().name( name ).email( email ).token( token ).build();
		
		return _registrationService.registerUser( user );
	}
	
	@Override
	public String postJob( String token, Job job )
	{
		validateUserToken( token );
		
		validateJobRequest( job.getPosition(), job.getLocation() );
		
		return _jobService.postJob( job );
	}
	
	@Override
	public List<Job> getJob( String token, String keyWord, String location )
	{
		validateUserToken( token );
		
		validateJobRequest( keyWord, location );
		
		return _jobService.getJob( keyWord, location );
	}
	
	
	private void validateNameAndEmail( String name, String email ) throws IllegalArgumentException
	{
		if ( name.length() > 100 )
		{
			throw new IllegalParameterException( "Name is not valid, exceeds 100 characters." );
		}
		if ( !_emailValidatorService.validateEmail( email ) )
		{
			throw new IllegalParameterException( "Email is not valid, please check email format." );
		}
	}
	
	private void validateUserToken( String token ) throws IllegalArgumentException
	{
		if (!_registrationService.checkTokenExists( token ))
		{
			throw new IllegalParameterException( "Token is not valid." );
		}
	}
	
	private void validateJobRequest( String position, String location)
	{
		if( position.length() > 50 )
		{
			throw new IllegalParameterException( "Position is not valid, exceeds 50 characters" );
		}
		if( location.length() > 50 )
		{
			throw new IllegalParameterException( "Location is not valid, exceeds 50 characters." );
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
