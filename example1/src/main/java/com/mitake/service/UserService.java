package com.mitake.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mitake.dao.UserDAO;

@Service()
@Transactional()
public class UserService {
	@Autowired
	private UserDAO userDAO;
	
	
}
