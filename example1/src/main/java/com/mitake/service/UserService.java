package com.mitake.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mitake.component.Bok508CRQBody;
import com.mitake.dao.UserDAO;
import com.mitake.entity.User;

@Service()
@Transactional()
public class UserService {
	@Autowired
	private UserDAO userDAO;
	
	public void create(Bok508CRQBody bok508crqbody){
		//這邊不可以私吞Exception，會造成無法RollBack
		User user = new User();
		user.setAcct(bok508crqbody.getUnifiedNo());
		userDAO.save(user);
	}
}
