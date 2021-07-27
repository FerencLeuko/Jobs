package com.example.demo.dataservice;

import java.util.List;

import com.example.demo.bean.Position;
import org.springframework.stereotype.Service;

@Service
public class JobServiceImpl implements JobService
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
