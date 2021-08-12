package com.example.demo.dataservice;

import java.util.List;

import com.example.demo.bean.Job;

public interface JobService
{
	String postJob( Job job );
	
	List<Job> getJob( String keyWord, String location );
}
