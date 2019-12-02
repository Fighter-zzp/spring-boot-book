package com.zzp.spring.boot.user.repository;


import com.zzp.spring.boot.user.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

public interface MessageRepository extends JpaRepository<Message,Long> {

}
