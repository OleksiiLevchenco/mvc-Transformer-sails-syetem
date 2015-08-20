package com.levchenko.transformerShop.service;


import com.levchenko.transformerShop.domain.Transformer;

import java.util.List;

/**
 * @author Alexey Levchenko
 */
public interface TransformerService {

    public void add(Transformer transformer);

    public List<Transformer> getList();

    public void remove(Integer id);

    public void update(Transformer transformer);

    public Transformer getById(Integer id);
}
