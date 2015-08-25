package com.levchenko.transformerShop.service;


import com.levchenko.transformerShop.domain.Employee;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author Alexey Levchenko
 */
public interface EmployeeService {

    public void add(Employee employee);

    public List<Employee> getList();

    public List<Employee> getListByShopId(Integer shopId);

    public void remove(Integer id);

    public void update(Employee employee);

    public Employee getById(Integer id);

    List<Employee> getListWithShops();

    void saveOrUpdate(Employee employee);

    void setImgUrl(String img, Integer id);

    String saveImage(MultipartFile file, Integer employeeId);

    boolean removeImage(Integer id);
}
