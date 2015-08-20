package com.levchenko.transformerShop.controller;

import com.levchenko.transformerShop.domain.Employee;
import com.levchenko.transformerShop.domain.Shop;
import com.levchenko.transformerShop.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Alexey Levchenko
 */
@Controller
//@RequestMapping(value = {"/shop"})
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

}

