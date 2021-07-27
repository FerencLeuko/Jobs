package com.example.demo.dataservice;

import com.example.demo.bean.User;

public interface RegistrationService
{
	String registerUser ( User user );
	
	boolean checkTokenExists( String tokenString );
}
