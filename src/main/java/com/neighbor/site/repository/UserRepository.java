package com.neighbor.site.repository;



import com.neighbor.site.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


// DAO
// 자동으로 bean등록이 된다.
// @Repository // 생략 가능하다.
public interface UserRepository extends JpaRepository<User, Integer>{
//jpa naming 전략
    //select * from  user where username = ? and password = ?
    User findByUsernameAndPassword(String username , String password);
    Optional<User> findByUsername(String username);


}