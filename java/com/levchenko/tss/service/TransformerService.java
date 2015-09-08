package com.levchenko.tss.service;


import com.levchenko.tss.domain.Transformer;

import java.util.List;

/**
 * @author Alexey Levchenko
 */
public interface TransformerService {


    List<Transformer> getList();

    void delete(Integer id);

    Transformer getById(Integer id);

    void saveOrUpdate(Transformer transformer);
}
