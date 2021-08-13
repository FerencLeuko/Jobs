package com.example.demo.database.datasource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.demo.database.entity.JobEntity;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

class GitJobDataService implements JobDataService
{
	GitJobDataService( RestTemplate restTemplate )
	{
		_restTemplate = restTemplate;
		_apiHost = "https://jobs.github.com";
		init();
	}
	
	@Override
	public List<JobEntity> getPosition( String keyWord, String location )
	{
		List<JobEntity> jobs = new ArrayList<>();
		
		jobs.addAll( getJobs( keyWord, location ) );
		
		return jobs;
	}
	
	@GetMapping("/positions/{description}/{location}")
	public List<JobEntity> getJobs(@PathVariable String description, @PathVariable String location)
	{
		Map<String, String> params = new HashMap<String, String>();
		params.put("description", description);
		params.put( "location", location );
		
		List<JobEntity> jobs = _restTemplate.getForObject( _apiHost, JobEntityList.class, params ).getJobList();
		
		return jobs;
	}
	
	private void init()
	{
		_restTemplate.setUriTemplateHandler( new DefaultUriBuilderFactory( _apiHost ) );
	}
	
	@Data
	private static class JobEntityList
	{
		JobEntityList()
		{
			jobList = new ArrayList<>();
		}
		private List<JobEntity> jobList;
	}
	

	private final RestTemplate _restTemplate;
	
	private final String _apiHost;
}
