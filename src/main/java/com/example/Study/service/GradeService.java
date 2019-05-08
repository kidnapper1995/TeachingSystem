package com.example.Study.service;

import com.example.Study.bean.Grade;
import com.example.Study.bean.SUser;
import com.example.Study.dao.GradeDao;
import com.example.Study.dao.SUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeService {
    @Autowired
    GradeDao gradeDao;
    @Autowired
    SUserDao sUserDao;

    public List<SUser> findAllUser(){
        return sUserDao.findAllUser();
    }

    public Grade findGradeByGradeId(int id){
        return gradeDao.findGradeByGradeId(id);
    }

    public List<Grade> findGradeByUserId(int id){
        return gradeDao.findGradeByUserId(id);

    }

    public SUser findSUserById(int id){
        return sUserDao.findSUserById(id);
    }

    public List<Grade> findAllGrade(){
        return gradeDao.findAllGrade();
    }

    public void add(int id,String studentName,String date,String newGrammar,String newPronunciation,
                    String newVocabulary,String newWritting,String newListening,String newReading,String newSpeaking){
        int nG,nP,nV,nW,nS,nR,nL;
        nG=Integer.valueOf(newGrammar);
        nP=Integer.valueOf(newPronunciation);
        nV=Integer.valueOf(newVocabulary);
        nW=Integer.valueOf(newWritting);
        nL=Integer.valueOf(newListening);
        nS=Integer.valueOf(newSpeaking);
        nR=Integer.valueOf(newReading);
        gradeDao.insertGrade(id,studentName,nG,nP,nV,nW,nL,nR,nS,date);
    }

    public void edit(int id,String newGrammar,String newPronunciation,String newVocabulary,String newWritting,String newListening,String newReading,String newSpeaking){
        Grade grade=gradeDao.findGradeByGradeId(id);
        int nG,nP,nV,nW,nS,nR,nL;
        if (newGrammar==null)
            nG=grade.getGrammar();
        else
            nG=Integer.valueOf(newGrammar);

        if (newPronunciation==null)
            nP=grade.getPronunciation();
        else
            nP=Integer.valueOf(newPronunciation);

        if (newVocabulary==null)
            nV=grade.getVocabulary();
        else
            nV=Integer.valueOf(newVocabulary);

        if (newWritting==null)
            nW=grade.getWritting();
        else
            nW=Integer.valueOf(newWritting);

        if (newListening==null)
            nL=grade.getListening();
        else
            nL=Integer.valueOf(newListening);

        if (newSpeaking==null)
            nS=grade.getSpeaking();
        else
            nS=Integer.valueOf(newSpeaking);

        if (newReading==null)
            nR=grade.getReading();
        else
            nR=Integer.valueOf(newReading);
        gradeDao.updateGrade(id,nG,nP,nV,nW,nL,nR,nS);

    }
}
