package com.example.demo.authorization;

public interface AuthorizationService
{
	String createTokenForUser( String name, String email );
}
