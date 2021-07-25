package com.example.demoMaven.authorization;

import com.example.demoMaven.bean.Token;

public interface AuthorizationService
{
	String createTokenForUser( String name, String email );
	
	void validateNameAndEmail( String name, String email ) throws IllegalArgumentException;
	
	void validateUserToken( Token token );
}
