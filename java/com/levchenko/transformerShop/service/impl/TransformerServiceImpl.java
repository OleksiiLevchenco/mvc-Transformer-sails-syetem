package com.levchenko.transformerShop.service.impl;


import com.levchenko.transformerShop.dao.TransformerDao;
import com.levchenko.transformerShop.domain.Transformer;
import com.levchenko.transformerShop.service.TransformerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Alexey Levchenko
 */
@Service
public class TransformerServiceImpl implements TransformerService {

    @Autowired
    private TransformerDao transformerDao;

    @Override
    @Transactional
    public void add(Transformer transformer) {
        transformerDao.add(transformer);
    }

    @Override
    @Transactional
    public List<Transformer> getList() {
        return transformerDao.getList();
    }

    @Override
    @Transactional
    public void remove(Integer id) {
        transformerDao.remove(id);
    }

    @Override
    @Transactional
    public void update(Transformer transformer) {
        transformerDao.update(transformer);
    }

    @Override
    @Transactional
    public Transformer getById(Integer id) {
        return transformerDao.getById(id);
    }
}
