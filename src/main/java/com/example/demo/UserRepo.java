package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> 
{



	User findByusername(String username);

	int countByUsername(String userName);

	User findByid(int id);


}