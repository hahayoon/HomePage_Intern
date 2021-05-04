package com.neighbor.site.service;



import com.neighbor.site.model.Project;
import com.neighbor.site.model.User;
import com.neighbor.site.repository.ProjectRepository;
import com.neighbor.site.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


// 스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌. IoC를 해준다.
@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Transactional
    public void write(Project project ,User user)  //타이틀 컨텐트
    {
        project.setCount(0);
        project.setUser(user);
        project.setWriter(user.getName());
        System.out.println("user= " + user);
        projectRepository.save(project);
    }


    public Page<Project> list(Pageable pageable){
        return projectRepository.findAll(pageable);
    }


    @Transactional
    public Project detail(int id){

        Project project = projectRepository.findById(id)
               .orElseThrow(()->{
                   return new IllegalArgumentException("글 상세보기 페이지를 찾을수 없습니다. ");
               });

        int num = project.getCount();
        num +=1;
        project.setCount(num);

        return project;
    }

}

