package com.example.demoMaven.database;

import java.util.List;

import com.example.demoMaven.bean.Position;

public interface DataResource
{
	String postPosition( String position, String location);
	
	List<Position> getPosition( String keyWord, String location );
}
