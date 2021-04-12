package com.flower.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.flower.entity.Flower;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface FlowerService extends IService<Flower> {

    /**
     * 添加一个鲜花
     * @param flower 鲜花对象
     */
    Integer addFlower(Flower flower);

    /**
     * 批量添加鲜花
     * @param flowers 鲜花列表
     */
    boolean addFlowers(List<Flower> flowers);

    /**
     * 查找所有鲜花
     * @param keyword 关键字
     * @param page 当前页
     * @param price 是否按照价格排序
     * @param sale 是否按照销量排序
     * @return 鲜花分页对象
     */
    PageInfo<Flower> findAll(String keyword, Integer page, Integer price, Integer sale);

    /**
     * 获取最后添加的鲜花
     * @return 鲜花对象
     */
    Flower getLast();

    /**
     * 获取鲜花总数
     * @return 鲜花总数
     */
    Integer getCount();

    /**
     * 查找最畅销的鲜花
     * @return 鲜花列表
     */
    List<Flower> findBySale();
}
