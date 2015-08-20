package com.levchenko.transformerShop.dao;


import com.levchenko.transformerShop.domain.Employee;

import java.util.List;

/**
 * @author Alexey Levchenko
 */
public interface EmployeeDao {

    public void save(Employee employee);

    public List<Employee> getList();

    public List<Employee> getListByShopId(Integer shopId);

    public void remove(Integer id);

    public Employee getById(Integer id);

    public void update(Employee employee);

    List<Employee> getListWithShops();

    void setImg(String img, Integer id);
}
