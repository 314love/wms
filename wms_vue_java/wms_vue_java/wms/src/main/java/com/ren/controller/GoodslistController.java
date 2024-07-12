package com.ren.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ren.common.QueryPageParam;
import com.ren.common.Result;
import com.ren.entity.Goodslist;
import com.ren.service.GoodslistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ren
 * @since 2022-11-22
 */
@RestController
@RequestMapping("/goodslist")
@CrossOrigin
public class GoodslistController {
    //    增删改查实现
    @Autowired
    private GoodslistService goodslistService;
    //    查询
    @GetMapping
    public Result selextList(){
        if( Result.suc(goodslistService.list())!=null){
            return  Result.suc(goodslistService.list());
        }else {
            return Result.fail();
        }
    }
    @GetMapping("/{id}")
    public Result selectByid(@PathVariable Integer id){
        if(goodslistService.getById(id)!=null){
            return Result.suc(goodslistService.getById(id));
        }else {
            return Result.fail();
        }

    }
    //   增加
    @PostMapping()
    public Result save(@RequestBody Goodslist goodslist){
        return goodslistService.save(goodslist)?Result.suc():Result.fail();
    }
    //    修改
    @PutMapping
    public Result update(@RequestBody  Goodslist goodslist){
        if(goodslistService.updateById(goodslist)){
            return Result.suc(goodslistService.updateById(goodslist));
        }else {
            return Result.fail();
        }
    }
    //    删除
    @DeleteMapping("/{id}")
    public Result deleted(@PathVariable Integer id){
        if(goodslistService.removeById(id)){
            return Result.suc(goodslistService.removeById(id));
        }else {
            return Result.fail();
        }
    }
    //    新增或修改
    @PostMapping("/saveOrupdate")
    public Result saveOrupdate(@RequestBody  Goodslist goodslist){
        if(goodslistService.saveOrUpdate(goodslist)){
            return Result.suc(goodslistService.saveOrUpdate(goodslist));
        }else {
            return Result.fail();
        }
    }
    //    模糊查询like
    @PostMapping("/listP")
    public Result listP(@RequestBody Goodslist goodslist){
        LambdaQueryWrapper<Goodslist> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        if(lambdaQueryWrapper.like(Goodslist::getName,goodslist.getName())!=null)
        {   return Result.suc(goodslistService.list(lambdaQueryWrapper));
        }else {
            return Result.fail();
        }

    }

    @PostMapping("/listPage")
    public Result listPage(@RequestBody QueryPageParam query){
//    System.out.println(query.getPageCurrent());
//    System.out.println(query.getPageSize());
        IPage page=new Page(query.getPageCurrent(),query.getPageSize());
        HashMap param = query.getParam();
        String name= (String) param.get("name");
        String storage= (String) param.get("storage");
        String goods= (String) param.get("goods");
        LambdaQueryWrapper<Goodslist> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        if(StringUtils.isNotBlank(name) && !"null".equals(name)){
            lambdaQueryWrapper.like(Goodslist::getName,name);
        }if(StringUtils.isNotBlank(storage) && !"null".equals(storage)){
            lambdaQueryWrapper.eq(Goodslist::getStorage,storage);
        }if(StringUtils.isNotBlank(goods) && !"null".equals(goods)){
            lambdaQueryWrapper.eq(Goodslist::getGoods,goods);
        }

        IPage iPage = goodslistService.page(page,lambdaQueryWrapper);

        return Result.suc(iPage.getRecords(),iPage.getTotal());
    }
}
