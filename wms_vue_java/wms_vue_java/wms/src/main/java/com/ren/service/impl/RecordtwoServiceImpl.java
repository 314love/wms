package com.ren.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ren.entity.Record;
import com.ren.entity.Recordtwo;
import com.ren.mapper.RecordMapper;
import com.ren.mapper.RecordtwoMapper;
import com.ren.service.RecordtwoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ren
 * @since 2022-11-28
 */
@Service
public class RecordtwoServiceImpl extends ServiceImpl<RecordtwoMapper, Recordtwo> implements RecordtwoService {
    @Autowired
    private RecordtwoMapper recordtwoMapper;

    @Override
    public IPage pageC(IPage<Recordtwo> page, Wrapper wrapper) {
        return recordtwoMapper.pageC(page,wrapper);
    }
}
