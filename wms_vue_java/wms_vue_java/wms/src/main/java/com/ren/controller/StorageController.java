package com.ren.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ren.common.QueryPageParam;
import com.ren.common.Result;
import com.ren.entity.Storage;
import com.ren.service.StorageService;
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
@RequestMapping("/storage")
@CrossOrigin//解决跨域问题
public class StorageController {
    //    增删改查实现
    @Autowired
    private StorageService storageService;
    //    查询
    @GetMapping
    public Result selextList(){
        if( Result.suc(storageService.list())!=null){
            return  Result.suc(storageService.list());
        }else {
            return Result.fail();
        }
    }
    @GetMapping("/{id}")
    public Result selectByid(@PathVariable Integer id){
        if(storageService.getById(id)!=null){
            return Result.suc(storageService.getById(id));
        }else {
            return Result.fail();
        }

    }
    //   增加
    @PostMapping()
    public Result save(@RequestBody Storage storage){
        return storageService.save(storage)?Result.suc():Result.fail();
    }
    //    修改
    @PutMapping
    public Result update(@RequestBody Storage storage){
        if(storageService.updateById(storage)){
            return Result.suc(storageService.updateById(storage));
        }else {
            return Result.fail();
        }
    }
    //    删除
    @DeleteMapping("/{id}")
    public Result deleted(@PathVariable Integer id){
        if(storageService.removeById(id)){
            return Result.suc(storageService.removeById(id));
        }else {
            return Result.fail();
        }
    }
    //    新增或修改
    @PostMapping("/saveOrupdate")
    public Result saveOrupdate(@RequestBody Storage storage){
        if(storageService.saveOrUpdate(storage)){
            return Result.suc(storageService.saveOrUpdate(storage));
        }else {
            return Result.fail();
        }
    }
    //    模糊查询like
    @PostMapping("/listP")
    public Result listP(@RequestBody Storage storage){
        LambdaQueryWrapper<Storage> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        if(lambdaQueryWrapper.like(Storage::getName,storage.getName())!=null)
        {   return Result.suc(storageService.list(lambdaQueryWrapper));
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
        LambdaQueryWrapper<Storage> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        if(StringUtils.isNotBlank(name) && !"null".equals(name)){
            lambdaQueryWrapper.like(Storage::getName,name);
        }

        IPage iPage = storageService.page(page,lambdaQueryWrapper);

        return Result.suc(iPage.getRecords(),iPage.getTotal());
    }
}
