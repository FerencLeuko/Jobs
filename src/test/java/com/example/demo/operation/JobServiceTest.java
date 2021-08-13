package com.example.demo.operation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.demo.bean.Job;
import com.example.demo.database.entity.EntityMapper;
import com.example.demo.database.entity.JobEntity;
import org.junit.jupiter.api.Test;

public class JobServiceTest
{
	
	@Test
	public void testMapping()
	{
		Job source =
				Job.builder().position( "ABC" ).location( "def" ).url( "GHI" ).build();
		
		JobEntity mapped = _mapper.jobToEntity( source );
		
		assertEquals( source, _mapper.entityToJob( mapped ));
	}
	
	private final EntityMapper _mapper = JobServiceImpl.getMapper();
}
