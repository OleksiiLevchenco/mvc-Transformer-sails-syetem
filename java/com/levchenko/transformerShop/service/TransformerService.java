package com.levchenko.transformerShop.service;


import com.levchenko.transformerShop.domain.Transformer;

import java.util.List;

/**
 * @author Alexey Levchenko
 */
public interface TransformerService {


    public List<Transformer> getList();

    public void delete(Integer id);

    public Transformer getById(Integer id);

    void saveOrUpdate(Transformer transformer);
}
