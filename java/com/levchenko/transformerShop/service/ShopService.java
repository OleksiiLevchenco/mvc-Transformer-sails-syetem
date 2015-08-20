package com.levchenko.transformerShop.service;


import com.levchenko.transformerShop.domain.Employee;
import com.levchenko.transformerShop.domain.Shop;

import java.util.List;

/**
 * @author Alexey Levchenko
 */
public interface ShopService {

    public void add(Shop shop);

    public List<Shop> getList();

    public void delete(Integer id);

    public void update(Shop shop);

    public Shop getById(Integer id);

    public List<Employee> getEmployeesListByShopId(Integer id);

    List<Shop> getListWithEmployees();

    void saveOrUpdate(Shop shop);
}
