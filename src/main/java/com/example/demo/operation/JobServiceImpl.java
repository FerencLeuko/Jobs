package com.example.demo.operation;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

import com.example.demo.bean.Job;
import com.example.demo.database.datasource.JobDataService;
import com.example.demo.database.datasource.JobDataServiceFactory;
import com.example.demo.database.datasource.LocalJobDataBaseService;
import com.example.demo.database.entity.EntityMapper;
import com.example.demo.database.entity.JobEntity;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

@Service
public class JobServiceImpl implements JobService
{
	public JobServiceImpl( LocalJobDataBaseService localJobDataBaseService, JobDataServiceFactory jobDataServiceFactory )
	{
		_localJobDataBaseService = localJobDataBaseService;
		_jobDataServiceFactory = jobDataServiceFactory;
		_jobDataServices = getJobDataServices();
		_executorService = Executors.newFixedThreadPool( _jobDataServices.size() );
		_entityMapper = getMapper();
	}
	
	@Override
	public String postJob( Job job )
	{
		return _localJobDataBaseService.postPosition( _entityMapper.jobToEntity( job ) );
	}
	
	@Override
	public List<Job> getJob( String keyWord, String location )
	{
		List<JobEntity> jobs = new ArrayList<>();
		
		_jobDataServices.add( _localJobDataBaseService );
		
		List<Future<List<JobEntity>>> futures = new ArrayList<>();
		
		_jobDataServices.stream()
				.forEach( d -> futures.add( _executorService.submit( new CallTask( d, keyWord, location ) ) ) );
		
		for ( Future<List<JobEntity>> future: futures)
		{
			try
			{
				jobs.addAll( future.get( 1000, TimeUnit.MILLISECONDS) );
			}
			catch( InterruptedException | ExecutionException | TimeoutException e )
			{
			}
		}
		
		return jobs.stream()
				.map( entity -> _entityMapper.entityToJob( entity ) )
				.distinct()
				.collect( Collectors.toList() );
	}
	
	private class CallTask implements Callable<List<JobEntity>>
	{
		CallTask( JobDataService jobDataService, String keyword, String location )
		{
			_jobDataService = jobDataService;
			_keyWord = keyword;
			_location = location;
		}
		
		@Override
		public List<JobEntity> call() throws Exception
		{
			return _jobDataService.getPosition( _keyWord, _location );
		}
		
		JobDataService _jobDataService;
		String _keyWord;
		String _location;
	}
	
	protected static EntityMapper getMapper()
	{
		return Mappers.getMapper( EntityMapper.class );
	}
	
	private List<JobDataService> getJobDataServices()
	{
		return _jobDataServiceFactory.getDataResources();
	}
	
	private final LocalJobDataBaseService _localJobDataBaseService;
	
	private final JobDataServiceFactory _jobDataServiceFactory;
	
	private List<JobDataService> _jobDataServices;
	
	private final ExecutorService _executorService;
	
	protected static EntityMapper _entityMapper;
}
