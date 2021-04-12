package com.flower.service.impl;

import com.flower.entity.Admin;
import com.flower.entity.Flower;
import com.flower.entity.Response;
import com.flower.entity.User;
import com.flower.mapper.AdminMapper;
import com.flower.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Resource
    private AdminMapper adminMapper;


    @Override
    public Admin login(String account, String password) {
        Admin admin=adminMapper.login(account,password);
        return admin;
    }

    @Override
    public List<Flower> flowerList() {
        List<Flower> flowerList=adminMapper.flowerList();
        return flowerList;
    }

    @Override
    public List<Flower> priceList() {
        List<Flower> priceList=adminMapper.priceList();
        return priceList;
    }

    @Override
    public int userCount() {
        int userCount=adminMapper.userCount();
        return userCount;
    }

    @Override
    public int flowerCount() {
        int flowerCount=adminMapper.flowerCount();
        return flowerCount;
    }

    @Override
    public int aWeekUser(){
        int aWeekUser=adminMapper.aWeekUser();
        return aWeekUser;
    }

    @Override
    public List<User> userList() {
        List<User> userList= adminMapper.userList();
        return userList;
    }

    @Override
    public Response deleteUserById(int id) {
        adminMapper.deleteUserById(id);
        if (adminMapper.selectUserNameById(id)!=""){
            return  new Response("200","注销成功");
        }else{
            return new Response("300","注销失败");
        }
    }

    @Override
    public List<User> selectOneUser(String username) {
        List<User> userList=adminMapper.selectOneUser(username);
        return userList;
    }

    @Override
    public List<Admin> adminList() {
        List<Admin> adminList=adminMapper.adminList();
        return adminList;
    }

    @Override
    public List<Admin> selectOneAdmin(String account) {
        List<Admin> adminList=adminMapper.selectOneAdmin(account);
        return adminList;
    }

    @Override
    public Response addAdmin(Admin admin) {
        String passwordIsExist=adminMapper.passwordIsExist(admin.getPassword());
        if (passwordIsExist==null){
            int isAdd=adminMapper.addAdmin(admin);
            if (isAdd==1){
                return new Response("200","添加成功！");
            }else {
                return new Response("300", "添加失败!");
            }
        }else{
            return new Response("300","添加失败，该用户已存在！");
        }
    }

    @Override
    public Response deleteAdminById(int id) {
        int isDelete=adminMapper.deleteAdminById(id);
        if (isDelete==1){
            return new Response("200","注销成功！");
        }else{
            return new Response("200","注销失败！");
        }
    }

    @Override
    public Response changeAdmin(String account, String password, String name) {

        int isUpdate=adminMapper.changeAdmin(account,password,name);
        if (isUpdate==1){
            return new Response("200","更改成功！");
        }else{
            return new Response("300","更改失败！");
        }
    }

    @Override
    public List<Flower> toFlowerControl() {
        List<Flower> flowerList=adminMapper.toFlowerControl();
        return flowerList;
    }

    @Override
    public Response deleteFlowerById(int id) {
        int isDelete=adminMapper.deleteFlowerById(id);
        System.out.println(isDelete);
        if (isDelete == 1) {
            return new Response("200","删除成功！");
        }else {
            return new Response("300", "删除失败！");
        }
    }

    @Override
    public List<Flower> selectFlower(String keyWord) {
        List<Flower> flowerList=adminMapper.selectFlower(keyWord);
        return flowerList;
    }
}
