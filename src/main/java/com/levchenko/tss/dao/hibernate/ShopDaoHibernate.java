package com.levchenko.tss.dao.hibernate;


import com.levchenko.tss.dao.ShopDao;
import com.levchenko.tss.domain.Employee;
import com.levchenko.tss.domain.Shop;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Alexey Levchenko
 */

@Repository
public class ShopDaoHibernate implements ShopDao {

    @Autowired(required = true)
    private SessionFactory sessionFactory;


    @Override
    @SuppressWarnings("unchecked")
    public List<Shop> getList() {
        final Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Shop.class);
        return criteria.list();
    }

    @Override
    public Shop getById(Integer id) {
        Shop shop = (Shop) sessionFactory.getCurrentSession().load(Shop.class, id);
        Hibernate.initialize(shop);
        return shop;
    }

    @Override
    public void saveOrUpdate(Shop shop) {
        sessionFactory.getCurrentSession().saveOrUpdate(shop);
    }


    @Override
    public void delete(Integer id) {
        Shop shop = (Shop) sessionFactory.getCurrentSession().load(Shop.class, id);
        if (shop != null) {
            sessionFactory.getCurrentSession().delete(shop);
        }
    }



}
