package com.example.demo.authorization;

import java.util.List;

import com.example.demo.bean.Job;

public interface AuthorizationService
{
	String createTokenForUser( String name, String email );
	
	String postJob ( String token, Job job);
	
	List<Job> getJob ( String token, String position, String location );
}
