package com.example.demo.dataservice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.demo.bean.Token;
import com.example.demo.bean.User;
import com.example.demo.database.entity.UserEntity;
import org.junit.jupiter.api.Test;

public class RegistrationServiceTest
{
	
	@Test
	public void testMapping()
	{
		User userSource =
				User.builder().name( "ABC" ).email( "abc@def.com" ).token( Token.builder().setUuid().build() ).build();
		UserEntity userMapped = _mapper.userToEntity( userSource );
		
		assertEquals( userSource.getName(), userMapped.getName());
		assertEquals( userSource.getEmail(), userMapped.getEmail() );
		assertEquals( userSource.getToken().get_uuidString(), userMapped.getToken() );
	}
	
	private final EntityMapper _mapper = RegistrationServiceImpl.getMapper();
}
