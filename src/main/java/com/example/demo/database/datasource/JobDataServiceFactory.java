package com.example.demo.database.datasource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class JobDataServiceFactory
{
	public JobDataServiceFactory( List<JobDataService> jobDataServices, RestTemplate restTemplate )
	{
		_restTemplate = restTemplate;
	}
	
	public List<JobDataService> getDataResources()
	{
		if ( _jobDataServices == null)
		{
			initDataResources();
		}
		return _jobDataServices;
	}
	
	private void initDataResources()
	{
		_jobDataServices = new ArrayList<>();
		_jobDataServices.add( new GitJobDataService( _restTemplate ) );
	}
	
	private List<JobDataService> _jobDataServices;
	private final RestTemplate _restTemplate;
	
}
