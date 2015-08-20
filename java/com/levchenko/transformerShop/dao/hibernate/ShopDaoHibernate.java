package com.levchenko.transformerShop.dao.hibernate;


import com.levchenko.transformerShop.dao.ShopDao;
import com.levchenko.transformerShop.domain.Employee;
import com.levchenko.transformerShop.domain.Shop;
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
    public void save(Shop shop) {
        sessionFactory.getCurrentSession().save(shop);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Shop> getList() {
        return sessionFactory.getCurrentSession()
                .createQuery("from com.levchenko.transformerShop.domain.Shop").list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Shop> getListWithEmployees() {
        final Session session = sessionFactory.getCurrentSession();
        final Criteria criteria = session.createCriteria(Shop.class);
        criteria.setFetchMode("employeeList", FetchMode.JOIN);
        return criteria.list();
    }

    @Override
    public void delete(Integer id) {
        Shop shop = (Shop) sessionFactory.getCurrentSession().load(Shop.class, id);
        if (shop != null) {
            sessionFactory.getCurrentSession().delete(shop);
        }
    }

    @Override
    public Shop getById(Integer id) {
        Shop shop = (Shop) sessionFactory.getCurrentSession().load(Shop.class, id);
        Hibernate.initialize(shop);
        return shop;
    }

    @Override
    public void update(Shop shop) {
        sessionFactory.getCurrentSession().update(shop);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Employee> getEmployeesListByShopId(Integer id) {

        final Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Shop.class);
        criteria.add(Restrictions.eq("id", id));
        criteria.setFetchMode("employeeList", FetchMode.JOIN);
        Shop shop = (Shop)criteria.uniqueResult();
        return shop.getEmployeeList();
    }

}
