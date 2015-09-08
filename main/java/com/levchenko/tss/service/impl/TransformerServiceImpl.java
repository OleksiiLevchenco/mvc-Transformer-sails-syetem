package com.levchenko.tss.service.impl;


import com.levchenko.tss.service.TransformerService;
import com.levchenko.tss.dao.TransformerDao;
import com.levchenko.tss.domain.Transformer;
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
    public List<Transformer> getList() {
        return transformerDao.getList();
    }

    @Override
    @Transactional
    public Transformer getById(Integer id) {
        return transformerDao.getById(id);
    }


    @Override
    @Transactional
    public void saveOrUpdate(Transformer transformer) {
        transformerDao.saveOrUpdate(transformer);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        transformerDao.delete(id);
    }
}
