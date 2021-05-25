package com.flower.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.flower.entity.Flower;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

//@Repository
@Mapper
public interface FlowerMapper extends BaseMapper<Flower> {
    /**
     * 获取最后添加的一个鲜花
     * @return 鲜花对象
     */
    @Select("select * from flower order by id desc limit 1")
    Flower getLast();

    /**
     * 获取最畅销的十种鲜花
     * @return 鲜花列表
     */
    @Select("select * from flower order by sale desc limit 4")
    List<Flower> findBySale();

    /**
     * 按照搜索条件查询鲜花
     * @param keyword 关键字
     * @param price 是否按照价格排序
     * @param sale 是否按照销量排序
     * @return 鲜花列表
     */
    @Select("<script>" +
            "select * from flower where 1=1 " +
            "<if test='keyword!=null'>and (title like #{keyword} or description like #{keyword}) </if>" +
            "<if test='price==1'>ORDER BY price asc</if>" +
            "<if test='price==2'>ORDER BY price desc</if>" +
            "<if test='sale==1'>ORDER BY sale asc</if>" +
            "<if test='sale==2'>ORDER BY sale desc</if>" +
            "</script>")
    List<Flower> selectBySearch(@Param("keyword") String keyword, @Param("price") Integer price, @Param("sale") Integer sale);

    /**
     * 获取推荐的鲜花信息
     * @return 推荐鲜花的列表
     */
    @Select("select * from flower order by rand() limit 8")
    List<Flower> recommendForMe();
}
