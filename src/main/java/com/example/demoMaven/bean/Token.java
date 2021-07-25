package com.example.demoMaven.bean;

import java.util.UUID;

import lombok.Value;

@Value
public class Token
{
	public static UUID convertStringToUuid( String uuidString )
	{
		return UUID.fromString( uuidString );
	}
	
	private final UUID _uuid;
	
	private final String _uuidString;
	
	private Token( UUID _uuid, String _uuidString )
	{
		this._uuid = _uuid;
		this._uuidString = _uuidString;
	}
	
	public static TokenBuilder builder()
	{
		return new TokenBuilder();
	}
	
	public static class TokenBuilder
	{
		private UUID _uuid;
		private String _uuidString;
		
		public TokenBuilder()
		{
		}
		
		public TokenBuilder setUuid( )
		{
			this._uuid = UUID.randomUUID();
			this._uuidString = _uuid.toString();
			return this;
		}
		
		public Token build()
		{
			return new Token( _uuid, _uuidString );
		}
	}
}
