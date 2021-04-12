package com.flower.controller;

import com.flower.entity.Admin;
import com.flower.entity.Flower;
import com.flower.entity.Response;
import com.flower.entity.User;
import com.flower.service.AdminService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;



@Controller
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private AdminService adminService;

    /**
     * 登陆页面接口
     * @return 跳转到adminLogin页面
     */
    @RequestMapping("/toAdminLoginPage")
    public String toAdminLoginPage(){
        return "admin/adminLogin";
    }

    /**
     * 对登录信息进行判断，并给前台相应的响应
     *
     * @param account 管理员账号
     * @param password 管理员密码
     * @param model 向model中放入数据，在前端页面展示
     * @return json数据，响应ajax请求
     */
    @RequestMapping("/login")
    @ResponseBody
    public Response login(String account, String password, Model model,HttpSession session){
        System.out.println(account+password);
        Admin admin=adminService.login(account,password);
        if(admin!=null){
            session.setAttribute("admin",admin);
            return new Response("200","登陆成功");
        }else{
            return new Response("300","账号或密码错误");
        }
    }

    /**
     * 对用户注册数，鲜花种类数，近一周用户注册数
     * 以及鲜花的销量价格排行进行查询跳转到index页面进行展示
     *
     * @param model 向model中放入数据，在前端页面展示
     * @return 跳转到index页面
     */
    @RequestMapping("/toIndexPage")
    public String toIndexPage(Model model) {
        //获取销量前五的销量
        List<Flower> flowerList=adminService.flowerList();
        List saleList=new ArrayList();
        List nameList=new ArrayList();
        for (Flower item:flowerList) {
            saleList.add(item.getSale());
            nameList.add(item.getId());
        }
        //获取价格前五的价格
        List<Flower> priceList=adminService.priceList();
        List priceL=new ArrayList();
        List idL=new ArrayList();
        for (Flower item:priceList) {
            priceL.add(item.getPrice());
            idL.add(item.getId());
        }
        //获取用户总数
        int userCount=adminService.userCount();
        //获取花卉种类数
        int flowerCount=adminService.flowerCount();
        //获取近一周用户注册数
        int aWeekUser=adminService.aWeekUser();
        System.out.println(saleList);
        System.out.println(nameList);
        model.addAttribute("saleList",saleList);
        model.addAttribute("nameList",nameList);
        model.addAttribute("priceL",priceL);
        model.addAttribute("idL",idL);
        model.addAttribute("userCount",userCount);
        model.addAttribute("flowerCopunt",flowerCount);
        model.addAttribute("aWeekUser",aWeekUser);
        return "admin/index";
    }

    /**
     * 查询所有用户的信息并进行分页展示
     *
     * @param model 向model中放入数据，在前端页面展示
     * @param pageNum 分页的页码
     * @return 跳转到userControl页面
     */
    @RequestMapping("toUserControlPage")
    public String toUserControlPage(Model model,@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,8);
        List<User> userList=adminService.userList();
        System.out.println(userList);

        PageInfo<User> pageInfo = new PageInfo<User>(userList);
        System.out.println(pageInfo);
        model.addAttribute("pageInfo",pageInfo);

        return "admin/userControl";
    }

    /**
     * 删除用户账号
     *
     * @param id 用户编号
     * @return json数据，响应ajax请求
     */
    @RequestMapping("deleteUserById")
    @ResponseBody
    public Response deleteUserbyId(int id){
        Response response=adminService.deleteUserById(id);
        return response;
    }

    /**
     * 查找用户
     *
     * @param username 用户名
     * @param model 向model中放入数据，在前端页面展示
     * @param pageNum 分页的页码
     * @return 跳转到userControl页面
     */
    @RequestMapping("selectOneUser")
    public String selectOneUser(String username,Model model,@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,8);
        System.out.println(username);
        List<User> userList=adminService.selectOneUser(username);
        System.out.println(userList);
        PageInfo<User> pageInfo = new PageInfo<User>(userList);
        System.out.println(pageInfo);
        model.addAttribute("pageInfo",pageInfo);

        return "admin/userControl";

    }

    /**
     * 查询所有的管理员账户，并分页展示
     *
     * @param model 向model中放入数据，在前端页面展示
     * @param pageNum 分页的页码
     * @return 跳转到adminUserControl页面
     */
    @RequestMapping("toAdminUserControl")
    public String toAdminUserControl(Model model,@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,8);
        List<Admin> adminList=adminService.adminList();
        System.out.println(adminList);
        PageInfo<Admin> pageInfo = new PageInfo<Admin>(adminList);
        System.out.println(pageInfo);
        model.addAttribute("pageInfo",pageInfo);

        return "admin/adminUserControl";
    }

    /**
     * 查找管理员信息
     *
     * @param account 管理员账号
     * @param model 向model中放入数据，在前端页面展示
     * @param pageNum 分页的页码
     * @return 跳转到adminUserControl页面
     */
    @RequestMapping("selectOneAdmin")
    public String selectOneAdmin(String account,Model model,@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,8);
        List<Admin> adminList=adminService.selectOneAdmin(account);
        System.out.println(adminList);
        PageInfo<Admin> pageInfo = new PageInfo<Admin>(adminList);
        System.out.println(pageInfo);
        model.addAttribute("pageInfo",pageInfo);

        return "admin/adminUserControl";
    }

    /**
     * 添加新管理员
     *
     * @param admin 管理员对象，用来接收前端传递来的信息（映射）
     * @return  json数据，响应ajax请求
     */
    @RequestMapping("addAdmin")
    @ResponseBody
    public Response addAdmin(Admin admin){
        admin.setIdentity("normal");
        Response response=adminService.addAdmin(admin);
        return response;
    }

    /**
     * 根据id删除管理员账户
     *
     * @param id 管理员编号
     * @return  json数据，响应ajax请求
     */
    @RequestMapping("deleteAdminById")
    @ResponseBody
    public Response deleteAdminById(int id){
        Response response=adminService.deleteAdminById(id);
        return response;
    }

    /**
     * 更改管理员账户信息
     *
     * @param account 管理员账号
     * @param password 管理员密码
     * @param name 管理员姓名
     * @return   json数据，响应ajax请求
     */
    @RequestMapping("changeAdmin")
    @ResponseBody
    public Response changeAdmin(String account,String password,String name){
        Response response=adminService.changeAdmin(account,password,name);
        return response;
    }

    /**
     * 查询所有鲜花的信息并分页展示
     *
     * @param model 向model中存放需要展示的数据
     * @param pageNum 分页的页码
     * @return 跳转到flowerControl页面
     */
    @RequestMapping("toFlowerControl")
    public String toFlowerControl(Model model,@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,8);
        List<Flower> flowerList=adminService.toFlowerControl();
        PageInfo<Flower> pageInfo = new PageInfo<Flower>(flowerList);
        System.out.println(pageInfo);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/flowerControl";
    }

    /**
     * 删除鲜花信息
     *
     * @param id 鲜花信息的编号
     * @return json数据，响应ajax请求
     */
    @RequestMapping("deleteFlowerById")
    @ResponseBody
    public Response deleteFlowerById(int id){
        Response response=adminService.deleteFlowerById(id);
        return response;
    }

    /**
     * 查询鲜花信息（模糊查询）
     *
     * @param keyWord 关键字
     * @param model  向model中放入要展示的数据
     * @param pageNum 分页的页码
     * @return 跳转到flowerControl页面
     */
    @RequestMapping("selectFlower")
    public String selectFlower(String keyWord,Model model,@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        keyWord="%"+keyWord+"%";
        PageHelper.startPage(pageNum,8);
        List<Flower> flowerList=adminService.selectFlower(keyWord);
        PageInfo<Flower> pageInfo = new PageInfo<Flower>(flowerList);
        System.out.println(pageInfo);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/flowerControl";
    }

    /**
     * 登出管理系统
     *
     * @param session 需要清除session中的数据
     * @return 向ajax请求给出响应
     */
    @RequestMapping("logout")
    @ResponseBody
    public Response adminLogout(HttpSession session){
        session.removeAttribute("user");
        session.removeAttribute("admin");
        return new Response("200","即将退出！");
    }
}
