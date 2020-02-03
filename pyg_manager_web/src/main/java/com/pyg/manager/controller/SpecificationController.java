package com.pyg.manager.controller;

import bean.PageResult;
import com.alibaba.dubbo.config.annotation.Reference;
import com.pyg.pojo.TbSpecification;
import com.pyg.sellergoods.service.TbSpecificationService;
import entity.Result;
import groupEntity.Specification;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController//此处要用此注解，不然返回值是页面，而不是JSON格式的数据。
@RequestMapping("/specification")
public class SpecificationController {
    @Reference//注入dubbo容器中的service对象
            TbSpecificationService tbSpecificationService;

    /**
     * 查询所有品牌数据
     *
     * @return
     */
    @RequestMapping("/findAll")
    public List<TbSpecification> findAll() {
        return tbSpecificationService.findAll();
    }

    /**
     * 分页查询所有品牌数据
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(Integer pageNo, Integer pageSize) {
        return tbSpecificationService.findAll(pageNo, pageSize);
    }

    /**
     * 新增数据
     *
     * @param specification
     * @return
     */
    @RequestMapping("/add")
    public Result add(@RequestBody Specification specification) {
        Result result = new Result();
        try {
            tbSpecificationService.add(specification);
            result.setSuccess(true);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMsg("添加品牌出错：" + e.getMessage());
        }
        return result;
    }

    /**
     * 修改品牌数据方法
     *
     * @param specification 修改后的品牌数据
     * @return Result 修改结果
     */
    @RequestMapping("/update")
    public Result updateSpecificationById(@RequestBody Specification specification) {
        try {
            tbSpecificationService.updateSpecificationById(specification);
            return new Result(true, "修改成功");
        } catch (Exception e) {
            return new Result(false, "修改失败" + e.getMessage());
        }

    }

    /**
     * 根据品牌Id,查询品牌数据
     *
     * @param id
     * @return
     */
    @RequestMapping("/findOne")
    public Specification findSpecificationById(Integer id) {
        return tbSpecificationService.findSpecificationById(id);
    }

    @RequestMapping("/delete")
    public Result deleteSpecificationByByIds(Long[] ids) {
        try {
            tbSpecificationService.deleteSpecificationByByIds(ids);
            return new Result(true, "删除成功");
        } catch (Exception e) {
            return new Result(false, "删除失败" + e.getMessage());
        }
    }

    /**
     * 修改品牌数据方法
     *
     * @param specification 修改后的品牌数据
     * @return Result 修改结果
     */
    @RequestMapping("/search")
    public PageResult searchTbSpecification(Integer pageNo, Integer pageSize, @RequestBody TbSpecification specification) {

        return tbSpecificationService.searchSpecification(pageNo, pageSize, specification);
    }
}
