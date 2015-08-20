package com.levchenko.transformerShop.dao;


import com.levchenko.transformerShop.domain.Transformer;

import java.util.List;

/**
 * @author Alexey Levchenko
 */
public interface TransformerDao {

    public void add(Transformer transformer);

    public List<Transformer> getList();

    public void remove(Integer id);

    public Transformer getById(Integer id);

    public void update(Transformer transformer);
}
