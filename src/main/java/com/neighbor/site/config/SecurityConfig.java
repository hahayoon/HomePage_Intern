package com.neighbor.site.config;

import com.neighbor.site.config.auth.PrincipalDetail;
import com.neighbor.site.config.auth.PrincipalDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.hibernate.criterion.Restrictions.and;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PrincipalDetailService principalDetailService;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());  //STATIC 한거는 필터적용하지 말아라
    }



    @Bean  //Ioc가 된다. ->BCryptPasswordEncoder 값을 스프링에서 관리  리턴하는 값을 스프링이 관리해준다.
    public BCryptPasswordEncoder encoderPWD() {   //암호화 해준다. (고정길이의 문자열로 해쉬화 시켜준다. )

        return new BCryptPasswordEncoder();
    }


    //시큐리티가 대신 로그인해주면서 password 가로채기를 하는데
    //해당패스워드가 어떤걸로 해수가 되었는지 알아야
    //같은 해쉬로 암호화해서 db에 있는 해쉬랑 비교할수 있다.
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(principalDetailService).passwordEncoder(encoderPWD());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() //csrf 토큰 비활성화
                .authorizeRequests()
                .antMatchers("/", "/auth/**" , "/js/**", "/css/**", "/image/**" , "/dummy/**" ,"/**")  //해당페이지 접근시
                .permitAll()  //누구나 들어올수 있다.
                .anyRequest()  //이게 아닌 다른요구는
                .authenticated()  //인증이 되어야함
                .and()
                .formLogin()
                .loginPage("/auth/loginForm")  //자동으로 인증이 필요한 로그인페이지로 시작됨
               .loginProcessingUrl("/nono/loginProc") //스프링 시큐리티가 해당주소로 요청오는 로그인을 가로채서 대신 로그인 해준다.
                .defaultSuccessUrl("/");  //정상적으로 요청이 완료되면 / 로 간다.

    }


}

