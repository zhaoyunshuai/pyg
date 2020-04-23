package com.pyg.manager.controller;

import bean.PageResult;
import bean.Result;
import com.alibaba.dubbo.config.annotation.Reference;
import com.pyg.pojo.TbSpecification;
import com.pyg.sellergoods.service.SpecificationService;
import groupEntity.Specification;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/specification")
public class SpecificationController {

    @Reference  //远程注入
    private SpecificationService specificationService;

    @RequestMapping("/findSpecList")
    public List<Map> findSpecList(){
        return specificationService.findSpecList();
    }
    @RequestMapping("/findAll")
    public List<TbSpecification> findAll(){
        return specificationService.findAll();
    }
    @RequestMapping("/findPage")
    public PageResult findPage(Integer pageNo, Integer pageSize){
//        {total:100,rows:[{},{}]}
        return specificationService.findPage(pageNo,pageSize);
//        return null;
    }
    @RequestMapping("/add")
    public Result add(@RequestBody Specification specification){ //@RequestBody是用来接收json数据的
//        {success:true|false,message:"添加成功"|"添加失败"}
        try {
            specificationService.add(specification);
            return new Result(true,"添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"添加失败");
        }
//        return null;
    }
    @RequestMapping("/fineOne")
    public Specification fineOne(Long id){
          return   specificationService.fineOne(id);
//        return null;
    }
    @RequestMapping("/update")
    public Result update(@RequestBody Specification specification){ //@RequestBody是用来接收json数据的
        try {
            specificationService.update(specification);
            return new Result(true,"修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"修改失败");
        }
    }
    @RequestMapping("/dele")
    public Result dele(Long[] ids){ //@RequestBody是用来接收json数据的
        try {
            specificationService.dele(ids);
            return new Result(true,"删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"删除失败");
        }
    }

    @RequestMapping("/search")
    public PageResult search(Integer pageNo, Integer pageSize,@RequestBody TbSpecification tbSpecification){
//        {total:100,rows:[{},{}]}
        return specificationService.findPage(pageNo,pageSize,tbSpecification);
//        return null;
    }
}
