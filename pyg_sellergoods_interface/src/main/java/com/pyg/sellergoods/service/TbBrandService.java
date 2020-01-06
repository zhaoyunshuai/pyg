package com.pyg.sellergoods.service;

import bean.PageResult;
import com.pyg.pojo.TbBrand;
import entity.Result;

import java.util.List;

public interface TbBrandService {
    /**
     * 查询所有品牌数据
     *
     * @return
     */
    public List<TbBrand> findAll();

    /**
     * 分页查询品牌数据
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    public PageResult findAll(Integer pageNo, Integer pageSize);

    /**
     * 新增品牌
     *
     * @param tbBrand
     * @return
     */
    public void add(TbBrand tbBrand);

    /**
     * 根据品牌Id，查询品牌数据
     *
     * @param id 品牌ID
     * @return TbBrand brand 品牌数据
     */
    public TbBrand findBrandById(Integer id);
}
