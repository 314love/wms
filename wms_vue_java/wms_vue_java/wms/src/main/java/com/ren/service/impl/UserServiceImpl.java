package com.ren.service.impl;

import com.ren.entity.User;
import com.ren.mapper.UserMapper;
import com.ren.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ren
 * @since 2022-11-01
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
