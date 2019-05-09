package com.example.Study.service;

import com.example.Study.bean.Grade;
import com.example.Study.bean.SUser;
import com.example.Study.dao.GradeDao;
import com.example.Study.dao.SUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public ArrayList<Map.Entry<Integer, Integer>> compareSubjects(int id){
        List<Grade> l=findGradeByUserId(id);

        if (l==null||l.isEmpty())
            return null;
        Grade last=l.get(l.size()-1);
        LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>();
        map.put(0,last.getGrammar());
        map.put(1,last.getPronunciation());
        map.put(2,last.getVocabulary());
        map.put(3,last.getWritting());
        map.put(4,last.getListening());
        map.put(5,last.getReading());
        map.put(6,last.getSpeaking());

        ArrayList<Map.Entry<Integer, Integer>> list =
                new ArrayList<Map.Entry<Integer, Integer>>(map.entrySet());

//从小到大排序（从大到小将o1与o2交换即可）
        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {

            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return ((o1.getValue() - o2.getValue() == 0) ?
                        0: (o1.getValue() - o2.getValue() > 0) ? 1: -1);
            }

        });


        return list;
    }

    public List<Grade> findGradeByDate(String date){
        return gradeDao.findGradeByDate(date);
    }

    public String compareWithOthers(int id){
        List<Grade> l=findGradeByUserId(id);
        long sumOfAllGrade=0;

        if (l==null||l.size()==0)
            return null;
        Grade last=l.get(l.size()-1);
        int sum=0;
        sum+=last.getGrammar();
        sum+=last.getPronunciation();
        sum+=last.getVocabulary();
        sum+=last.getWritting();
        sum+=last.getListening();
        sum+=last.getReading();
        sum+=last.getSpeaking();
        List<Grade> allGrade=findGradeByDate(last.getDate());
        for (int i = 0; i < allGrade.size(); i++) {
            sumOfAllGrade+=allGrade.get(i).getGrammar();
            sumOfAllGrade+=allGrade.get(i).getPronunciation();
            sumOfAllGrade+=allGrade.get(i).getVocabulary();
            sumOfAllGrade+=allGrade.get(i).getWritting();
            sumOfAllGrade+=allGrade.get(i).getListening();
            sumOfAllGrade+=allGrade.get(i).getReading();
            sumOfAllGrade+=allGrade.get(i).getSpeaking();
        }
        long avgAll=sumOfAllGrade/allGrade.size();
        if (avgAll-sum>50)
            return "最近一次成绩远低于平均分";
        if(avgAll-sum<=50&&avgAll-sum>=20)
            return "最近一次成绩低于平均分";
        if (avgAll-sum<20&&avgAll-sum>-20)
            return "最近一次成绩处在班级中游水平";
        if (avgAll-sum>=-50&&avgAll-sum<=-20)
            return "最近一次成绩高于平均分";
        else
            return "最近一次成绩远高于平均分";

    }

    public String compareWithOthersPrevious(int id){
        List<Grade> l=findGradeByUserId(id);
        long sumOfAllGrade=0;

        if (l==null||l.size()==0)
            return null;
        Grade last;
        int allGradeSize=0;
        int sum=0;
        for(int k=0;k<l.size();k++)
        {
            last=l.get(k);

            sum+=last.getGrammar();
            sum+=last.getPronunciation();
            sum+=last.getVocabulary();
            sum+=last.getWritting();
            sum+=last.getListening();
            sum+=last.getReading();
            sum+=last.getSpeaking();
            List<Grade> allGrade=findGradeByDate(last.getDate());
            for (int i = 0; i < allGrade.size(); i++) {
                sumOfAllGrade+=allGrade.get(i).getGrammar();
                sumOfAllGrade+=allGrade.get(i).getPronunciation();
                sumOfAllGrade+=allGrade.get(i).getVocabulary();
                sumOfAllGrade+=allGrade.get(i).getWritting();
                sumOfAllGrade+=allGrade.get(i).getListening();
                sumOfAllGrade+=allGrade.get(i).getReading();
                sumOfAllGrade+=allGrade.get(i).getSpeaking();
            }
            allGradeSize+=allGrade.size();
        }


        long avgAll=sumOfAllGrade/allGradeSize;
        sum=sum/l.size();
        if (avgAll-sum>50)
            return "历史成绩远低于平均分";
        if(avgAll-sum<=50&&avgAll-sum>=20)
            return "历史成绩低于平均分";
        if (avgAll-sum<20&&avgAll-sum>-20)
            return "历史成绩处在班级中游水平";
        if (avgAll-sum>=-50&&avgAll-sum<=-20)
            return "历史成绩高于平均分";
        else
            return "历史成绩远高于平均分";

    }

    public String compareWithPrevious(int id){
        List<Grade> l=findGradeByUserId(id);
        long sumOfAllGrade=0;

        if (l==null||l.size()<2)
            return null;
        Grade last=l.get(l.size()-1);
        Grade previous=l.get(l.size()-2);
        int sum=0;
        sum+=last.getGrammar();
        sum+=last.getPronunciation();
        sum+=last.getVocabulary();
        sum+=last.getWritting();
        sum+=last.getListening();
        sum+=last.getReading();
        sum+=last.getSpeaking();
        int sum2=0;
        sum2+=previous.getGrammar();
        sum2+=previous.getPronunciation();
        sum2+=previous.getVocabulary();
        sum2+=previous.getWritting();
        sum2+=previous.getListening();
        sum2+=previous.getReading();
        sum2+=previous.getSpeaking();
        if (sum-sum2>50)
            return "成绩较上次大幅度提升";
        if (sum-sum2>20)
            return "成绩较上次小幅度提升";
        if (sum-sum2<=20&&sum-sum2>=-20)
            return "成绩较上次基本持平";
        if (sum-sum2<-50)
            return "成绩较上次大幅度下降";
        else
            return "成绩较上次小幅度下降";

    }

}
