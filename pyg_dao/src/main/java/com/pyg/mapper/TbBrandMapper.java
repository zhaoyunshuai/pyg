package com.pyg.mapper;

import bean.PageResult;
import com.pyg.pojo.TbBrand;
import org.apache.ibatis.annotations.Param;

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

    /**
     * 根据品牌ID，修改品牌数据
     */
    public void updateBrandById(TbBrand tbBrand);

    /**
     * 根据品牌id删除品牌数据
     * @param ids
     */
    void deleteBrandByByIds(@Param("ids")Long[] ids);

    List<TbBrand> searchBrand(TbBrand brand);
}
