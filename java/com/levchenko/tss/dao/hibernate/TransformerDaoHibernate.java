package com.levchenko.tss.dao.hibernate;


import com.levchenko.tss.dao.TransformerDao;
import com.levchenko.tss.domain.Transformer;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Alexey Levchenko
 */

@Repository
public class TransformerDaoHibernate implements TransformerDao {

    @Autowired(required = true)
    private SessionFactory sessionFactory;



    @Override
    @SuppressWarnings("unchecked")
    public List<Transformer> getList() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Transformer.class);
        return criteria.list();
    }

    @Override
    public Transformer getById(Integer id) {
        Transformer transformer = (Transformer) sessionFactory.getCurrentSession().load(Transformer.class, id);
        Hibernate.initialize(transformer);
        return transformer;
    }

    @Override
    public void saveOrUpdate(Transformer transformer) {
        sessionFactory.getCurrentSession().saveOrUpdate(transformer);
    }


    @Override
    public void delete(Integer id) {
        Transformer transformer = (Transformer) sessionFactory.getCurrentSession().load(Transformer.class, id);
        if (transformer != null) {
            sessionFactory.getCurrentSession().delete(transformer);
        }
    }


}
