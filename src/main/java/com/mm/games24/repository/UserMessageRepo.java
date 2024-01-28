package com.mm.games24.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mm.games24.entities.UserMessage;

public interface UserMessageRepo extends JpaRepository<UserMessage, Integer>{

}
