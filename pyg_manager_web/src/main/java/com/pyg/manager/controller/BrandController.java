package com.pyg.manager.controller;

import bean.PageResult;
import com.alibaba.dubbo.config.annotation.Reference;
import com.pyg.pojo.TbBrand;
import com.pyg.sellergoods.service.TbBrandService;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import entity.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController//此处要用此注解，不然返回值是页面，而不是JSON格式的数据。
@RequestMapping("/brand")
public class BrandController {
    @Reference//注入dubbo容器中的service对象
            TbBrandService tbBrandService;

    /**
     * 查询所有品牌数据
     *
     * @return
     */
    @RequestMapping("/findAll")
    public List<TbBrand> findAll() {
        return tbBrandService.findAll();
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
        return tbBrandService.findAll(pageNo, pageSize);
    }

    /**
     * 新增数据
     *
     * @param brand
     * @return
     */
    @RequestMapping("/add")
    public Result add(@RequestBody TbBrand brand) {
        Result result = new Result();
        try {
            tbBrandService.add(brand);
            result.setSuccess(true);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMsg("添加品牌出错：" + e.getMessage());
        }
        return result;
    }

    /**
     * 修改品牌数据方法
     * @param brand 修改后的品牌数据
     * @return Result 修改结果
     */
    @RequestMapping("/update")
    public Result updateBrandById(@RequestBody TbBrand brand) {
        try {
            tbBrandService.updateBrandById(brand);
            return  new Result(true,"修改成功");
        } catch (Exception e) {
            return  new Result(false,"修改失败"+e.getMessage());
        }

    }

    /**
     * 根据品牌Id,查询品牌数据
     * @param id
     * @return
     */
    @RequestMapping("/findOne")
    public TbBrand findBrandById(Integer id) {
        return tbBrandService.findBrandById(id);
    }
    @RequestMapping("/delete")
    public Result deleteBrandByByIds(Long[] ids) {
        try {
            tbBrandService.deleteBrandByByIds(ids);
            return new Result(true,"删除成功");
        } catch (Exception e) {
            return new Result(false,"删除失败"+e.getMessage());
        }
    }


}
