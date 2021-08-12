package com.example.demo.database.repository;

import java.util.Collection;
import java.util.List;

import com.example.demo.database.entity.JobEntity;
import com.example.demo.database.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends CrudRepository<JobEntity, Integer>
{
	List<JobEntity> findByPositionContainingIgnoreCase( String position );
	
	List<JobEntity> findByLocationContainingIgnoreCase( String location );
	
	List<JobEntity> findByUrl( String url );
	
	@Query(	value = "SELECT * FROM job j WHERE j.position LIKE %:position% AND j.location LIKE %:location%",
			nativeQuery = true)
	List<JobEntity> findJobsByPositionAndLocation(
			@Param("position") String position, @Param("location") String location
	);
}
