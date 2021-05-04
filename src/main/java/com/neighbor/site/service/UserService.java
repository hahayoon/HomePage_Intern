package com.neighbor.site.service;



import com.neighbor.site.model.User;
import com.neighbor.site.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


// 스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌. IoC를 해준다.
@Service
public class UserService {
    @Autowired
    private BCryptPasswordEncoder encoder;


    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void joink(User user) {
 //비밀번호 암호화
//        String rawPassword= user.getPassword();
//        String encPassword = encoder.encode(rawPassword);
//        user.setPassword(encPassword);


        userRepository.save(user);
    }

    @Transactional(readOnly = true) //select 할때 트렌젝션 시작 , 정합성 유지시킬수 있다.
    public User logink(User user){
        return userRepository.findByUsernameAndPassword(user.getUsername(),user.getPassword());

    }
}

