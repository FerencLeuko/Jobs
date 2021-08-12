package com.example.demo.database.datasource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class JobDataServiceFactory
{
	public static List<JobDataService> getDataResources()
	{
		if ( _jobDataServices == null)
		{
			initDataResources();
		}
		return _jobDataServices;
	}
	
	private static void initDataResources()
	{
		_jobDataServices = new ArrayList<>();
		_jobDataServices.add( new GitJobDataService() );
	}
	
	private static List<JobDataService> _jobDataServices;
	
}
