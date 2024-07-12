package com.ren.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ren.common.QueryPageParam;
import com.ren.common.Result;
import com.ren.entity.Goods;
import com.ren.service.GoodsService;
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
@CrossOrigin
@RestController
@RequestMapping("/goods")
public class GoodsController {
    //    增删改查实现
    @Autowired
    private GoodsService goodsService;
    //    查询
    @GetMapping
    public Result selextList(){
        if( Result.suc(goodsService.list())!=null){
            return  Result.suc(goodsService.list());
        }else {
            return Result.fail();
        }
    }
    @GetMapping("/{id}")
    public Result selectByid(@PathVariable Integer id){
        if(goodsService.getById(id)!=null){
            return Result.suc(goodsService.getById(id));
        }else {
            return Result.fail();
        }

    }
    //   增加
    @PostMapping()
    public Result save(@RequestBody Goods goods){
        return goodsService.save(goods)?Result.suc():Result.fail();
    }
    //    修改
    @PutMapping
    public Result update(@RequestBody  Goods goods){
        if(goodsService.updateById(goods)){
            return Result.suc(goodsService.updateById(goods));
        }else {
            return Result.fail();
        }
    }
    //    删除
    @DeleteMapping("/{id}")
    public Result deleted(@PathVariable Integer id){
        if(goodsService.removeById(id)){
            return Result.suc(goodsService.removeById(id));
        }else {
            return Result.fail();
        }
    }
    //    新增或修改
    @PostMapping("/saveOrupdate")
    public Result saveOrupdate(@RequestBody  Goods goods){
        if(goodsService.saveOrUpdate(goods)){
            return Result.suc(goodsService.saveOrUpdate(goods));
        }else {
            return Result.fail();
        }
    }
    //    模糊查询like
    @PostMapping("/listP")
    public Result listP(@RequestBody Goods goods){
        LambdaQueryWrapper<Goods> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        if(lambdaQueryWrapper.like(Goods::getName,goods.getName())!=null)
        {   return Result.suc(goodsService.list(lambdaQueryWrapper));
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
        LambdaQueryWrapper<Goods> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        if(StringUtils.isNotBlank(name) && !"null".equals(name)){
            lambdaQueryWrapper.like(Goods::getName,name);
        }

        IPage iPage = goodsService.page(page,lambdaQueryWrapper);

        return Result.suc(iPage.getRecords(),iPage.getTotal());
    }
}
