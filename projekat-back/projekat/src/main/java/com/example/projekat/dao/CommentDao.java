package com.example.projekat.dao;

import com.example.projekat.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface CommentDao extends JpaRepository<Comment, Integer> {
}