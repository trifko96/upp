package com.example.demonc.services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demonc.model.User;
import com.example.demonc.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public void save(User user) {
		// TODO Auto-generated method stub
		System.out.println("Usao u saveUser");
		userRepository.save(user);
	}
	
	@Override
	public void updateUser(User user) {
		userRepository.updateUser(user.getUsername());
	}

	@Override
	public User findUserByMail(String mail) {
		// TODO Auto-generated method stub
		System.out.println("Usao je u findUserBymail");
		System.out.println("Mail je dobio "+mail);
		return userRepository.findOneByMail(mail);
	}

	@Override
	public User findUserByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepository.findOneByUsername(username);
	}

	@Override
	public void deleteUser(User user) {
		// TODO Auto-generated method stub
		 userRepository.delete(user);
	}

	public String encrypt(String sifra) throws NoSuchAlgorithmException {
		System.out.println("Usao da enkriptuje sifru " + sifra );
		MessageDigest md = MessageDigest.getInstance("SHA-256"); 
		  
		 //pretvori sifru  u bajte
         byte[] messageDigest = md.digest(sifra.getBytes()); 
         
         StringBuilder sb = new StringBuilder();
         for (byte b : messageDigest) {
             sb.append(String.format("%02x", b));
         }
        String povratna=sb.toString();
        System.out.println("Rezultat enkripcije je "+povratna);
    	
        return povratna;
	}

	@Override
	public List<User> findAllByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepository.findAllByUsername(username);
	}


}
