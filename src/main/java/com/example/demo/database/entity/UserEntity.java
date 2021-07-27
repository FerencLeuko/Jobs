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
@Table ( name ="user" )
public class UserEntity
{
	@Id
	@SequenceGenerator( name = "user_id_seq", sequenceName = "user_id_seq" )
	@GeneratedValue ( strategy = GenerationType.SEQUENCE, generator = "user_id_seq" )
	Integer id;
	
    @Column( name = "user_name" )
	String name;
	
	String email;
	
	String token;
}
