package com.flower.controller;

import com.flower.entity.Flower;
import com.flower.service.FlowerService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/flower")
public class FlowerController {
    @Autowired
    private FlowerService flowerService;

    /**
     * 鲜花首页
     */
    @GetMapping("/index")
    public ModelAndView index() {
        List<Flower> flowers = flowerService.findBySale();
        ModelAndView mav = new ModelAndView();
        mav.addObject("flowers", flowers);
        mav.setViewName("index");
        return mav;
    }

    /**
     * 鲜花查询页
     * @param keyword 关键字
     * @param page 当前页码
     * @param price 价格排序
     * @param sale 销售量排序
     */
    @GetMapping("/search")
    public ModelAndView search(@RequestParam(value = "keyword", defaultValue = "") String keyword,
                         @RequestParam(value = "page", required = true, defaultValue = "1") Integer page,
                         @RequestParam(value = "price", required = false, defaultValue = "0") Integer price,
                         @RequestParam(value = "sale", required = false, defaultValue = "0") Integer sale) {
        ModelMap modelMap = new ModelMap();
        PageInfo<Flower> flowerPageInfo = flowerService.findAll(keyword, page, price, sale);
        modelMap.addAttribute("page", flowerPageInfo);
        modelMap.addAttribute("price", price);
        modelMap.addAttribute("sale", sale);
        modelMap.addAttribute("keyword", keyword);
        ModelAndView mav = new ModelAndView("search", modelMap);
        return mav;
    }
}
