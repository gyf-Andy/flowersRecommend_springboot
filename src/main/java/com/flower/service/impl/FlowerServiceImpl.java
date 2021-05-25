package com.flower.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.flower.entity.Flower;
import com.flower.mapper.FlowerMapper;
import com.flower.service.FlowerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FlowerServiceImpl extends ServiceImpl<FlowerMapper, Flower> implements FlowerService {

    @Resource
    private FlowerMapper mapper;

    @Override
    public Integer addFlower(Flower flower) {
        return mapper.insert(flower);
    }

    @Override
    public boolean addFlowers(List<Flower> flowers) {
        return this.saveBatch(flowers);
    }

    @Override
    public PageInfo<Flower> findAll(String keyword, Integer page, Integer price, Integer sale) {
        PageHelper.startPage(page, 16);
        if (keyword.equals("")) {
            keyword = null;
        } else {
            keyword = "%" + keyword + "%";
        }
        List<Flower> flowers = mapper.selectBySearch(keyword, price, sale);
        return new PageInfo(flowers);
    }


    @Override
    public Flower getLast() {
        return mapper.getLast();
    }

    @Override
    public Integer getCount() {
        return this.count();
    }

    @Override
    public List<Flower> findBySale() {
        return mapper.findBySale();
    }

    @Override
    public List<Flower> recommendForMe() {
        List<Flower> flowerList= mapper.recommendForMe();
        return flowerList;
    }
}
