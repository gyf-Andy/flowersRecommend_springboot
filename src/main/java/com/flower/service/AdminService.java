package com.flower.service;

import com.flower.entity.Admin;
import com.flower.entity.Flower;
import com.flower.entity.Response;
import com.flower.entity.User;

import java.util.List;

public interface AdminService {
    /**
     * 管理员登陆
     *
     * @param account
     * @param password
     * @return
     */
    Admin login(String account, String password);

    List<Flower> flowerList();

    List<Flower> priceList();

    int userCount();

    int flowerCount();

    int aWeekUser();

    List<User> userList();

    Response deleteUserById(int id);

    List<User> selectOneUser(String username);

    List<Admin> adminList();

    List<Admin> selectOneAdmin(String account);

    Response addAdmin(Admin admin);

    Response deleteAdminById(int id);

    Response changeAdmin(String account,String password,String name);

    List<Flower> toFlowerControl();

    Response deleteFlowerById(int id);

    List<Flower> selectFlower(String keyWord);
}
