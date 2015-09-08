package com.levchenko.tss.validator;

import com.levchenko.tss.domain.Employee;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author Alexey Levchhenko
 */
@Component
public class EmployeeFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Employee.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        Employee employee = (Employee) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotNull.employeeForm.name");

//        if (employee.getName().length() == 0){
//            errors.rejectValue("name", "NotNull.employeeForm.name");
//        }

    }
}
