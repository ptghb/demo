package com.pt.service;

import java.util.List;

/**
 * 服务基础类
 * @author gehb
 *
 * @param <T>
 */
public interface BaseService<T> {
	
	public T save(T t) throws Exception;
	
	public void delete(T user) throws Exception;
	
	public List<T> query() throws Exception;
	
	public T query(Long id) throws Exception;

	public List<T> query(Integer pageNo, Integer pageSize) throws Exception;
}
