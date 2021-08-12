package com.example.demo.database.datasource;

import java.util.List;

import com.example.demo.database.entity.JobEntity;
import com.example.demo.database.repository.JobRepository;
import com.example.demo.database.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class LocalJobDataBaseService implements JobDataService
{
	public LocalJobDataBaseService( UserRepository userRepository, JobRepository jobRepository )
	{
		_userRepository = userRepository;
		_jobRepository = jobRepository;
	}
	
	public String postPosition( JobEntity jobEntity )
	{
		_jobRepository.save( jobEntity );
		return jobEntity.getUrl();
	}
	
	@Override
	public List<JobEntity> getPosition( String keyWord, String location )
	{
		return _jobRepository.findJobsByPositionAndLocation( keyWord, location );
	}
	
	private final UserRepository _userRepository;
	
	private final JobRepository _jobRepository;
	
	
}
