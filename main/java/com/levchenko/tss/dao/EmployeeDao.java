package com.levchenko.tss.dao;


import com.levchenko.tss.domain.Employee;

import java.util.List;

/**
 * @author Alexey Levchenko
 */
public interface EmployeeDao {


    List<Employee> getListByShopId(Integer shopId);

    Employee getById(Integer id);

    void saveOrUpdate(Employee employee);

    void setImgUrl(String imgUrl, Integer id);

    void delete(Integer id);

}
