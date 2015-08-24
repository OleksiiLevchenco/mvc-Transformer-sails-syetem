package com.levchenko.transformerShop.controller;

import com.levchenko.transformerShop.domain.Employee;
import com.levchenko.transformerShop.domain.Shop;
import com.levchenko.transformerShop.service.EmployeeService;
import com.levchenko.transformerShop.service.ShopService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * @author Alexey Levchenko
 */

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ShopService shopService;
    @Autowired
    private Validator employeeFormValidator;
    @Autowired
    private Validator fileUploadValidator;

    private String imageFolder;

    private String defaultImageUrl;

//    Для использования аннотации @Validate
//    @InitBinder("employee")
//    //todo: Если убрать "employee" request processing failed; nested exception is java.lang.IllegalStateException: Invalid target for Validator
//    protected void initBinder(WebDataBinder binder) {
//        binder.setValidator(employeeFormValidator);
//    }

    //    get list
    @RequestMapping(value = {"*/{shopId}/employees"}, method = RequestMethod.GET)
    public String getShopsWithShops(Model model, @PathVariable("shopId") Integer shopId) {
        List<Employee> employees = employeeService.getListByShopId(shopId);
        String shopName = shopService.getById(shopId).getName();
        model.addAttribute("employees", employees);
        model.addAttribute("shopId", shopId);
        model.addAttribute("shopName", shopName);
        return "employee/employeeList";
    }

    //  show by id
    @RequestMapping(value = {"*/{shopId}/employees/{id}"}, method = RequestMethod.GET)
    public String getShowById(Model model, @PathVariable("shopId") Integer shopId, @PathVariable("id") Integer id) {

        Employee employee = employeeService.getById(id);
        Shop shop = shopService.getById(shopId);
        employee.setShop(shop);
        model.addAttribute("employeeAttribute", employee);
        model.addAttribute("shopId", shopId);
        model.addAttribute("shopName", shop.getName());
        return "employee/employeeShow";
    }

    //    new
    @RequestMapping(value = {"*/{shopId}/employees/add"}, method = RequestMethod.GET)
    public String getNew(Model model, @PathVariable("shopId") Integer shopId) {
        Employee employee = new Employee();
        employee.setShop(shopService.getById(shopId));
        model.addAttribute("employeeAttribute", employee);
        return "employee/employeeForm";
    }


    //    SAVE or UPDATE
    @RequestMapping(value = "*/{shopId}/employees", method = RequestMethod.POST)
    public String addShop(@ModelAttribute("employeeAttribute") Employee employee,
                          BindingResult bindingResult,
                          @PathVariable("shopId") Integer shopId,
                          @RequestParam("file") MultipartFile file,
                          Model model,
                          final RedirectAttributes redirectAttributes) {

        employeeFormValidator.validate(employee, bindingResult);

        boolean newEmployeeWithEmptyImgField = employee.getImgUrl() == null || employee.getImgUrl().equals("");
        boolean fileIsPresent = file.getSize() != 0;

        if (fileIsPresent) {
            fileUploadValidator.validate(file, bindingResult);
        } else {
            if (newEmployeeWithEmptyImgField) {
                employee.setImgUrl(defaultImageUrl);
            }
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("shopId", shopId);
            return "employee/employeeForm";
        } else {
            redirectAttributes.addFlashAttribute("css", "success");
            if (employee.getId() == null) {
                redirectAttributes.addFlashAttribute("msg", "employee added successful");
            } else {
                redirectAttributes.addFlashAttribute("msg", "employee updated successful");
            }

            employee.setShop(shopService.getById(shopId));
            employeeService.saveOrUpdate(employee); // todo: Achtung!!! для получения id пршлось лишний раз сохранять

            if (fileIsPresent){
                employee.setImgUrl(saveEmployeesPhoto(file, shopId, employee.getId()));
            }

            employeeService.saveOrUpdate(employee);

            return "redirect:/shops/" + shopId + "/employees/" + employee.getId();
        }
    }


    //    update
    @RequestMapping(value = "/shops/{shopId}/employees/{id}/update", method = RequestMethod.GET)
    public String getUpdate(@PathVariable("id") Integer id, @PathVariable("shopId") Integer shopId, Model model) {

        model.addAttribute("employeeAttribute", employeeService.getById(id));
        model.addAttribute("shopId", shopId);
        return "employee/employeeForm";
    }


    //    delete
    @RequestMapping(value = "/shops/{shopId}/employees/{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Integer id, Model model,
                         @PathVariable("shopId") Integer shopId,
                         RedirectAttributes redirectAttributes) {
        removeImageByEmployeeId(id);
        employeeService.remove(id);
        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", "Employee is deleted!");
        return "redirect:/shops/" + shopId + "/employees";

    }




    private String saveEmployeesPhoto(MultipartFile file, Integer shopId, Integer employeeId) {

        if (!file.isEmpty()) {

            String ext = FilenameUtils.getExtension(file.getOriginalFilename());

            File employeesDir = new File(imageFolder);
            if (!employeesDir.exists()) {
                employeesDir.mkdirs();
            }

            StringBuilder fileNameBuilder = new StringBuilder();
            fileNameBuilder
                    .append(File.separator)
                    .append(employeeId)
                    .append('.')
                    .append(ext);

            try {
                byte[] bytes = file.getBytes();

                File serveFile = new File(employeesDir.getAbsolutePath() + fileNameBuilder.toString());
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serveFile));
                stream.write(bytes);
                stream.close();

            } catch (IOException e) {
                e.printStackTrace();
                return "-1";
            }

            String imageUrl = ("/images" + fileNameBuilder.toString()).replaceAll("[\\\\]", "/");
            employeeService.setImg(imageUrl, employeeId);

            return imageUrl;
        }

        return"-1";
    }

    private boolean removeImageByEmployeeId(Integer id) {
        final String imgUrl = employeeService.getById(id).getImgUrl();
        if ( ! imgUrl.equals(defaultImageUrl)) {
            File file = new File(imageFolder + imgUrl.replace("/images", ""));
            return file.delete();
        } else return true;
    }


// getters and setters
    public String getImageFolder() {
        return imageFolder;
    }

    public void setImageFolder(String imageFolder) {
        this.imageFolder = imageFolder;
    }

    public String getDefaultImageUrl() {
        return defaultImageUrl;
    }

    public void setDefaultImageUrl(String defaultImageUrl) {
        this.defaultImageUrl = defaultImageUrl;
    }
}

