package com.example.demoMaven.dataservice;

import com.example.demoMaven.bean.User;

public class RegistrationServiceImpl implements RegistrationService
{
	@Override
	public void registerUser( User user )
	{
	
	}
	
	@Override
	public boolean checkUser( String tokenString )
	{
		return false;
	}
	
	private boolean checkUniqueUserData ( User user )
	{
		return false;
	}
}
