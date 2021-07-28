package com.hackertest.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import lombok.Data;

//the key expires (Redis automatically delete it) after 5 minutes
@RedisHash(value="SigninFailure", timeToLive=300L)
@Data
public class SigninFailure {
	
	@Id
	private String id;
	
	@Indexed
	private String ip;
	private long timestamp;
	
	public SigninFailure(String ip, long timestamp) {
		super();
		this.ip = ip;
		this.timestamp = timestamp;
	}
	
	
}
