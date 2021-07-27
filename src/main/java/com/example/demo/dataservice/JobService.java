package com.example.demo.dataservice;

import java.util.List;

import com.example.demo.bean.Position;

public interface JobService
{
	String postPosition( String position, String location);
	
	List<Position> getPosition( String keyWord, String location );
}
