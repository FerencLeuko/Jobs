package com.example.demo.database.entity;

import com.example.demo.bean.Job;
import com.example.demo.bean.User;
import org.mapstruct.Mapping;

@org.mapstruct.Mapper
public interface EntityMapper
{
	@Mapping( source = "token._uuidString", target = "token" )
	UserEntity userToEntity( User user );
	
	JobEntity jobToEntity ( Job job );
	
	Job entityToJob ( JobEntity jobEntity );
}
