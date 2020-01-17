package com.example.demonc.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demonc.model.User;

@Repository
public interface UserRepository  extends JpaRepository<User, Long>{
	User findOneById(long id);
	User findOneByMail(String mail);
	User findOneByUsername(String username);

	@Transactional
	@Modifying
	@Query("update User u set active = true where u.username = ?1")
	void updateUser(String username);
	List<User> findAllByUsername(String username);
}
