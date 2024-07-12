package com.ren.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ren.entity.Record;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ren
 * @since 2022-11-24
 */
public interface RecordService extends IService<Record> {
 IPage pageC(IPage<Record> page, Wrapper wrapper);
}
