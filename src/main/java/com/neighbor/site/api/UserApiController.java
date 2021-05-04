package com.neighbor.site.api;


import com.neighbor.site.dto.ResponseDto;
import com.neighbor.site.model.RoleType;
import com.neighbor.site.model.User;
import com.neighbor.site.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;


@RestController
    public class UserApiController {

        @Autowired
        private UserService userService;



        @PostMapping("/joinProc")
        public ResponseDto<Integer> save(@RequestBody User user) { // username, password, email ,name
            System.out.println("UserApiController : save 호출됨");
            // 실제로 DB에 insert를 하고 아래에서 return이 되면 되요.
            user.setRole(RoleType.USER);
            userService.joink(user);
            return new ResponseDto<Integer>(HttpStatus.OK , 1); // 자바오브젝트를 JSON으로 변환해서 리턴 (Jackson)
        }


        @PostMapping("/loginProc")
        public ResponseDto<Integer> login (@RequestBody User user , HttpSession session) { // username, password
            System.out.println("UserApiController : login 호출됨");
            // 실제로 DB에 insert를 하고 아래에서 return이 되면 되요.

            User principal =  userService.logink(user);
            System.out.println("principal = " + principal );
            if(principal != null) {
                session.setAttribute("principal", principal);
            }
            return new ResponseDto<Integer>(HttpStatus.OK , 1); // 자바오브젝트를 JSON으로 변환해서 리턴 (Jackson)
        }
    }