package com.pyg.sellergoods.service;

import bean.PageResult;
import com.pyg.pojo.TbSpecification;
import groupEntity.Specification;

import java.util.List;
import java.util.Map;

public interface SpecificationService {

    public List<TbSpecification> findAll();

    PageResult findPage(Integer pageNo, Integer pageSize);

    void add(Specification specification);

    Specification fineOne(Long id);

    void update(Specification specification);

    void dele(Long[] ids);

    PageResult findPage(Integer pageNo, Integer pageSize, TbSpecification tbSpecification);

    List<Map> findSpecList();
}
