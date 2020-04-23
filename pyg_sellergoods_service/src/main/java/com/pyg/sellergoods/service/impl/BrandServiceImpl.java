package com.pyg.sellergoods.service.impl;

import bean.PageResult;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pyg.mapper.TbBrandMapper;
import com.pyg.pojo.TbBrand;
import com.pyg.pojo.TbBrandExample;
import com.pyg.sellergoods.service.BrandService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private TbBrandMapper brandMapper;

    @Override
    public List<TbBrand> findAll() {
        return brandMapper.selectByExample(null);
    }

    @Override
    public PageResult findPage(int pageNo, int pageSize) {

        PageHelper.startPage(pageNo,pageSize);
        Page<TbBrand> page = (Page<TbBrand>) brandMapper.selectByExample(null);
//        page.getResult();//当前页的数据
//        page.getTotal();//总条数
        return new PageResult( page.getTotal(),page.getResult());
    }

    @Override
    public void add(TbBrand brand) {
        brandMapper.insert(brand);
    }

    @Override
    public TbBrand findOne(Long id) {
        return brandMapper.selectByPrimaryKey(id);
    }

    @Override
    public void update(TbBrand brand) {
        brandMapper.updateByPrimaryKey(brand);
    }

    @Override
    public void dele(Long[] ids) {
        for (Long id : ids) {
            brandMapper.deleteByPrimaryKey(id);
        }

    }

    @Override
    public PageResult findPage(TbBrand tbBrand, int pageNo,int pageSize) {

        PageHelper.startPage(pageNo,pageSize);
        TbBrandExample example = new TbBrandExample();
        TbBrandExample.Criteria criteria = example.createCriteria();

        if(StringUtils.isNotBlank(tbBrand.getName())){
            criteria.andNameLike("%"+tbBrand.getName()+"%");
        }
        if(StringUtils.isNotBlank(tbBrand.getFirstChar())){
            criteria.andFirstCharEqualTo(tbBrand.getFirstChar());
        }
        Page<TbBrand> page = (Page<TbBrand>) brandMapper.selectByExample(example);
        return new PageResult(page.getTotal(),page.getResult());
    }
    /**
     * 此方法是在添加模板时需要的 要求数据格式是 [{"id":"1","text":"华为"},{"id":"2","text":"小米"},{"id":"3","text":"锤子"}]
     */
    @Override
    public List<Map> findBrandList() {
        return brandMapper.findBrandList();
    }
}
