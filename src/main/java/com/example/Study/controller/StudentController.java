package com.example.Study.controller;

import com.example.Study.dao.SUserDao;
import com.example.Study.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class StudentController {
    @Autowired
    GradeService gradeService;

    @Autowired
    SUserDao sUserDao;





    @RequestMapping("/toCheck")
    public String check(Model model){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        int id=sUserDao.findSUserByName(userDetails.getUsername()).getId();
        ArrayList<Map.Entry<Integer, Integer>> list=gradeService.compareSubjects(id);
        String[] subjects={"grammar","pronunciation","vocabulary","writting","listening","reading","speaking"};
        if (list==null){
            model.addAttribute("realName",gradeService.findSUserById(id).getRealName());
            model.addAttribute("weak",null);
            model.addAttribute("good",null);
        }
        else {
            model.addAttribute("realName",gradeService.findSUserById(id).getRealName());
            model.addAttribute("weak",subjects[list.get(0).getKey()]);
            model.addAttribute("good",subjects[list.get(6).getKey()]);
        }

        model.addAttribute("last",gradeService.compareWithOthers(id));
        model.addAttribute("previous",gradeService.compareWithOthersPrevious(id));
        model.addAttribute("compareP",gradeService.compareWithPrevious(id));

        return "user/showCheck";
    }
}
