package com.levchenko.transformerShop.dao.hibernate;


import com.levchenko.transformerShop.dao.TransformerDao;
import com.levchenko.transformerShop.domain.Transformer;
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
    public void add(Transformer transformer) {
        sessionFactory.getCurrentSession().save(transformer);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Transformer> getList() {
        return sessionFactory.getCurrentSession()
                .createQuery("from com.levchenko.transformerShop.domain.Transformer").list();
    }

    @Override
    public void remove(Integer id) {
        Transformer transformer = (Transformer) sessionFactory.getCurrentSession().load(Transformer.class, id);
        if (transformer != null) {
            sessionFactory.getCurrentSession().delete(transformer);
        }
    }

    @Override
    public Transformer getById(Integer id) {
        Transformer transformer = (Transformer) sessionFactory.getCurrentSession().load(Transformer.class, id);
        System.out.println(transformer); // if delete it go to org.hibernate.LazyInitializationException: could not initialize proxy - no Session
        return transformer;
    }

    @Override
    public void update(Transformer transformer) {
        sessionFactory.getCurrentSession().update(transformer);
    }


}
