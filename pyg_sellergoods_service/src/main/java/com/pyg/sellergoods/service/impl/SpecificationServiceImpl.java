package com.pyg.sellergoods.service.impl;

import bean.PageResult;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pyg.mapper.TbSpecificationMapper;
import com.pyg.mapper.TbSpecificationOptionMapper;
import com.pyg.pojo.TbSpecification;
import com.pyg.pojo.TbSpecificationExample;
import com.pyg.pojo.TbSpecificationOption;
import com.pyg.pojo.TbSpecificationOptionExample;
import com.pyg.sellergoods.service.SpecificationService;
import groupEntity.Specification;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@Service
public class SpecificationServiceImpl implements SpecificationService {

    @Autowired
    private TbSpecificationMapper specificationMapper;
    @Autowired
    private TbSpecificationOptionMapper specificationOptionMapper;

    @Override
    public List<TbSpecification> findAll() {
        return specificationMapper.selectByExample(null);
    }

    @Override
    public PageResult findPage(Integer pageNo, Integer pageSize) {

        PageHelper.startPage(pageNo,pageSize);
        Page<TbSpecification> page = (Page<TbSpecification>) specificationMapper.selectByExample(null);
//        page.getResult();//当前页的数据
//        page.getTotal();//总条数
        return new PageResult( page.getTotal(),page.getResult());
    }

    @Override
    public void add(Specification specification) {

//        {"tbSpecificationOptionList":
//            [{"optionName":"玻璃","orders":"1"},
//            {"optionName":"陶瓷","orders":"2"}],


//            "tbSpecification":{"specName":"水杯材质"}}

        TbSpecification tbSpecification = specification.getTbSpecification();
        specificationMapper.insert(tbSpecification); //注意在mapper中插入对象后返回主键id

//  `spec_id` bigint(30) DEFAULT NULL COMMENT '规格ID',

        List<TbSpecificationOption> tbSpecificationOptionList = specification.getTbSpecificationOptionList();
        for (TbSpecificationOption tbSpecificationOption : tbSpecificationOptionList) {
            tbSpecificationOption.setSpecId(tbSpecification.getId());
            specificationOptionMapper.insert(tbSpecificationOption);
        }

    }

    @Override
    public Specification fineOne(Long id) {


        Specification specification = new Specification();
        TbSpecification tbSpecification = specificationMapper.selectByPrimaryKey(id);
        specification.setTbSpecification(tbSpecification);

//        select * from tb_specification_option where spec_id=?
        TbSpecificationOptionExample example = new TbSpecificationOptionExample();
        example.createCriteria().andSpecIdEqualTo(id);
        List<TbSpecificationOption> tbSpecificationOptionList = specificationOptionMapper.selectByExample(example);
//        List<TbSpecificationOption> tbSpecificationOptionList = specificationOptionMapper.selectBySpecId(id);
        specification.setTbSpecificationOptionList(tbSpecificationOptionList);
        return specification;
    }

    @Override
    public void update(Specification specification) {
        TbSpecification tbSpecification = specification.getTbSpecification();
        specificationMapper.updateByPrimaryKey(tbSpecification);

//        先全部删除此规格下的规格小项 然后再重新添加
//        delete from tb_specification_option where spec_id=?
        TbSpecificationOptionExample example = new TbSpecificationOptionExample();
        example.createCriteria().andSpecIdEqualTo(tbSpecification.getId());
        specificationOptionMapper.deleteByExample(example);
//        specificationOptionMapper.deleteBySpecId(tbSpecification.getId());

        List<TbSpecificationOption> tbSpecificationOptionList = specification.getTbSpecificationOptionList();
        for (TbSpecificationOption tbSpecificationOption : tbSpecificationOptionList) {
            tbSpecificationOption.setSpecId(tbSpecification.getId());
            specificationOptionMapper.insert(tbSpecificationOption);
        }

    }

    @Override
    public void dele(Long[] ids) {
        for (Long id : ids) {
            specificationMapper.deleteByPrimaryKey(id);
//            把规格小项的数据也一起删除
//            specificationOptionMapper.deleteBySpecId(id);
            TbSpecificationOptionExample example = new TbSpecificationOptionExample();
            example.createCriteria().andSpecIdEqualTo(id);
            specificationOptionMapper.deleteByExample(example);
        }

    }

    @Override
    public PageResult findPage(Integer pageNo, Integer pageSize, TbSpecification tbSpecification) {

        PageHelper.startPage(pageNo,pageSize);
        TbSpecificationExample example = new TbSpecificationExample();
        TbSpecificationExample.Criteria criteria = example.createCriteria();
        if(StringUtils.isNotBlank(tbSpecification.getSpecName())){
            criteria.andSpecNameLike("%"+tbSpecification.getSpecName()+"%");
        }
        Page<TbSpecification> page = (Page<TbSpecification>) specificationMapper.selectByExample(example);

        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public List<Map> findSpecList() {
        return specificationMapper.findSpecList();
    }
}
