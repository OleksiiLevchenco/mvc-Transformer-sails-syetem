package com.levchenko.transformerShop.service.impl;


import com.levchenko.transformerShop.dao.ShopDao;
import com.levchenko.transformerShop.domain.Employee;
import com.levchenko.transformerShop.domain.Shop;
import com.levchenko.transformerShop.service.ShopService;
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


    @Override @Transactional
    public List<Shop> getList() {
        return shopDao.getList();
    }

    @Override @Transactional
    public void saveOrUpdate(Shop shop) {
        if (shop.getId()==null) {
            shopDao.save(shop);
        } else {
            shopDao.update(shop);
        }
    }

    @Override @Transactional
    public Shop getById(Integer id) {
        return shopDao.getById(id);
    }

    @Override @Transactional
    public List<Employee> getEmployeesListByShopId(Integer id) {
        return  shopDao.getEmployeesListByShopId(id);
    }





//_______Trash____________________________________
//

    @Override
    @Transactional
    public void update(Shop shop) {
        shopDao.update(shop);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        shopDao.delete(id);
    }

    @Override
    @Transactional
    public void add(Shop shop) {
        shopDao.save(shop);
    }

    @Override
    @Transactional
    public List<Shop> getListWithEmployees() {
        return shopDao.getListWithEmployees();
    }

}



