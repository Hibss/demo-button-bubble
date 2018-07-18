package com.Demonor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Demonor.dao.UserRedisDao;
import com.Demonor.model.User;

@Service
public class UserService {
	
	@Autowired
	UserRedisDao userRedisDao;
	
	public List<User> getAll(){
		 List<User> users = userRedisDao.getAll();
		 return users;
	}
	
	public void save(User user){
		userRedisDao.put(user.getUsername(), user, -1);
	}
	
	public User get(String key){
		return userRedisDao.get(key);
	}
	
	public boolean remove(String key){
		 userRedisDao.remove(key);
		 boolean IsRemove = false;
		 if(get(key) == null){
			 IsRemove = true;
		 }else{
			 IsRemove = false;
		 }
		 return IsRemove;
	}
	
	
	public boolean isUser(User user){
		boolean isUser = true;
		User u = userRedisDao.get(user.getUsername());
		if(u == null){
			isUser = false;
		}else{
			if(!u.getPassword().trim().equals(user.getPassword().trim())){
				isUser = false;
			}else{
				isUser = true;
			}
		}
		return isUser;
	}

}
