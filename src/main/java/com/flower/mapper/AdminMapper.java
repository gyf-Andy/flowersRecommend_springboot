package com.flower.mapper;

import com.flower.entity.Admin;
import com.flower.entity.Flower;
import com.flower.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AdminMapper {

    @Select("select * from admin where account=#{account} and password=#{password}")
    Admin login(@Param("account") String account, @Param("password") String password);

    @Select("select * from flower order by sale desc limit 0,5")
    List<Flower> flowerList();

    @Select("select * from flower order by price desc limit 0,5")
    List<Flower> priceList();

    @Select("select count(*) from user")
    int userCount();

    @Select("select count(distinct title) from flower")
    int flowerCount();

    @Select("select count(*) from user where TO_DAYS(now()) - TO_DAYS(registerdate) <=7 ")
    int aWeekUser();

    @Select("select * from user")
    List<User> userList();

    @Delete("delete from user where id=#{id}")
    void deleteUserById(int id);

    @Select("select username from user where id=#{id}")
    String selectUserNameById(int id);

    @Select("select * from user where username=#{username}")
    List<User> selectOneUser(String username);

    @Select("select * from admin")
    List<Admin> adminList();

    @Select("select * from admin where account=#{account} ")
    List<Admin> selectOneAdmin(String account);

    @Select("select name from admin where password=#{password}")
    String passwordIsExist(String password);

    @Insert("insert into admin (account,password,name,identity) values(#{admin.account},#{admin.password},#{admin.name},#{admin.identity})")
    int addAdmin(@Param("admin") Admin admin);

    @Delete("delete from admin where id=#{id}")
    int deleteAdminById(int id);

    @Update("update admin set password=#{password},name=#{name} where account=#{account}")
    int changeAdmin(@Param("account") String account,@Param("password") String password,@Param("name") String name);

    @Select("select * from flower ")
    List<Flower> toFlowerControl();

    @Delete("delete from flower where id=#{id}")
    int deleteFlowerById(int id);

    @Select("<script>" +
            "select * from flower where 1=1 " +
            "<if test='keyWord!=null'>and title like #{keyWord} or description like #{keyWord} </if>" +
            "</script>")
    List<Flower> selectFlower(String keyWord);
}
