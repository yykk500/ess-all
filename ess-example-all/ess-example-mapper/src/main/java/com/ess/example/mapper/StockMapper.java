package com.ess.example.mapper;

import com.ess.example.entity.Stock;
import com.ess.framework.mapper.AbstractMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StockMapper extends AbstractMapper<Stock> {
}