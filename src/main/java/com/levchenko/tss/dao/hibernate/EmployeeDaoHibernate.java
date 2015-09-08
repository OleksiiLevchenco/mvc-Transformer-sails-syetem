package com.levchenko.tss.dao.hibernate;


import com.levchenko.tss.dao.EmployeeDao;
import com.levchenko.tss.domain.Employee;
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
    @SuppressWarnings("unchecked")
    public List<Employee> getListByShopId(Integer shopId) {
        final Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Employee.class);
        criteria.setFetchMode("shop", FetchMode.JOIN);
        criteria.add(Restrictions.eq("shop.id", shopId));
        return criteria.list();
    }


    @Override
    public Employee getById(Integer id) {
        Employee employee = (Employee) sessionFactory.getCurrentSession().load(Employee.class, id);
        Hibernate.initialize(employee.getShop());
        return employee;
    }


    @Override
    public void saveOrUpdate(Employee employee) {
        sessionFactory.getCurrentSession().saveOrUpdate(employee);
    }


    @Override
    public void setImgUrl(String imgUrl, Integer id) {
        Employee employee = (Employee) sessionFactory.getCurrentSession().load(Employee.class, id);
        employee.setImgUrl(imgUrl);
    }


    @Override
    public void delete(Integer id) {
        Employee employee = (Employee) sessionFactory.getCurrentSession().load(Employee.class, id);
        if (employee != null) {
            sessionFactory.getCurrentSession().delete(employee);
        }
    }



}
