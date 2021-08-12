package com.example.demo.database.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table( name ="job" )
public class JobEntity
{
	@Id
	@SequenceGenerator( name = "job_id_seq", sequenceName = "job_id_seq" )
	@GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "job_id_seq" )
	Integer id;
	
	String position;
	
	String location;
	
	String url;
}
