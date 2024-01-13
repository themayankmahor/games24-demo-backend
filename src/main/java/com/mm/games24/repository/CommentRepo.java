package com.mm.games24.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mm.games24.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer>{

}
