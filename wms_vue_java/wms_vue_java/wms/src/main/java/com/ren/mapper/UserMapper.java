package com.ren.mapper;

import com.ren.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ren
 * @since 2022-11-01
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
