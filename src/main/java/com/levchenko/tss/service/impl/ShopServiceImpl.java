package com.levchenko.tss.service.impl;


import com.levchenko.tss.dao.EmployeeDao;
import com.levchenko.tss.dao.ShopDao;
import com.levchenko.tss.domain.Employee;
import com.levchenko.tss.domain.Shop;
import com.levchenko.tss.service.ShopService;
import com.levchenko.tss.service.impl.exceptions.DeletingShopWithEmployeesException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Alexey Levchenko
 */
@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopDao shopDao;

    @Autowired
    private EmployeeDao employeeDao;


    @Override
    @Transactional
    public List<Shop> getList() {
        return shopDao.getList();
    }


    @Override
    @Transactional
    public void saveOrUpdate(Shop shop) {
        shopDao.saveOrUpdate(shop);
    }

    @Override
    @Transactional
    public Shop getById(Integer id) {
        return shopDao.getById(id);
    }


    @Override
    @Transactional
    public void delete(Integer id) {
        final List<Employee> employees = employeeDao.getListByShopId(id);
        if (employees.size() <= 0) {
            shopDao.delete(id);
        } else {
            throw new DeletingShopWithEmployeesException(id, "You can't delete the store, first delete all employees of the shop.");
        }
    }


}



