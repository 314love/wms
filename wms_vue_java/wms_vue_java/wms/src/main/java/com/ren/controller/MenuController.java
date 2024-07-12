package com.ren.controller;


import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.ren.common.Result;
import com.ren.entity.Menu;
import com.ren.entity.User;
import com.ren.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ren
 * @since 2022-11-08
 */
@CrossOrigin//解决跨域问题
@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;
    @GetMapping("/list")
    public Result list(@RequestParam String roleId){
        List<Menu> list = menuService.lambdaQuery().like(Menu::getMenuright, roleId).list();
        return Result.suc(list);

    }


}
