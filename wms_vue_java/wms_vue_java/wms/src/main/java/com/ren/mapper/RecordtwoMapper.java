package com.ren.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.ren.entity.Recordtwo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ren
 * @since 2022-11-28
 */
@Mapper
public interface RecordtwoMapper extends BaseMapper<Recordtwo> {

    IPage pageC(IPage<Recordtwo> page, @Param(Constants.WRAPPER) Wrapper wrapper);
}
