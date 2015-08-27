package com.levchenko.transformerShop.controller;

import com.levchenko.transformerShop.service.impl.exceptions.DeletingShopWithEmployeesException;
import com.levchenko.transformerShop.service.impl.exceptions.MyException;
import com.levchenko.transformerShop.domain.Shop;
import com.levchenko.transformerShop.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @author Alexey Levchenko
 */
@Controller
public class ShopController {

    @Autowired
    private ShopService shopService;


    //    show List
    @RequestMapping(value = {"/shops"}, method = RequestMethod.GET)
    public String getShops(Model model) {

        model.addAttribute("shops", shopService.getList());
        return "shop/shopsList";
    }

    //    save or update
    @RequestMapping(value = "/shops", method = RequestMethod.POST)
    public String saveOrUpdateShop(@ModelAttribute("shopAttribute") @Valid Shop shop,
                                   BindingResult bindingResult,
                                   Model model,
                                   final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "shop/shopForm";
        } else {
            redirectAttributes.addFlashAttribute("css", "success");
            if (shop.getId() == null) {
                redirectAttributes.addFlashAttribute("msg", "shop added successful");
            } else {
                redirectAttributes.addFlashAttribute("msg", "shop updated successful");
            }
            shopService.saveOrUpdate(shop);
            return "redirect:/shops/" + shop.getId();
        }
    }

    //  show by id
    @RequestMapping(value = "/shops/{id}", method = RequestMethod.GET)
    public String showUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("shopAttribute", shopService.getById(id));
        return "shop/shopShow";
    }

    //    new
    @RequestMapping(value = "/shops/add", method = RequestMethod.GET)
    public String getNew(Model model) {
        model.addAttribute("shopAttribute", new Shop());
        return "shop/shopForm";
    }

    //    update
    @RequestMapping(value = "/shops/{id}/update", method = RequestMethod.GET)
    public String getUpdate(@PathVariable("id") Integer id, Model model) {

        model.addAttribute("shopAttribute", shopService.getById(id));
        return "shop/shopForm";
    }


    //    delete
    @RequestMapping(value = "/shops/{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Integer id, Model model,
                         RedirectAttributes redirectAttributes) {
        shopService.delete(id);
        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", "Shop is deleted!");
        return "redirect:/shops";
    }




    @ExceptionHandler(DeletingShopWithEmployeesException.class)
    public String handleDeletingShopWithEmployeesException(DeletingShopWithEmployeesException ex,  HttpServletRequest request) {

        request.setAttribute("ex", ex);

        return "forward:/caseDeletingShopWithEmployeesException";
    }
    @RequestMapping(value = "/caseDeletingShopWithEmployeesException", method = RequestMethod.GET)
    public String caseDeletingShopWithEmployeesException( HttpServletRequest request,
                         RedirectAttributes redirectAttributes) {
        final DeletingShopWithEmployeesException ex = (DeletingShopWithEmployeesException) request.getAttribute("ex");
        redirectAttributes.addFlashAttribute("css", "warning");
        redirectAttributes.addFlashAttribute("msg", ex.getErrMsg());
        return "redirect:/shops/"+ex.getShopId();
    }




// With FlashMap
    @ExceptionHandler(MyException.class)
    public RedirectView handleMyException(MyException ex, HttpServletRequest request) {

        String redirect = "/shops/" + ex.getShopId();

        RedirectView rw = new RedirectView(redirect);

        FlashMap outputFlashMap = RequestContextUtils.getOutputFlashMap(request);
        if (outputFlashMap != null){
            outputFlashMap.put("css", "warning");
            outputFlashMap.put("msg", ex.getErrMsg());
        }
        return rw;
    }


}

