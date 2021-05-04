package com.neighbor.site.config.auth;

import com.neighbor.site.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

//스프링 시큐리티가 로그인 요청을 가로채서 로그인하고 완료되면 userdetail 타입의 오브젝트를 스프링 시큐리티의 고유한 세션 저장소에 저장을 해준다.

public class PrincipalDetail implements UserDetails {  //추상메소드 오버라이드
    private User user;

    public PrincipalDetail(User user){
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {  //계정 권한 목록을 리턴한다,
        Collection<GrantedAuthority> collectors = new ArrayList<>();
        collectors.add(()->{return "ROLE_"+user.getRole();});
        return collectors;
    }

    @Override
    public String getPassword() {

        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() { //계정이 만료되지 않았는지 리턴
        return true;
    }

    @Override
    public boolean isAccountNonLocked() { //계정잠겨있는지
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() { //비번만료
        return true;
    }

    @Override
    public boolean isEnabled() {  //계정 활성화
        return true;
    }
}
