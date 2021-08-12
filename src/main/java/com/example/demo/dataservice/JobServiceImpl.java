package com.example.demo.dataservice;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.bean.Job;
import com.example.demo.database.datasource.JobDataService;
import com.example.demo.database.datasource.JobDataServiceFactory;
import com.example.demo.database.datasource.LocalJobDataBaseService;
import com.example.demo.database.entity.JobEntity;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

@Service
public class JobServiceImpl implements JobService
{
	public JobServiceImpl( LocalJobDataBaseService localJobDataBaseService )
	{
		_localJobDataBaseService = localJobDataBaseService;
		_jobDataServices = getJobDataServices();
		_entityMapper = getMapper();
	}
	
	@Override
	public String postJob( Job job  )
	{
		return _localJobDataBaseService.postPosition( _entityMapper.jobToEntity( job ));
	}
	
	@Override
	public List<Job> getJob( String keyWord, String location )
	{
		List<JobEntity> jobs = new ArrayList<>();
		
		jobs.addAll( _localJobDataBaseService.getPosition( keyWord, location ) );
		
		_jobDataServices.stream()
				.peek( resource -> jobs.addAll( resource.getPosition( keyWord, location )) );
		
		return jobs.stream()
				.map( entity -> _entityMapper.entityToJob( entity ) )
				.collect( Collectors.toList());
	}
	
	protected static EntityMapper getMapper()
	{
		return Mappers.getMapper( EntityMapper.class );
	}
	
	private static List<JobDataService> getJobDataServices(){ return JobDataServiceFactory.getDataResources(); }
	
	private final LocalJobDataBaseService _localJobDataBaseService;
	
	private static List<JobDataService> _jobDataServices;
	
	protected static EntityMapper _entityMapper;
}
