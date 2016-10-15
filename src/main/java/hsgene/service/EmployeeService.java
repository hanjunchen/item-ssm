package hsgene.service;

import hsgene.common.BaseService;
import hsgene.dao.EmployeeDao;
import hsgene.entity.Employee;

import java.util.List;

/**
 * Created by hjc on 2016/10/15.
 */
public class EmployeeService extends BaseService {

    private EmployeeDao employeeDao;

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

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
