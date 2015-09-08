package com.levchenko.tss.dao;


import com.levchenko.tss.domain.Transformer;

import java.util.List;

/**
 * @author Alexey Levchenko
 */
public interface TransformerDao {


    List<Transformer> getList();

    Transformer getById(Integer id);

    void saveOrUpdate(Transformer transformer);

    void delete(Integer id);
}
