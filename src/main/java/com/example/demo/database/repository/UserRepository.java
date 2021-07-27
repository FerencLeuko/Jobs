package com.example.demo.database.repository;

import java.util.List;

import com.example.demo.database.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer>
{
	List<UserEntity> findByToken( String token );
	
	List<UserEntity> findByName( String name );
	
	List<UserEntity> findByEmail( String email );
	
	List<UserEntity> findByNameOrEmailOrToken( String name, String email, String token );
}
