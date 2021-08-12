package com.example.demo.bean;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Job
{
	private String position;
	
	private String location;
	
	private String url;
}
