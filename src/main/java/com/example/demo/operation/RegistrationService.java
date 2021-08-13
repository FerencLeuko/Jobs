package com.example.demo.operation;

import com.example.demo.bean.User;

public interface RegistrationService
{
	String registerUser ( User user );
	
	boolean checkTokenExists( String tokenString );
}
