package com.neighbor.site.account;

import com.neighbor.site.model.Project;
import com.neighbor.site.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AccountController {
    @GetMapping("/")
    public String mainForm(Model model){
        model.addAttribute("mainPage",new MainPage());

        return "index";
    }


    @GetMapping("/home")
    public  String home(){

        return "static/html/home";

    }

    @GetMapping("/introduce")
    public  String introduce(){

        return "static/html/introduce";

    }

    @GetMapping("/notice")
    public  String notice(){

        return "static/html/notice";

    }

    @GetMapping("/board")
    public  String board(){

        return "static/html/board";

    }



    @GetMapping("/mypage")
    public  String mypage(){

        return "static/html/mypage";

    }

    @GetMapping("/myboard")
    public  String myboard(){

        return "static/html/myboard";

    }




    @GetMapping("/auth/loginForm")
    public  String login(){

        return "static/html/login";

    }


    @GetMapping("/auth/joinForm")
    public  String join(){

        return "static/html/join";

    }


    @GetMapping("/change")
    public  String change(){

        return "static/html/change";

    }



    @GetMapping("/projectManage")
    public  String projectManage(){

        return "static/html/projectManage";

    }

    @Autowired
    private ProjectService projectService;

    @GetMapping("/boardCompany")
    public  String  boardCompany(Model model ,  @PageableDefault(size=10 , sort ="id" , direction = Sort.Direction.DESC)Pageable pageable){
        model.addAttribute("projects" ,projectService.list(pageable));
        return "static/html/boardCompany";

    }

    @GetMapping("/request")
    public  String  request(){

        return "static/html/request";

    }

    @GetMapping("/circles")
    public  String  circles(){

        return "static/html/circle";

    }


    @GetMapping("/writeCompany")
    public  String  writeCompany(){

        return "static/html/writeBoardCompany";

    }

    @GetMapping("/project/{id}")
    public  String  findById(@PathVariable int id , Model model){


        model.addAttribute("project",  projectService.detail(id));

        return "static/html/boardDetail";

    }

}
