package com.levchenko.tss.dao;


import com.levchenko.tss.domain.Employee;
import com.levchenko.tss.domain.Shop;

import java.util.List;

/**
 * @author Alexey Levchenko
 */
public interface ShopDao {


    List<Shop> getList();

    Shop getById(Integer id);

    void saveOrUpdate(Shop shop);

    void delete(Integer id);

}
