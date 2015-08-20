package com.levchenko.transformerShop.dao;


import com.levchenko.transformerShop.domain.Employee;
import com.levchenko.transformerShop.domain.Shop;

import java.util.List;

/**
 * @author Alexey Levchenko
 */
public interface ShopDao {

    public void save(Shop shop);

    public void update(Shop shop);

    public void delete(Integer id);

    public Shop getById(Integer id);

    public List<Shop> getList();

    List<Shop> getListWithEmployees();

    public List<Employee> getEmployeesListByShopId(Integer id);
}
