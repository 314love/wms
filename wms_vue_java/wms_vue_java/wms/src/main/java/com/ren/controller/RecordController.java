package com.ren.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ren.common.QueryPageParam;
import com.ren.common.Result;
import com.ren.entity.Goodslist;
import com.ren.entity.Record;
import com.ren.service.GoodslistService;
import com.ren.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ren
 * @since 2022-11-24
 */
@RestController
@RequestMapping("/record")
@CrossOrigin
public class RecordController {
    @Autowired
    private RecordService recordService;
    @Autowired
    private GoodslistService goodslistService;
    @PostMapping("/listPage")
    public Result listPage(@RequestBody QueryPageParam query){
//    System.out.println(query.getPageCurrent());
//    System.out.println(query.getPageSize());
        IPage page=new Page(query.getPageCurrent(),query.getPageSize());
        HashMap param = query.getParam();
        String name= (String) param.get("name");
        String storage= (String) param.get("storage");
        String goods= (String) param.get("goods");
        String userId= (String) param.get("userId");
        String roleId= (String) param.get("roleId");
        QueryWrapper<Record> queryWrapper=new QueryWrapper<Record>();
        queryWrapper.apply(" a.goodslist=b.id and b.storage=c.id and b.goods=d.id ");
        if("2".equals(roleId)){
            queryWrapper.apply("a.userId="+userId);
        }
        if(StringUtils.isNotBlank(name) && !"null".equals(name)){
            queryWrapper.like("b.name",name);
        }
        if(StringUtils.isNotBlank(storage) && !"null".equals(storage)){
            queryWrapper.eq("c.id",storage);
        }
        if(StringUtils.isNotBlank(goods) && !"null".equals(goods)){
            queryWrapper.eq("d.id",goods);
        }

        IPage iPage = recordService.pageC(page,queryWrapper);

        return Result.suc(iPage.getRecords(),iPage.getTotal());
    }
    //   增加
    @PostMapping()
    public Result save(@RequestBody Record record){
//        先查询再修改
        Goodslist goodslist = goodslistService.getById(record.getGoodslist());
//        要加入的数量
        int n = record.getCount();
//表示出库
         if("2".equals(record.getAction())&&record.getCount()<=goodslist.getCount()){
               n=-n;
               record.setCount(n);
               int num=goodslist.getCount()+n;
               goodslist.setCount(num);
               goodslistService.updateById(goodslist);
        }else if("2".equals(record.getAction())&&record.getCount()>goodslist.getCount()){
               return Result.fail();
         }else {
              int num=goodslist.getCount()+n;
              goodslist.setCount(num);
              goodslistService.updateById(goodslist);}
        return recordService.save(record)?Result.suc():Result.fail();
    }
}
