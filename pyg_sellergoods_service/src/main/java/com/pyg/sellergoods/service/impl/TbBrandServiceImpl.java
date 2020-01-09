package com.pyg.sellergoods.service.impl;

import bean.PageResult;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pyg.mapper.TbBrandMapper;
import com.pyg.pojo.TbBrand;
import com.pyg.sellergoods.service.TbBrandService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@Service
public class TbBrandServiceImpl implements TbBrandService {
    @Autowired
    TbBrandMapper tbBrandMapper;
    @Override
    public List<TbBrand> findAll() {
        return tbBrandMapper.findAll();
    }

    @Override
    public PageResult findAll(Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        Page<TbBrand> page= (Page<TbBrand>) tbBrandMapper.findAll();
        List<TbBrand> rows = page.getResult();
        long total = page.getTotal();
        PageResult pageResult = new PageResult(total, rows);
        return pageResult;
    }

    @Override
    public void add(TbBrand tbBrand) {
        tbBrandMapper.addBrand(tbBrand);

    }

    @Override
    public TbBrand findBrandById(Integer id) {

        return tbBrandMapper.findBrandById(id);
    }

    @Override
    public void updateBrandById(TbBrand tbBrand) {

        tbBrandMapper.updateBrandById(tbBrand);

    }

    @Override
    public void deleteBrandByByIds(Long[] ids) {
        tbBrandMapper.deleteBrandByByIds(ids);
    }
}
