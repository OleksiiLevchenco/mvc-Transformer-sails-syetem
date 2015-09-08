package com.levchenko.tss.service;


import com.levchenko.tss.domain.Employee;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author Alexey Levchenko
 */
public interface EmployeeService {


    List<Employee> getListByShopId(Integer shopId);

    void delete(Integer id);

    Employee getById(Integer id);

    void saveOrUpdate(Employee employee);

    void setImgUrl(String img, Integer id);

    String saveImage(MultipartFile file, Integer employeeId);

    boolean deleteImage(Integer id);
}
