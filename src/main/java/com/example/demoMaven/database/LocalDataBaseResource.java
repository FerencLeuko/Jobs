package com.example.demoMaven.database;

import java.util.List;

import com.example.demoMaven.bean.Position;

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
