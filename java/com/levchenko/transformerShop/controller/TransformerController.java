package com.levchenko.transformerShop.controller;

import com.levchenko.transformerShop.domain.Transformer;
import com.levchenko.transformerShop.service.TransformerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * @author Alexey Levchenko
 */
@Controller
public class TransformerController {

    @Autowired
    private TransformerService transformerService;

    //   get List
    @RequestMapping(value = "/transformers" , method = RequestMethod.GET)
    public String getTransformers(Model model) {

        model.addAttribute("transformers",  transformerService.getList());
        return "transformer/transformersList";
    }


    //    Save or Update
    @RequestMapping(value = "/transformers", method = RequestMethod.POST)
    public String saveOrUpdate(@ModelAttribute("transformerAttribute") @Valid Transformer transformer,
                                   BindingResult bindingResult,
                                   Model model,
                                   final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "transformer/transformerForm";
        } else {
            redirectAttributes.addFlashAttribute("css", "success");
            if (transformer.getId() == null) {
                redirectAttributes.addFlashAttribute("msg", "transformer added successful");
            } else {
                redirectAttributes.addFlashAttribute("msg", "transformer updated successful");
            }
            transformerService.saveOrUpdate(transformer);
            return "redirect:/transformers/" + transformer.getId();
        }
    }


    //  Show by id
    @RequestMapping(value = "/transformers/{id}", method = RequestMethod.GET)
    public String showById(@PathVariable("id") int id, Model model) {
        model.addAttribute("transformerAttribute", transformerService.getById(id));
        return "transformer/transformerShow";
    }


    //    New
    @RequestMapping(value = "/transformers/add", method = RequestMethod.GET)
    public String getNew(Model model) {
        model.addAttribute("transformerAttribute", new Transformer());
        return "transformer/transformerForm";
    }


    //    Update
    @RequestMapping(value = "/transformers/{id}/update", method = RequestMethod.GET)
    public String getUpdate(@PathVariable("id") Integer id, Model model) {

        model.addAttribute("transformerAttribute", transformerService.getById(id));
        return "transformer/transformerForm";
    }

    // Delete
    @RequestMapping(value = "/transformers/{id}/delete", method = RequestMethod.GET)
    public String deleteTransformer(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", "transformer deleted successful");
        transformerService.delete(id);
        return "redirect:/transformers";
    }

}

