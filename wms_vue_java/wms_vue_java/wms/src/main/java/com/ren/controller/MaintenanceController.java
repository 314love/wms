package com.ren.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ren.common.QueryPageParam;
import com.ren.common.Result;
import com.ren.entity.Maintenance;
import com.ren.service.MaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ren
 * @since 2022-11-28
 */
@RestController
@RequestMapping("/maintenance")
@CrossOrigin
public class MaintenanceController {
    //    增删改查实现
    @Autowired
    private MaintenanceService maintenanceService;
    //    查询
    @GetMapping
    public Result selextList(){
        if( Result.suc(maintenanceService.list())!=null){
            return  Result.suc(maintenanceService.list());
        }else {
            return Result.fail();
        }
    }
    @GetMapping("/{id}")
    public Result selectByid(@PathVariable Integer id){
        if(maintenanceService.getById(id)!=null){
            return Result.suc(maintenanceService.getById(id));
        }else {
            return Result.fail();
        }

    }
    //   增加
    @PostMapping()
    public Result save(@RequestBody Maintenance maintenance){
        return maintenanceService.save(maintenance)?Result.suc():Result.fail();
    }
    //    修改
    @PutMapping
    public Result update(@RequestBody Maintenance maintenance){
        if(maintenanceService.updateById(maintenance)){
            return Result.suc(maintenanceService.updateById(maintenance));
        }else {
            return Result.fail();
        }
    }
    //    删除
    @DeleteMapping("/{id}")
    public Result deleted(@PathVariable Integer id){
        if(maintenanceService.removeById(id)){
            return Result.suc(maintenanceService.removeById(id));
        }else {
            return Result.fail();
        }
    }
    //    新增或修改
    @PostMapping("/saveOrupdate")
    public Result saveOrupdate(@RequestBody Maintenance maintenance){
        if(maintenanceService.saveOrUpdate(maintenance)){
            return Result.suc(maintenanceService.saveOrUpdate(maintenance));
        }else {
            return Result.fail();
        }
    }
    //    模糊查询like
    @PostMapping("/listP")
    public Result listP(@RequestBody Maintenance maintenance){
        LambdaQueryWrapper<Maintenance> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        if(lambdaQueryWrapper.like(Maintenance::getTypeName,maintenance.getTypeName())!=null)
        {   return Result.suc(maintenanceService.list(lambdaQueryWrapper));
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
        String typeName= (String) param.get("typeName");
        LambdaQueryWrapper<Maintenance> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        if(StringUtils.isNotBlank(typeName) && !"null".equals(typeName)){
            lambdaQueryWrapper.like(Maintenance::getTypeName,typeName);
        }

        IPage iPage = maintenanceService.page(page,lambdaQueryWrapper);

        return Result.suc(iPage.getRecords(),iPage.getTotal());
    }
}
