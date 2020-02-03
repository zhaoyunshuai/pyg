package com.pyg.sellergoods.service;

import bean.PageResult;
import com.pyg.pojo.TbSpecification;
import groupEntity.Specification;

import java.util.List;

public interface TbSpecificationService {
    /**
     * 查询所有品牌数据
     *
     * @return
     */
    public List<TbSpecification> findAll();

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
     * @param specification 组合类
     * @return
     */
    public void add(Specification specification);

    /**
     * 根据品牌Id，查询品牌数据
     *
     * @param id 品牌ID
     * @return TbSpecification specification 品牌数据
     */
    public Specification findSpecificationById(Integer id);

    /**
     * 根据品牌Id,修改品牌数据
     * @param tbSpecification
     * @return
     */
    public void updateSpecificationById(Specification tbSpecification);

    public void  deleteSpecificationByByIds(Long[] ids);

    PageResult searchSpecification(Integer pageNo, Integer pageSize, TbSpecification specification);
}
