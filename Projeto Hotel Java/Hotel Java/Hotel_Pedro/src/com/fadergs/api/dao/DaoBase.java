package com.fadergs.api.dao;

import java.util.List;


public interface DaoBase<T> {
	T insert (T object) throws Exception;      
	T update(T object) throws Exception;
	void delete(int id) throws Exception;
	T findById(int id);
	List<T> findAll();




}
