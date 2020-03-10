package com.ess.example.mapper;

import com.ess.example.entity.Goods;
import com.ess.framework.mapper.AbstractMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GoodsMapper extends AbstractMapper<Goods> {
}