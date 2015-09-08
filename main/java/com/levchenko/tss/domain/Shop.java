package com.levchenko.tss.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

//import org.hibernate.annotations.GenericGenerator;

/**
 * @author Alexey Levchenko
 */
@Entity
@Table(name = "shops")
public class Shop {

@Id
@GeneratedValue
@Column(name = "id")
    private Integer id;

    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "name")
    private String name;

    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "address")
    private String address;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "tel")
    private String tel;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "working_time")
    private String workingTime;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "shop", cascade = CascadeType.REMOVE)
    private List<Employee> employeeList = new ArrayList<>(0);

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getWorkingTime() {
        return workingTime;
    }

    public void setWorkingTime(String workingTime) {
        this.workingTime = workingTime;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeSet) {
        this.employeeList = employeeSet;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", tel='" + tel + '\'' +
                ", workingTime='" + workingTime + '\'' +
                '}';
    }
}
