package com.hsgene.controller;

import com.hsgene.entity.Employee;
import com.hsgene.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by hjc on 2016/10/15.
 */
@Controller
public class EmployeeController {

    @Resource
    private EmployeeService employeeService;

    @RequestMapping(value = "/list")
    public String findList(Employee employee, Model model) {
        model.addAttribute("list",employeeService.findList(employee));
        return "employeeList";
    }

    @RequestMapping(value = "/testDate")
    @ResponseBody
    public Employee testDate(){
        //  日期字段默认被转为时间戳传到前台
        return employeeService.findList(new Employee()).get(0);
    }
}
