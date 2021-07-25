package com.example.demoMaven.dataservice;

import com.example.demoMaven.bean.User;

public interface RegistrationService
{
	void registerUser ( User user );
	
	boolean checkUser ( String tokenString );
}
