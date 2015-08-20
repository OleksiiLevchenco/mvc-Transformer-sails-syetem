package com.levchenko.transformerShop.controller;

import com.levchenko.transformerShop.domain.Transformer;
import com.levchenko.transformerShop.service.TransformerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Alexey Levchenko
 */
@Controller
@RequestMapping(value = {"/transformer"})
public class TransformerController {

    @Autowired
    private TransformerService transformerService;

    //    list
    @RequestMapping(value = {"/list"}, method = RequestMethod.GET)
    public String getTransformers(Model model) {
        List<Transformer> transformers = transformerService.getList();
        model.addAttribute("transformers", transformers);
        return "transformer/transformersList";
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public String get(@RequestParam(value = "pageType")  String pageType,
                      @RequestParam(value = "id", required = false) Integer id,
                      Model model) {
        switch (pageType) {
            case "save":
                model.addAttribute("transformerAttribute", new Transformer());
                model.addAttribute("pageType", pageType);
                return "transformer/transformerForm";
            case "edit":
            case "delete":
                model.addAttribute("transformerAttribute", transformerService.getById(id));
                model.addAttribute("pageType", pageType);
                return "transformer/transformerForm";
            default:
                return null;// todo: надо с этим чего то сделать
        }
    }


    //    save
//    @RequestMapping(value = "/save", method = RequestMethod.GET)
//    public String getAddTransformer(Model model) {
//        model.addAttribute("transformerAttribute", new Transformer());
//        model.addAttribute("pageType", "save");
//        return "transformer/transformerForm";
//    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addTransformer(@ModelAttribute("transformerAttribute") @Valid Transformer transformer,
                                 BindingResult bindingResult,
                                 Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("pageType", "save");
            return "transformer/transformerForm";
        } else {
            transformerService.add(transformer);
            model.addAttribute("pageType", "save");
            model.addAttribute("entityType", "Transformer");
            return "redirect:/success";
        }
    }

    //    edit
//    @RequestMapping(value = "/edit", method = RequestMethod.GET)
//    public String getEditTransformer(@RequestParam(value = "id", required = true) Integer id, Model model) {
//
//        model.addAttribute("transformerAttribute", transformerService.getById(id));
//        model.addAttribute("pageType", "edit");
//        return "transformer/transformerForm";
//    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editTransformer(@ModelAttribute("transformerAttribute") @Valid Transformer transformer,
                                  BindingResult bindingResult,
                                  Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("pageType", "edit");
            return "transformer/transformerForm";
        } else {
            transformerService.update(transformer);
            model.addAttribute("pageType", "edit");
            model.addAttribute("entityType", "Transformer");
            return "redirect:/success";
        }

    }

    //    delete
//    @RequestMapping(value = "/delete", method = RequestMethod.GET)
//    public String getDeleteTransformer(@RequestParam(value = "id", required = true) Integer id, Model model) {
//
//        model.addAttribute("transformerAttribute", transformerService.getById(id));
//        model.addAttribute("pageType", "delete");
//        return "transformer/transformerForm";
//    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteTransformer(@ModelAttribute("transformerAttribute") Transformer transformer, Model model) {
        transformerService.remove(transformer.getId());
        model.addAttribute("pageType", "delete");
        model.addAttribute("entityType", "Transformer");
        return "redirect:/success";
    }

}

