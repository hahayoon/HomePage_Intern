package com.neighbor.site.api;

import com.neighbor.site.dto.ResponseDto;
import com.neighbor.site.model.Project;
import com.neighbor.site.model.RoleType;
import com.neighbor.site.model.User;
import com.neighbor.site.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class BoardApiController {

    @Autowired
    private ProjectService projectService;


    @PostMapping("/boardproc")
    public ResponseDto<Integer> save(@RequestBody Project project , HttpSession session) { // username, password, email ,name
        System.out.println("boardApiController : save 호출됨");
        System.out.println("내용은 ? " + project.getContent());
        // 실제로 DB에 insert를 하고 아래에서 return이 되면 되요.ㄴ



       User user = (User) session.getAttribute("principal");

         projectService.write(project , user);
        return new ResponseDto<Integer>(HttpStatus.OK , 1); // 자바오브젝트를 JSON으로 변환해서 리턴 (Jackson)
    }

}
