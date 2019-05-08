package com.example.Study.dao;

import com.example.Study.bean.Grade;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface GradeDao {
    @Select(value = " SELECT su.* FROM english su WHERE su.studentId = #{studentId} ")
    public List<Grade> findGradeByStudentId(int studentId);

    @Select(value = " SELECT su.* FROM english su WHERE su.id = #{id} ")
    public Grade findGradeByGradeId(int id);

    @Select(value = " SELECT su.* FROM english su WHERE su.studentId = #{id} ")
    public List<Grade> findGradeByUserId(int id);

    @Select(value = " SELECT su.* FROM english su ")
    public List<Grade> findAllGrade();

    @Insert(value = "INSERT english (studentId,studentName,grammar,pronunciation," +
            "vocabulary,writting,listening,reading,speaking,date) VALUES (#{studentId}," +
            "#{studentName},#{grammar},#{pronunciation},#{vocabulary}," +
            "#{writting},#{listening},#{reading},#{speaking},#{date})")
    public void insertGrade(int studentId,String studentName,int grammar,int pronunciation,
                            int vocabulary,int writting,int listening,int reading, int speaking
    ,String date);

    @Update(value = "UPDATE english SET grammar=#{nG}, pronunciation=#{nP}," +
            "vocabulary=#{nV},writting=#{nW},listening=#{nL},reading=#{nR}," +
            "speaking=#{nS} where id=#{id}")
    public void updateGrade(int id,int nG,int nP,int nV,int nW,int nL,int nR, int nS);
}
