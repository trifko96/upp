package com.example.demonc.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demonc.model.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long>{
	Author findById(long id);
}
