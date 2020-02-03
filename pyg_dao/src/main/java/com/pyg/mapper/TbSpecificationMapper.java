package com.pyg.mapper;

import com.pyg.pojo.TbSpecification;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbSpecificationMapper {
    /**
     * 查询所有品牌数据
     * @return
     */
    public List<TbSpecification> findAll();

    /**
     * 新增品牌
     */
    public void addSpecification(TbSpecification tbSpecification);
    /**
     * 根据品牌Id，查询品牌数据
     */
    public TbSpecification findSpecificationById(Integer id);

    /**
     * 根据品牌ID，修改品牌数据
     */
    public void updateSpecificationById(TbSpecification tbSpecification);

    /**
     * 根据品牌id删除品牌数据
     * @param ids
     */
    void deleteSpecificationByByIds(@Param("ids") Long[] ids);

    /**
     * 模糊查询
     * @param specification
     * @return
     */
    List<TbSpecification> searchSpecification(TbSpecification specification);
}
