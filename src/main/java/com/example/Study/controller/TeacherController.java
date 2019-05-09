package com.example.Study.controller;

import com.example.Study.bean.SUser;
import com.example.Study.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class TeacherController {
    @Autowired
    GradeService gradeService;


    @RequestMapping("/check")
    public String check(Model model){
        model.addAttribute("users",gradeService.findAllUser());
        return "/admin/check";
    }


    @RequestMapping("/toCheck")
    public String check(Model model,int id){
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

        return "admin/showCheck";
    }

    @RequestMapping("/add")
    public String add(Model model){
        model.addAttribute("users",gradeService.findAllUser());
        return "admin/showAdd";
    }

    @RequestMapping("/toAdd")
    public String toAdd(Model model,int id){
        model.addAttribute("grades",gradeService.findGradeByUserId(id));
        model.addAttribute("studentId",id);
        model.addAttribute("studentName",gradeService.findSUserById(id).getRealName());
        return "admin/toAdd";
    }

    @RequestMapping("/finishAdd")
    public String finishAdd(Model model,int id,String studentName,String newDate,String newGrammar,String newPronunciation,
                            String newVocabulary,String newWritting,String newListening,String newReading,String newSpeaking){
        gradeService.add(id,studentName,newDate,newGrammar,newPronunciation,newVocabulary,newWritting,newListening,newReading,newSpeaking);

        model.addAttribute("grades",gradeService.findGradeByUserId(id));
        model.addAttribute("studentId",id);
        model.addAttribute("studentName",gradeService.findSUserById(id).getRealName());
        return "admin/toAdd";
    }



    @RequestMapping("/edit")
    public String edit(Model model){
        model.addAttribute("grades",gradeService.findAllGrade());
        return "admin/edit";
    }

    @RequestMapping(value = "/toEdit",method = RequestMethod.GET)
    public String toEdit(Model model,int id) {
        model.addAttribute("grade",gradeService.findGradeByGradeId(id));
        return "admin/toEdit";
    }

    @RequestMapping(value = "/finishEdit",method = RequestMethod.POST)
    public String finishEdit(Model model,int id,String newGrammar,String newPronunciation,
                             String newVocabulary,String newWritting,String newListening,String newReading,String newSpeaking) {
        gradeService.edit(id, newGrammar, newPronunciation, newVocabulary, newWritting, newListening, newReading, newSpeaking);
        model.addAttribute("grades",gradeService.findAllGrade());
        return "admin/edit";
    }
}
