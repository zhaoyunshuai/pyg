package com.pyg.mapper;

import com.pyg.pojo.TbSpecificationOption;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbSpecificationOptionMapper {
    /**
     * 查询所有品牌数据
     * @return
     */
    public List<TbSpecificationOption> findAll();

    /**
     * 新增品牌
     */
    public void addOption(TbSpecificationOption tbOption);
    /**
     * 根据规格小项的spec_id，查询品牌数据
     */
    public List<TbSpecificationOption> findOptionById(Integer specId);

    /**
     * 根据品牌ID，修改品牌数据
     */
    public void updateOptionById(TbSpecificationOption tbOption);

    /**
     * 根据品牌id删除品牌数据
     * @param ids
     */
    void deleteOptionByByIds(@Param("ids") Long[] ids);

    List<TbSpecificationOption> searchOption(TbSpecificationOption option);
}
