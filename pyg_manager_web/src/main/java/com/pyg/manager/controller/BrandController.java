package com.pyg.manager.controller;

import bean.PageResult;
import com.alibaba.dubbo.config.annotation.Reference;
import com.pyg.pojo.TbBrand;
import com.pyg.sellergoods.service.TbBrandService;
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

    @RequestMapping("/update")
    public Result updateBrandById() {
        //todo 修改方法，有待完善
        return new Result();
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
}
