package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class MyController {
	
	@Autowired
	UserRepo userRepo;
	@Autowired
	TaskRepo taskRepo;
	@RequestMapping("register{name}and{userName}and{userPassword}and{securityCode}")
	public boolean register(@PathVariable String name,@PathVariable String userName,@PathVariable String userPassword,@PathVariable String securityCode)
	{
		try {
			int count = userRepo.countByUsername(userName);
			if (count > 0)
				return false;
			User user=new User();
			user.setId(0);
			user.setName(name);
			user.setPassword(userPassword);
			user.setUsername(userName);
			user.setSecurityCode(securityCode);
			userRepo.save(user);
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	@RequestMapping("login{username}and{password}")
	public User login(@PathVariable String username,@PathVariable String password)
	{
		try {
			
			int count=userRepo.countByUsername(username);
			if(count!=1)
			return null;
			User user=userRepo.findByusername(username);
			if(!user.getPassword().equals(password))
			user.setPassword(null);
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@RequestMapping("checkCode{username}and{securityCode}")
	public boolean securitycheck(@PathVariable String username,@PathVariable String securityCode)
	{
		try
		{
			User user=userRepo.findByusername(username);
			if(user.getSecurityCode().equals(securityCode)) 
			{
				return true;
			}
			return false;
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	@RequestMapping("changepassword{username}and{confrimpassword}")
	public boolean forgetPassword(@PathVariable String username,@PathVariable String confrimpassword)
	{
		try
		{		
		User u=userRepo.findByusername(username);
		u.setPassword(confrimpassword);
		userRepo.save(u);
		return true;
		}
		catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		
	}
	
	@RequestMapping("add{id}and{details}")
	public Task add(@PathVariable int id,@PathVariable String details){
		try {
			
			 
			Task task=new Task(0,details,0);
		    taskRepo.save(task);
		    User user= userRepo.findByid(id);
			user.getTask().add(task);
		    userRepo.save(user);
			return task;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@RequestMapping("delete{userId}and{taskId}")
	public boolean newdelete(@PathVariable int userId,@PathVariable int taskId)
	{ 
		try {
		User user=userRepo.findById(userId).get();
		List<Task> tasks=user.getTask();
		for(Task task:tasks)
		{
			if(task.getId()==taskId)
			{
				tasks.remove(task);
			
				break;
			}
		}
		userRepo.save(user);
	    return true;
	 
	  }
	    catch(Exception e) {
		e.printStackTrace();
		return false;
	}
  }
	
	@RequestMapping("changeStatus{taskId}and{newStatus}")
	public boolean changestatus(@PathVariable int taskId, @PathVariable int newStatus)
	{
		try {
			Task task=taskRepo.findById(taskId).get();
			task.setStatus(newStatus);
			taskRepo.save(task);
			return true;
			} catch (Exception e) {
	       e.printStackTrace();
	       return false;
		}
	}
}