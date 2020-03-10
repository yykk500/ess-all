package com.ess.framework.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ess.framework.mapper.AbstractMapper;

/**
 * Service基类，所有Service需要继承此类
 * @author Luozelin
 */
public abstract class AbstractService<M extends AbstractMapper<T>,T>  implements AbstractMapper<T>{
	
	/** Logger used by this class. Available to subclasses. */
//	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
    @Autowired
    protected M baseMapper;

	public M getBaseMapper() {
		return baseMapper;
	}
	
	@Override
	public T selectOne(T record) {
		return baseMapper.selectOne(record);
	}
	
	@Override
	public List<T> select(T record) {
		return baseMapper.select(record);
	}

	@Override
	public List<T> selectAll() {
		return baseMapper.selectAll();
	}

	@Override
	public int selectCount(T record) {
		return baseMapper.selectCount(record);
	}

	@Override
	public T selectByPrimaryKey(Object key) {
		return baseMapper.selectByPrimaryKey(key);
	}

	@Override
	public boolean existsWithPrimaryKey(Object key) {
		return baseMapper.existsWithPrimaryKey(key);
	}

	@Override
	@Transactional
	public int insert(T record) {
		return baseMapper.insert(record);
	}

	@Override
	@Transactional
	public int insertSelective(T record) {
		return baseMapper.insertSelective(record);
	}

	@Override
	@Transactional
	public int updateByPrimaryKey(T record) {
		return baseMapper.updateByPrimaryKey(record);
	}

	@Override
	@Transactional
	public int updateByPrimaryKeySelective(T record) {
		return baseMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	@Transactional
	public int delete(T record) {
		return baseMapper.delete(record);
	}

	@Override
	@Transactional
	public int deleteByPrimaryKey(Object key) {
		return baseMapper.deleteByPrimaryKey(key);
	}

	@Override
	public List<T> selectByExample(Object example) {
		return baseMapper.selectByExample(example);
	}

	@Override
	public T selectOneByExample(Object example) {
		return baseMapper.selectOneByExample(example);
	}

	@Override
	public int selectCountByExample(Object example) {
		return  baseMapper.selectCountByExample(example);
	}

	@Override
	@Transactional
	public int deleteByExample(Object example) {
		return baseMapper.deleteByExample(example);
	}

	@Override
	@Transactional
	public int updateByExample(T record, Object example) {
		return baseMapper.updateByExample(record,example);
	}

	@Override
	@Transactional
	public int updateByExampleSelective(T record, Object example) {
		return baseMapper.updateByExample(record,example);
	}

	@Override
	public List<T> selectByExampleAndRowBounds(Object example, RowBounds rowBounds) {
		return baseMapper.selectByExampleAndRowBounds(example,rowBounds);
	}

	@Override
	public List<T> selectByRowBounds(T record, RowBounds rowBounds) {
		return baseMapper.selectByExampleAndRowBounds(record, rowBounds);
	}

}