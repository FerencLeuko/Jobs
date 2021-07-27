package com.example.demo.database.datasource;

import java.util.List;

import com.example.demo.bean.Position;
import com.example.demo.database.datasource.DataResource;

class LocalDataBaseResource implements DataResource
{
	@Override
	public String postPosition( String position, String location )
	{
		return null;
	}
	
	@Override
	public List<Position> getPosition( String keyWord, String location )
	{
		return null;
	}
}
