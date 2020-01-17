package com.example.demonc.services;

import java.util.List;
import org.springframework.stereotype.Service;

import com.example.demonc.model.User;

@Service
public interface UserService {
	public List<User> getAll();
	public void save(User user);
	public void updateUser(User user);
	public User findUserByMail(String mail);
	public User findUserByUsername(String username);
	public List<User> findAllByUsername(String username);
	public void deleteUser(User user);
}
