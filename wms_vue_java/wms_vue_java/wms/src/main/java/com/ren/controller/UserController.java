package com.ren.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ren.common.QueryPageParam;
import com.ren.common.Result;
import com.ren.entity.Menu;
import com.ren.entity.User;
import com.ren.service.MenuService;
import com.ren.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin//解决跨域问题
public class UserController {
//    增删改查实现
    @Autowired
   private UserService userService;
    @Autowired
    private MenuService menuService;

//    查询
    @GetMapping
    public Result selextList(){
        if( Result.suc(userService.list())!=null){
            return  Result.suc(userService.list());
        }else {
            return Result.fail();
        }
    }
    @GetMapping("/{id}")
    public Result selectByid(@PathVariable Integer id){
       if(userService.getById(id)!=null){
           return Result.suc(userService.getById(id));
       }else {
           return Result.fail();
       }

    }
//   增加
    @PostMapping()
    public Result save(@RequestBody User user){
        return userService.save(user)?Result.suc():Result.fail();
    }
//    修改
    @PutMapping
    public Result update(@RequestBody User user){
       if(userService.updateById(user)){
           return Result.suc(userService.updateById(user));
       }else {
           return Result.fail();
       }
    }
//    删除
    @DeleteMapping("/{id}")
    public Result deleted(@PathVariable Integer id){
         if(userService.removeById(id)){
             return Result.suc(userService.removeById(id));
         }else {
             return Result.fail();
         }
    }
//    新增或修改
    @PostMapping("/saveOrupdate")
    public Result saveOrupdate(@RequestBody User user){
        if(userService.saveOrUpdate(user)){
            return Result.suc(userService.saveOrUpdate(user));
        }else {
            return Result.fail();
        }
    }
//    模糊查询like
    @PostMapping("/listP")
    public Result listP(@RequestBody User user){
        LambdaQueryWrapper<User> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        if(lambdaQueryWrapper.like(User::getName,user.getName())!=null)
        {   return Result.suc(userService.list(lambdaQueryWrapper));
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
    String sex= (String) param.get("sex");
    String roleId = (String) param.get("roleId");
 //   查询条件
   // 判断参数：是否不为空，长度是否不为0，值是否不包含空白字符。
// * StringUtils.isNotBlank(null)  -> false
//            * StringUtils.isNotBlank("")  -> false
//            * StringUtils.isNotBlank(" ")  -> false
//            * StringUtils.isNotBlank(" ")  -> false
//            * StringUtils.isNotBlank("\t \n \f \r")  -> false
//            * StringUtils.isNotBlank("\b")  -> true
//            * StringUtils.isNotBlank("bob")  -> true
//            * StringUtils.isNotBlank(" bob ")  -> true
//    对于制表符、换行符、换页符和回车符StringUtils.isBlank()均识为空白符
//            * StringUtils.isBlank("\t \n \f \r")  -> true
//            *
// * \b 为单词边界符,不识别为空白符


    LambdaQueryWrapper<User> lambdaQueryWrapper=new LambdaQueryWrapper<>();
    if(StringUtils.isNotBlank(name) && !"null".equals(name)){
        lambdaQueryWrapper.like(User::getName,name);
    }
    if(StringUtils.isNotBlank(sex)){
        lambdaQueryWrapper.eq(User::getSex,sex);
    }
    if(StringUtils.isNotBlank(roleId)){
        lambdaQueryWrapper.eq(User::getRoleId,roleId);
    }
    IPage iPage = userService.page(page,lambdaQueryWrapper);
//    HashMap param = query.getParam();
//    System.out.println(param.get("name"));
//    System.out.println(param.get("no"));
    return Result.suc(iPage.getRecords(),iPage.getTotal());
}
//账号唯一性验证
    @GetMapping("/findByNo")
    public Result findByNo(@RequestParam String no){
        List<User> list = userService.lambdaQuery().eq(User::getNo, no).list();
        return list.size()>0?Result.suc(list): Result.fail();
    }
//    登录
    @PostMapping("/login")
public Result login(@RequestBody User user) {
        List<User> list = userService.lambdaQuery().eq(User::getNo, user.getNo())
                .eq(User::getPassword, user.getPassword()).list();
        if (list.size() > 0) {
            User user1 = list.get(0);
            List menuList=menuService.lambdaQuery().like(Menu::getMenuright, user1.getRoleId()).list();
            HashMap map = new HashMap();
            map.put("user", user1);
            map.put("menu", menuList);
            return Result.suc(map);
        }else {
            return Result.fail();
        }

//        list里面只有1条数据或者没有数据，list.get(0)表示第一1条数据，这样写才会使前端动态获取名字简单===》user:Object
    }
}
