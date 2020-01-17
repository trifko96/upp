package com.example.demonc.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demonc.model.Editor;

@Repository
public interface EditorRepository extends JpaRepository<Editor, Long>{

}
