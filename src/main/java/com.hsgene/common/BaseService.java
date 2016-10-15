package com.hsgene.common;

import com.hsgene.entity.Employee;

import java.util.List;

/**
 * Created by hjc on 2016/10/15.
 */
public abstract class BaseService {

    protected abstract List<Employee> findList(Employee employee);

    protected abstract Employee getById(String id);

    protected abstract int insert(Employee employee);

    protected abstract int update(Employee employee);

    protected abstract int delete(Employee employee);
}
