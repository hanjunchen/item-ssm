package com.hsgene.service;

import com.hsgene.common.BaseService;
import com.hsgene.dao.EmployeeDao;
import com.hsgene.entity.Employee;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by hjc on 2016/10/15.
 */
@Service
public class EmployeeService extends BaseService {

    // idea自动开启autowiring功能，会和注解注入重复，在setting中关闭；或者自定义一个注解加到dao上面，在MapperScannerConfigurer上引用
    @Resource
    private EmployeeDao employeeDao;

    @Override
    public List<Employee> findList(Employee employee) {
        return employeeDao.findList(employee);
    }

    @Override
    public Employee getById(String id) {
        return employeeDao.getById(id);
    }

    @Override
    public int insert(Employee employee) {
        return employeeDao.insert(employee);
    }

    @Override
    public int update(Employee employee) {
        return employeeDao.update(employee);
    }

    @Override
    public int delete(Employee employee) {
        return employeeDao.delete(employee);
    }
}
