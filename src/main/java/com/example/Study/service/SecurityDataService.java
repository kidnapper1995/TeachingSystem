package com.example.Study.service;


import com.example.Study.bean.SPermission;
import com.example.Study.bean.SRole;
import com.example.Study.bean.SUser;
import com.example.Study.dao.SPermissionDao;
import com.example.Study.dao.SRoleDao;
import com.example.Study.dao.SUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Security 数据服务
 *
 * @author Gu
 */
@Service
public class SecurityDataService {
    @Autowired
    private SUserDao sUserDao;
    @Autowired
    private SRoleDao sRoleDao;
    @Autowired
    private SPermissionDao sPermissionDao;

    public SUser findSUserByName(String name) {
        return sUserDao.findSUserByName(name);
    }



    public boolean addUser(String userName,String realName,String password){
        boolean flag=false;
        flag=sUserDao.addUser(userName,realName,password);
        sRoleDao.addUser(userName);
        return flag;
    }

    public List<SRole> findSRoleListBySUserId(int sUserId) {
        return sRoleDao.findSRoleListBySUserId(sUserId);
    }

    public List<SRole> findSRoleListBySPermissionUrl(String sPermissionUrl) {
        return sRoleDao.findSRoleListBySPermissionUrl(sPermissionUrl);
    }

    public List<SPermission> findSPermissionListBySUserId(int sUserId) {
        return sPermissionDao.findSPermissionListBySUserId(sUserId);
    }

    public List<SPermission> findSPermissionListBySPermissionUrl(String sPermissionUrl) {
        return sPermissionDao.findSPermissionListBySPermissionUrl(sPermissionUrl);
    }
}