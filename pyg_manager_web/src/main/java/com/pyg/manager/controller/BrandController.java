package com.pyg.manager.controller;

import bean.PageResult;
import bean.Result;
import com.alibaba.dubbo.config.annotation.Reference;
import com.pyg.pojo.TbBrand;
import com.pyg.sellergoods.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/brand")
public class BrandController {

    @Reference  //远程注入
    private BrandService brandService;
    /**
     * 此方法是在添加模板时需要的 要求数据格式是 [{"id":"1","text":"华为"},{"id":"2","text":"小米"},{"id":"3","text":"锤子"}]
     */
    @RequestMapping("/findBrandList")
    public List<Map> findBrandList(){
        return brandService.findBrandList();
    }


    @RequestMapping("/findAll")
    public List<TbBrand> findAll(){
        return brandService.findAll();
    }


    @RequestMapping("/findPage")
    public PageResult findPage(Integer pageNo, Integer pageSize){
//        {total:100,rows:[{},{}]}
        return brandService.findPage(pageNo,pageSize);
//        return null;
    }
    @RequestMapping("/add")
    public Result add(@RequestBody TbBrand brand){ //@RequestBody是用来接收json数据的
//        {success:true|false,message:"添加成功"|"添加失败"}
        try {
            brandService.add(brand);
            return new Result(true,"添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"添加失败");
        }
//        return null;
    }
    @RequestMapping("/fineOne")
    public TbBrand fineOne(Long id){
          return   brandService.findOne(id);
//        return null;
    }
    @RequestMapping("/update")
    public Result update(@RequestBody TbBrand brand){ //@RequestBody是用来接收json数据的
        try {
            brandService.update(brand);
            return new Result(true,"修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"修改失败");
        }
    }
    @RequestMapping("/dele")
    public Result dele(Long[] ids){ //@RequestBody是用来接收json数据的
        try {
            brandService.dele(ids);
            return new Result(true,"删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"删除失败");
        }
    }

    @RequestMapping("/search")
    public PageResult search(Integer pageNo, Integer pageSize,@RequestBody TbBrand tbBrand){
//        {total:100,rows:[{},{}]}
        return brandService.findPage(tbBrand,pageNo,pageSize);
//        return null;
    }
}
