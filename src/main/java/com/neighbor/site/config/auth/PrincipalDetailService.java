package com.neighbor.site.config.auth;

import com.neighbor.site.model.User;
import com.neighbor.site.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service  //bean 등록
public class PrincipalDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    //username 이랑 password  변수 2개를 가로챈다.
    //password 는 알아서 함 ,
    //user name 이 db에 있는지만 확인해 주면 된다.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User principal = userRepository.findByUsername(username)
                .orElseThrow(()->{
                   return  new UsernameNotFoundException("해당 사용자를 찾을수 없습니다. " + username);
                });
        return new PrincipalDetail(principal);  //시큐리티 세션에 user 정보가 저장이된다.
    }
}
