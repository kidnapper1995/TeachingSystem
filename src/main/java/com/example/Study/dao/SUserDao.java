package com.example.Study.dao;

import com.example.Study.bean.SUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
 * 用户信息查询
 * @author Gu
 */
@Mapper
public interface SUserDao {
	/**
	 * 根据用户名获取用户
	 *
	 * @param name
	 * @return
	 */
	@Select(value = " SELECT su.* FROM s_user su WHERE su.name = #{name} ")
	public SUser findSUserByName(String name);

	@Select(value = "SELECT su.* FROM s_user su WHERE su.id=#{id}")
	public SUser findSUserById(int id);

	@Select(value = " SELECT su.* FROM s_user su left join s_user_role sur  on su.id=" +
			"sur.fk_user_id where sur.fk_role_id=2")
	public List<SUser> findAllUser();


	@Insert(value=" INSERT s_user (name,realName,password) values (#{userName},#{realName},#{password})")
	public boolean addUser(String userName, String realName, String password);


}