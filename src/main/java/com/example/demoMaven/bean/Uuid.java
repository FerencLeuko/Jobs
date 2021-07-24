package com.example.demoMaven.bean;

import java.util.UUID;

import lombok.Value;

@Value
public class Uuid
{
	public static UUID convertStringToUuid( String uuidString )
	{
		return UUID.fromString( uuidString );
	}
	
	private final UUID _uuid;
	
	private final String _uuidString;
	
	private Uuid( UUID _uuid, String _uuidString )
	{
		this._uuid = _uuid;
		this._uuidString = _uuidString;
	}
	
	public static UuidBuilder builder()
	{
		return new UuidBuilder();
	}
	
	public static class UuidBuilder
	{
		private UUID _uuid;
		private String _uuidString;
		
		public UuidBuilder()
		{
		}
		
		public UuidBuilder setUuid( )
		{
			this._uuid = UUID.randomUUID();
			this._uuidString = _uuid.toString();
			return this;
		}
		
		public Uuid build()
		{
			return new Uuid( _uuid, _uuidString );
		}
	}
}
