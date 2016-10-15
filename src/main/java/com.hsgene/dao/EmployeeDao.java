package com.hsgene.dao;

import com.hsgene.entity.Employee;

import java.util.List;

/**
 * Created by hjc on 2016/10/15.
 */
public interface EmployeeDao {

    List<Employee> findList(Employee employee);

    Employee getById(String id);
    // mybatis中的增删改操作都默认返回受影响的行数
    int insert(Employee employee);

    int update(Employee employee);

    int delete(Employee employee);
}
