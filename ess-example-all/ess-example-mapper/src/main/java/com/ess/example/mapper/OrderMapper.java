package com.ess.example.mapper;

import com.ess.example.entity.Order;
import com.ess.framework.mapper.AbstractMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper extends AbstractMapper<Order> {
}