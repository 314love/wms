package com.ren.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ren.entity.Record;
import com.ren.entity.Recordtwo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ren
 * @since 2022-11-28
 */
public interface RecordtwoService extends IService<Recordtwo> {
    IPage pageC(IPage<Recordtwo> page, Wrapper wrapper);
}
