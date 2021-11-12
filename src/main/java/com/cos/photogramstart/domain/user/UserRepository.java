package com.cos.photogramstart.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

// 어노테이션 없어도 IoC 자동등록 (JPA리포지토리 상속받았기때문)
public interface UserRepository extends JpaRepository<User, Integer>{
	// JPA query method
	User findByUsername(String username);
	
}
