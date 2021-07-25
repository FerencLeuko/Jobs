package com.example.demoMaven.dataservice;

import java.util.List;

import com.example.demoMaven.bean.Position;

public interface JobService
{
	String postPosition( String position, String location);
	
	List<Position> getPosition( String keyWord, String location );
}
