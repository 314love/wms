package com.ren.service.impl;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ren.mapper.RecordMapper;
import com.ren.service.RecordService;
import com.ren.entity.Record;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ren
 * @since 2022-11-24
 */
@Service
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record> implements RecordService {
    @Autowired
    private RecordMapper recordMapper;

    @Override
    public IPage pageC(IPage<Record> page, Wrapper wrapper) {
        return recordMapper.pageC(page,wrapper);
    }
}
