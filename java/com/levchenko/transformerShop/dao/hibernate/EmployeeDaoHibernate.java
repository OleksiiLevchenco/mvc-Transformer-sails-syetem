package com.levchenko.transformerShop.dao.hibernate;


import com.levchenko.transformerShop.dao.EmployeeDao;
import com.levchenko.transformerShop.domain.Employee;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Alexey Levchenko
 */

@Repository
public class EmployeeDaoHibernate implements EmployeeDao {

    @Autowired(required = true)
    private SessionFactory sessionFactory;

    @Override
    public void save(Employee employee) {
        sessionFactory.getCurrentSession().save(employee);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Employee> getList() {
        return sessionFactory.getCurrentSession()
                .createQuery("from com.levchenko.transformerShop.domain.Employee").list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Employee> getListWithShops() {
        final Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Employee.class);
        criteria.setFetchMode("shop", FetchMode.JOIN);
        return criteria.list();
    }

    @Override
    public void setImg(String img, Integer id) {
        Employee employee = (Employee) sessionFactory.getCurrentSession().load(Employee.class, id);
        employee.setImgUrl(img);
    }


    @Override
    @SuppressWarnings("unchecked")
    public List<Employee> getListByShopId(Integer shopId) {
        final Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Employee.class);
        criteria.setFetchMode("shop", FetchMode.JOIN);
        criteria.add(Restrictions.eq("shop.id", shopId));
        return criteria.list();
    }

    @Override
    public void remove(Integer id) {
        Employee employee = (Employee) sessionFactory.getCurrentSession().load(Employee.class, id);
        if (employee != null) {
            sessionFactory.getCurrentSession().delete(employee);
        }
    }

    @Override
    public Employee getById(Integer id) {
        Employee employee = (Employee) sessionFactory.getCurrentSession().load(Employee.class, id);
        Hibernate.initialize(employee.getShop());
        return employee;
    }

    @Override
    public void update(Employee employee) {
        sessionFactory.getCurrentSession().update(employee);
    }



}
