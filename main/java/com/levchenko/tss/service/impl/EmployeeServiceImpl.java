package com.levchenko.tss.service.impl;


import com.levchenko.tss.service.EmployeeService;
import com.levchenko.tss.dao.EmployeeDao;
import com.levchenko.tss.dao.hibernate.ImageDao;
import com.levchenko.tss.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author Alexey Levchenko
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private ImageDao imageDao;


    @Override
    @Transactional
    public List<Employee> getListByShopId(Integer shopId) {
        return employeeDao.getListByShopId(shopId);
    }

    @Override
    @Transactional
    public void saveOrUpdate(Employee employee) {
        employeeDao.saveOrUpdate(employee);
    }

    @Override
    @Transactional
    public Employee getById(Integer id) {
        return employeeDao.getById(id);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        deleteImage(id);
        employeeDao.delete(id);
    }

    @Override
    @Transactional
    public void setImgUrl(String img, Integer id) {
        employeeDao.setImgUrl(img, id);
    }

    @Override
    @Transactional
    public String saveImage(MultipartFile file, Integer employeeId) {
        return imageDao.saveImage(file, employeeId);
    }

    @Override
    public boolean deleteImage(Integer id) {
        return imageDao.deleteImage(id);
    }


}


