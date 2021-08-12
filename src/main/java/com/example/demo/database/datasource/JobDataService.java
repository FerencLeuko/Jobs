package com.example.demo.database.datasource;

import java.util.List;

import com.example.demo.bean.Job;
import com.example.demo.database.entity.JobEntity;

public interface JobDataService
{
	List<JobEntity> getPosition( String keyWord, String location );
}
