package com.example.demo.dataservice;

import com.example.demo.bean.User;
import com.example.demo.database.entity.UserEntity;
import com.example.demo.database.repository.UserRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceImpl implements RegistrationService
{
	public RegistrationServiceImpl( UserRepository userRepository )
	{
		_userRepository = userRepository;
		_entityMapper = getMapper();
	}
	
	@Override
	public String registerUser( User user )
	{
		checkUniqueUserData( user );
		
		UserEntity userEntity = _entityMapper.userToEntity( user );
		
		_userRepository.save( userEntity );
		
		return _userRepository.findById( userEntity.getId() ).get().getToken();
	}
	
	@Override
	public boolean checkTokenExists( String tokenString ) throws IllegalArgumentException
	{
		return !_userRepository.findByToken( tokenString ).isEmpty();
	}
	
	private void checkUniqueUserData ( User user )
	{
		if ( !_userRepository.findByName( user.getName() ).isEmpty())
		{
			throw new IllegalArgumentException( "username already exists : " + user.getName() );
		}
		if ( !_userRepository.findByEmail( user.getEmail() ).isEmpty())
		{
			throw new IllegalArgumentException( "email already exists : " + user.getEmail() );
		}
	}
	
	protected static EntityMapper getMapper()
	{
		return Mappers.getMapper( EntityMapper.class );
	}
	
	private final UserRepository _userRepository;
	
	protected static EntityMapper _entityMapper;
	
}
