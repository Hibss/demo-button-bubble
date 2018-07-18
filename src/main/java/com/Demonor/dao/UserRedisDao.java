package com.Demonor.dao;

import org.springframework.stereotype.Repository;

import com.Demonor.model.User;

@Repository
public class UserRedisDao extends IRedisDao<User>{
	 private static final String REDIS_KEY= "com.Demonor.model.User";
	 
	 @SuppressWarnings("static-access")
	 @Override
	 protected String getRedisKey() {
		 return this.REDIS_KEY;
		 }
	 }