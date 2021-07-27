package com.example.demo.database.datasource;

import java.util.List;

import com.example.demo.bean.Position;

public interface DataResource
{
	String postPosition( String position, String location);
	
	List<Position> getPosition( String keyWord, String location );
}
