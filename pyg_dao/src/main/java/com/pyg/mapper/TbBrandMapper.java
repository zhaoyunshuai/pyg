package com.pyg.mapper;

import bean.PageResult;
import com.pyg.pojo.TbBrand;

import java.util.List;

public interface TbBrandMapper {
    /**
     * 查询所有品牌数据
     * @return
     */
    public List<TbBrand> findAll();

    /**
     * 新增品牌
     */
    public void addBrand(TbBrand tbBrand);
    /**
     * 根据品牌Id，查询品牌数据
     */
    public TbBrand findBrandById(Integer id);
}
