package com.abc.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.userservice.dao.UserDao;
import com.abc.userservice.entity.User;
import com.abc.userservice.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDAO;

    @Override
    public User addUser(User user) {
        return userDAO.save(user);
    }

    @Override
    public User updateUser(User user) {
        if (!userDAO.existsById(user.getUserId())) {
            throw new ResourceNotFoundException("User not found with id: " + user.getUserId());
        }
        return userDAO.save(user);
    }
    @Override
	public void removeUser(User user) {
		Optional<User> optionalproduct = userDAO.findById(user.getUserId());
		if (optionalproduct.isEmpty()) {
			throw new ResourceNotFoundException("User Not found with id "+user.getUserId());

		}
		User users =optionalproduct.get();
		userDAO.delete(users);
		
	}
    @Override
    public List<User> getAllUsers() {
        return userDAO.findAll();
    }

    @Override
    public User getUserById(int userId) {
        return userDAO.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
    }
}
