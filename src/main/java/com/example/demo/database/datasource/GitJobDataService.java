package com.example.demo.database.datasource;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.database.entity.JobEntity;

class GitJobDataService implements JobDataService
{
	@Override
	public List<JobEntity> getPosition( String keyWord, String location )
	{
		List<JobEntity> jobs = new ArrayList<>();
		
		return jobs;
	}
}
