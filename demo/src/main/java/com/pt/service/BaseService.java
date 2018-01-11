package com.pt.service;

import java.util.List;

/**
 * 服务基础类
 * @author gehb
 *
 * @param <T>
 */
public interface BaseService<T> {
	
	public T save(T t);
	
	public void delete(T user);
	
	public List<T> query();
	
	public T query(Long id);

	public List<T> query(Integer pageNo, Integer pageSize);
}
