package com.levchenko.tss.controller;

import com.levchenko.tss.service.ShopService;
import com.levchenko.tss.service.impl.exceptions.DeletingShopWithEmployeesException;
import com.levchenko.tss.service.impl.exceptions.MyException;
import com.levchenko.tss.domain.Shop;
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
@RequestMapping("/shops")
public class ShopController {

    @Autowired
    private ShopService shopService;

    private static final String SHOPS_LIST = "shop/shopsList";
    private static final String SHOP_FORM = "shop/shopForm";
    private static final String SHOP_REDIRECT = "redirect:/shops/";
    private static final String SHOP_SHOW = "shop/shopShow";


    //    Show List
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getShops(Model model) {

        model.addAttribute("shops", shopService.getList());
        return SHOPS_LIST;
    }

    //    SAVE OR UPDATE
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String saveOrUpdateShop(@ModelAttribute("shopAttribute") @Valid Shop shop,
                                   BindingResult bindingResult,
                                   final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return SHOP_FORM;
        } else {
            redirectAttributes.addFlashAttribute("css", "success");
            if (shop.getId() == null) {
                redirectAttributes.addFlashAttribute("msg", "shop added successful");
            } else {
                redirectAttributes.addFlashAttribute("msg", "shop updated successful");
            }
            shopService.saveOrUpdate(shop);
            return SHOP_REDIRECT + shop.getId();
        }
    }

    //  Show by id
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public String showUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("shopAttribute", shopService.getById(id));
        return SHOP_SHOW;
    }

    //    New
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String getNew(Model model) {
        model.addAttribute("shopAttribute", new Shop());
        return SHOP_FORM;
    }

    //    Update
    @RequestMapping(value = "{id}/update", method = RequestMethod.GET)
    public String getUpdate(@PathVariable("id") Integer id, Model model) {

        model.addAttribute("shopAttribute", shopService.getById(id));
        return SHOP_FORM;
    }


    //    Delete
    @RequestMapping(value = "{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Integer id, Model model,
                         RedirectAttributes redirectAttributes) {
        shopService.delete(id);
        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", "Shop is deleted!");
        return SHOP_REDIRECT;
    }


    // Exception handling, case delete shop with employees
    @ExceptionHandler(DeletingShopWithEmployeesException.class)
    public String handleDeletingShopWithEmployeesException(DeletingShopWithEmployeesException ex,  HttpServletRequest request) {

        request.setAttribute("ex", ex);

        return "forward:/shops/caseDeletingShopWithEmployeesException";
    }
    @RequestMapping(value = "/caseDeletingShopWithEmployeesException", method = RequestMethod.GET)
    public String caseDeletingShopWithEmployeesException( HttpServletRequest request, RedirectAttributes redirectAttributes) {

        final DeletingShopWithEmployeesException ex = (DeletingShopWithEmployeesException) request.getAttribute("ex");
        redirectAttributes.addFlashAttribute("css", "warning");
        redirectAttributes.addFlashAttribute("msg", ex.getErrMsg());

        return SHOP_REDIRECT + ex.getShopId();
    }

}

