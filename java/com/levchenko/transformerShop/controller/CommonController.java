package com.levchenko.transformerShop.controller;

import com.levchenko.transformerShop.domain.Transformer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Alexey Levchenko
 */
@Controller
@RequestMapping(value = {"/"})
public class CommonController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model) {
        return "redirect:/shops";
    }

    @RequestMapping(value = "success", method = RequestMethod.GET)
    public String addingShopSuccess(@RequestParam(value = "pageType", required = false)String pageType,
                                    @RequestParam(value = "entityType", required = false)String entityType,
                                    @RequestParam(value = "returnUrl", required = false)String returnUrl,
                                    @RequestParam(value = "shopId", required = false)String shopId,
                                    Model model) {
        model.addAttribute("pageType", pageType);
        model.addAttribute("entityType", entityType);
        model.addAttribute("returnUrl", returnUrl);
        model.addAttribute("shopId", shopId);
        return "successPage";
    }

    @RequestMapping(value = "/getCommon", method = RequestMethod.GET)
    public String get(@ModelAttribute("pageType") @Valid String pageType,
                      @RequestParam("entityType")String entityType,
                      Model model) {
        switch (entityType) {
            case "Transformer":
                switch (pageType) {
                    case "save":
                        model.addAttribute("transformerAttribute", new Transformer());
                        model.addAttribute("pageType", pageType);
                        return "transformer/transformerForm";
                    case "edit":
                }

            default: return null;
        }

//        model.addAttribute("employeeAttribute", new Employee());
//        model.addAttribute("pageType", pageType);
//        return "employee/employeeForm";
    }


    @RequestMapping(value = "/ok", method = RequestMethod.GET)
    public @ResponseBody
    String getOk(Model model) {
        return "it is ok!";
    }


}

