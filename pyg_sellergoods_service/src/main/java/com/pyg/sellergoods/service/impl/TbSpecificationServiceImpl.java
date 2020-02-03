package com.pyg.sellergoods.service.impl;

import bean.PageResult;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pyg.mapper.TbSpecificationMapper;
import com.pyg.mapper.TbSpecificationOptionMapper;
import com.pyg.pojo.TbSpecification;
import com.pyg.pojo.TbSpecificationOption;
import com.pyg.sellergoods.service.TbSpecificationService;
import groupEntity.Specification;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class TbSpecificationServiceImpl implements TbSpecificationService {
    @Autowired
    TbSpecificationMapper tbSpecificationMapper;
    @Autowired
    TbSpecificationOptionMapper  tbSpecificationOptionMapper;
    @Override
    public List<TbSpecification> findAll() {
        return tbSpecificationMapper.findAll();
    }

    @Override
    public PageResult findAll(Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        Page<TbSpecification> page= (Page<TbSpecification>) tbSpecificationMapper.findAll();
        List<TbSpecification> rows = page.getResult();
        long total = page.getTotal();
        PageResult pageResult = new PageResult(total, rows);
        return pageResult;
    }

    @Override
    public void add(Specification specification) {
        TbSpecification tbSpecification = specification.getTbSpecification();
        //需要做主键回显，用于给个小项的插入
        tbSpecificationMapper.addSpecification(tbSpecification);
        List<TbSpecificationOption> optionList = specification.getOptionList();
        for (TbSpecificationOption tbSpecificationOption : optionList) {
            tbSpecificationOption.setSpecId(tbSpecification.getId());
            tbSpecificationOptionMapper.addOption(tbSpecificationOption);
        }

    }

    @Override
    public Specification findSpecificationById(Integer id) {


        Specification specification = new Specification();
        specification.setTbSpecification(tbSpecificationMapper.findSpecificationById(id));
        specification.setOptionList(tbSpecificationOptionMapper.findOptionById(id));
        return specification;
    }

    @Override
    public void updateSpecificationById(Specification specification) {
        tbSpecificationMapper.updateSpecificationById(specification.getTbSpecification());
        List<TbSpecificationOption> optionList = specification.getOptionList();
        for (TbSpecificationOption tbSpecificationOption : optionList) {
            tbSpecificationOptionMapper.updateOptionById(tbSpecificationOption);
        }
      /*前端点击删除规格小项后，做的相应的处理
        首先要确定删除哪些数据，即前段没有传递过来的数据，就是需要进行删除的数据。
        如果判定前段有没有传递过来呢？可以先去查一下，specId对应的规格小项数据
        */



    }

    @Override
    public void deleteSpecificationByByIds(Long[] ids) {
        tbSpecificationMapper.deleteSpecificationByByIds(ids);
    }

    @Override
    public PageResult searchSpecification(Integer pageNo, Integer pageSize, TbSpecification specification) {
        PageHelper.startPage(pageNo,pageSize);
        Page<TbSpecification> page= (Page<TbSpecification>) tbSpecificationMapper.searchSpecification(specification);
        List<TbSpecification> rows = page.getResult();
        long total = page.getTotal();
        PageResult pageResult = new PageResult(total, rows);
        return pageResult;
    }
}
