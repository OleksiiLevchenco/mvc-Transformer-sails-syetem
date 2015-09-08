package com.levchenko.tss.controller;

import com.levchenko.tss.service.EmployeeService;
import com.levchenko.tss.service.ShopService;
import com.levchenko.tss.domain.Employee;
import com.levchenko.tss.domain.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author Alexey Levchenko
 */

@Controller
@RequestMapping(value = "/shops/{shopId}/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ShopService shopService;
    @Autowired
    private Validator employeeFormValidator;
    @Autowired
    private Validator fileUploadValidator;
    @Autowired
    private String defaultImageUrl;

    private static final String EMPLOYEE_FORM = "employee/employeeForm";
    private static final String EMPLOYEE_LIST = "employee/employeeList";
    private static final String EMPLOYEE_SHOW = "employee/employeeShow";
    private static final String SHOP_REDIRECT = "redirect:/shops/";


    //    get list
    @RequestMapping(value = {""}, method = RequestMethod.GET)
    public String getEmployeesWithShops(Model model, @PathVariable("shopId") Integer shopId) {

        model.addAttribute("employees", employeeService.getListByShopId(shopId));
        model.addAttribute("shop", shopService.getById(shopId));

        return EMPLOYEE_LIST;
    }

    //  show by id
    @RequestMapping(value = {"{id}"}, method = RequestMethod.GET)
    public String getShowById(Model model, @PathVariable("shopId") Integer shopId, @PathVariable("id") Integer id) {

        Employee employee = employeeService.getById(id);
        Shop shop = shopService.getById(shopId);
        employee.setShop(shop);
        model.addAttribute("employeeAttribute", employee);
        model.addAttribute("shop", shop);

        return EMPLOYEE_SHOW;
    }

    //    New
    @RequestMapping(value = {"add"}, method = RequestMethod.GET)
    public String getNew(Model model, @PathVariable("shopId") Integer shopId) {

        Employee employee = new Employee();
        employee.setShop(shopService.getById(shopId));
        model.addAttribute("employeeAttribute", employee);

        return EMPLOYEE_FORM;
    }


    //    SAVE or UPDATE
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String addShop(@ModelAttribute("employeeAttribute") Employee employee,
                          BindingResult bindingResult,
                          @PathVariable("shopId") Integer shopId,
                          @RequestParam("file") MultipartFile file,
                          Model model,
                          final RedirectAttributes redirectAttributes) {

        employeeFormValidator.validate(employee, bindingResult);

        boolean newEmployeeWithoutImg = employee.getImgUrl() == null || employee.getImgUrl().equals("");
        boolean fileIsPresent = file.getSize() != 0;

        if (fileIsPresent) {
            fileUploadValidator.validate(file, bindingResult);
        } else if (newEmployeeWithoutImg) {
            employee.setImgUrl(defaultImageUrl);
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("shopId", shopId);
            return EMPLOYEE_FORM;
        } else {
            redirectAttributes.addFlashAttribute("css", "success");
            if (employee.getId() == null) {
                redirectAttributes.addFlashAttribute("msg", "employee added successful");
            } else {
                redirectAttributes.addFlashAttribute("msg", "employee updated successful");
            }

            employee.setShop(shopService.getById(shopId));
            employeeService.saveOrUpdate(employee);
            if (fileIsPresent){
                employee.setImgUrl(employeeService.saveImage(file, employee.getId()));
            }
            employeeService.saveOrUpdate(employee);

            return SHOP_REDIRECT + shopId + "/employees/" + employee.getId();
        }
    }


    //    Update
    @RequestMapping(value = "{id}/update", method = RequestMethod.GET)
    public String getUpdate(@PathVariable("id") Integer id, @PathVariable("shopId") Integer shopId, Model model) {

        model.addAttribute("employeeAttribute", employeeService.getById(id));
        model.addAttribute("shopId", shopId);
        return EMPLOYEE_FORM;
    }


    //    Delete
    @RequestMapping(value = "{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Integer id, Model model,
                         @PathVariable("shopId") Integer shopId,
                         RedirectAttributes redirectAttributes) {
        employeeService.delete(id);
        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", "Employee is deleted!");
        return SHOP_REDIRECT + shopId + "/employees";

    }

}

