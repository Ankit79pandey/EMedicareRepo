package org.medicare.services;

import org.medicare.dao.UserRepository;
import org.medicare.entity.AuthenticationStatus;
import org.medicare.entity.User;
import org.springframework.stereotype.Service;
@Service
public class UserServiceImpl implements UserService {
private UserRepository userRepository;

public UserServiceImpl(UserRepository userRepository) {
	super();
	this.userRepository = userRepository;
	
}
	@Override
	public void insertUser(User user) {
		userRepository.save(user);
		
	}

	@Override
	public AuthenticationStatus getStatus(String username, String password) {
		AuthenticationStatus status;
		User user=userRepository.findByUsernameAndPassword(username, password);
		if(user!=null) {
			status=new AuthenticationStatus(user.getUsername(), user.getPassword(), true);
		}
		else
		{
			status=new AuthenticationStatus(null,null,false);
		}
		return status;
		
	}

}
