package com.ren.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ren.common.QueryPageParam;
import com.ren.common.Result;
import com.ren.entity.Maintenance;
import com.ren.entity.Record;
import com.ren.entity.Recordtwo;
import com.ren.service.MaintenanceService;
import com.ren.service.RecordtwoService;
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
@RequestMapping("/recordtwo")
@CrossOrigin
public class RecordtwoController {
    @Autowired
    private MaintenanceService maintenanceService;
    @Autowired
    private RecordtwoService recordtwoService;
    @PostMapping("/listPage")
    public Result listPage(@RequestBody QueryPageParam query){
//    System.out.println(query.getPageCurrent());
//    System.out.println(query.getPageSize());
        IPage page=new Page(query.getPageCurrent(),query.getPageSize());
        HashMap param = query.getParam();
        String typeName= (String) param.get("typeName");
        String userId= (String) param.get("userId");
        String roleId= (String) param.get("roleId");
        QueryWrapper<Recordtwo> queryWrapper=new QueryWrapper<Recordtwo>();
        queryWrapper.apply(" a.maintenance=m.id ");
        if("2".equals(roleId)){
            queryWrapper.apply("a.userId="+userId);
        }
        if(StringUtils.isNotBlank(typeName) && !"null".equals(typeName)){
            queryWrapper.like("m.type_name",typeName);
        }
//        if(StringUtils.isNotBlank(storage) && !"null".equals(storage)){
//            queryWrapper.eq("c.id",storage);
//        }
//        if(StringUtils.isNotBlank(goods) && !"null".equals(goods)){
//            queryWrapper.eq("d.id",goods);
//        }

        IPage iPage = recordtwoService.pageC(page,queryWrapper);

        return Result.suc(iPage.getRecords(),iPage.getTotal());
    }


    @PostMapping()
    public Result save(@RequestBody Recordtwo recordtwo){
//        先查询再修改
        Maintenance maintenance = maintenanceService.getById(recordtwo.getMaintenance());
//        要减去的数量
        int n = recordtwo.getCount();
        n=-n;
        recordtwo.setCount(n);
        Integer num=maintenance.getCount()+n;
        if(num>=0&&recordtwo.getCount()<=maintenance.getCount())
        {
            maintenance.setCount(num);
            maintenanceService.updateById(maintenance);
            return recordtwoService.save(recordtwo)?Result.suc():Result.fail();
        }else {
            return Result.fail();
        }
    }
}
