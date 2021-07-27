package com.example.demo.dataservice;

import com.example.demo.bean.User;
import com.example.demo.database.entity.UserEntity;
import org.mapstruct.Mapping;

@org.mapstruct.Mapper
public interface EntityMapper
{
	@Mapping( source = "token._uuidString", target = "token" )
	UserEntity userToEntity( User user );
}
