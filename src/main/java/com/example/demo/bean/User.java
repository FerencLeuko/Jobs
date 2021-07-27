package com.example.demo.bean;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class User
{
	private String name;
	
	private String email;
	
	private Token token;
}
