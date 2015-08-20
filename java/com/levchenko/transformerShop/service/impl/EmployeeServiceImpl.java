package com.levchenko.transformerShop.service.impl;


import com.levchenko.transformerShop.dao.EmployeeDao;
import com.levchenko.transformerShop.domain.Employee;
import com.levchenko.transformerShop.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Alexey Levchenko
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    @Override
    @Transactional
    public void add(Employee employee) {
        employeeDao.save(employee);
    }

    @Override
    @Transactional
    public List<Employee> getList() {
        return employeeDao.getList();
    }

    @Override
    @Transactional
    public List<Employee> getListByShopId(Integer shopId) {
        return  employeeDao.getListByShopId(shopId);
    }

    @Override
    @Transactional
    public List<Employee> getListWithShops() {
        return employeeDao.getListWithShops();
    }

    @Override
    @Transactional
    public void saveOrUpdate(Employee employee) {
        if (employee.getId() != null){
            employeeDao.update(employee);
        } else {
            employeeDao.save(employee);
        }

    }

    @Override
    @Transactional
    public void remove(Integer id) {
        employeeDao.remove(id);
    }

    @Override
    @Transactional
    public void update(Employee employee) {
        employeeDao.update(employee);
    }

    @Override
    @Transactional
    public Employee getById(Integer id) {
        return employeeDao.getById(id);
    }

}


