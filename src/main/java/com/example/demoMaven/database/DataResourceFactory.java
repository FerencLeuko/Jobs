package com.example.demoMaven.database;

import java.util.List;

public class DataResourceFactory
{
	public static List<DataResource> getDataResources()
	{
		if ( _dataResources == null)
		{
			initDataResources();
		}
		return _dataResources;
	}
	
	private static void initDataResources()
	{
		_dataResources.add( new GitDataResource() );
		_dataResources.add( new LocalDataBaseResource() );
	}
	
	private static List<DataResource> _dataResources;
	
}
