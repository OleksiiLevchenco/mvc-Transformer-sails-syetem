package com.levchenko.tss.controller;

import com.levchenko.tss.service.TransformerService;
import com.levchenko.tss.domain.Transformer;
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
@RequestMapping("/transformers")
public class TransformerController {

    @Autowired
    private TransformerService transformerService;

    private static final String TRANSFORMERS_LIST = "transformer/transformersList";
    private static final String TRANSFORMER_FORM = "transformer/transformerForm";
    private static final String TRANSFORMER_SHOW = "transformer/transformerShow";
    private static final String REDIRECT_TRANSFORMERS = "redirect:/transformers/";


    //   get List
    @RequestMapping(value = "" , method = RequestMethod.GET)
    public String getTransformers(Model model) {

        model.addAttribute("transformers",  transformerService.getList());
        return TRANSFORMERS_LIST;
    }


    //    Save or Update
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String saveOrUpdate(@ModelAttribute("transformerAttribute") @Valid Transformer transformer,
                                   BindingResult bindingResult,
                                   Model model,
                                   final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return TRANSFORMER_FORM;
        } else {
            redirectAttributes.addFlashAttribute("css", "success");
            if (transformer.getId() == null) {
                redirectAttributes.addFlashAttribute("msg", "transformer added successful");
            } else {
                redirectAttributes.addFlashAttribute("msg", "transformer updated successful");
            }
            transformerService.saveOrUpdate(transformer);
            return REDIRECT_TRANSFORMERS + transformer.getId();
        }
    }


    //  Show by id
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public String showById(@PathVariable("id") int id, Model model) {
        model.addAttribute("transformerAttribute", transformerService.getById(id));
        return TRANSFORMER_SHOW;
    }


    //    New
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String getNew(Model model) {
        model.addAttribute("transformerAttribute", new Transformer());
        return TRANSFORMER_FORM;
    }


    //    Update
    @RequestMapping(value = "{id}/update", method = RequestMethod.GET)
    public String getUpdate(@PathVariable("id") Integer id, Model model) {

        model.addAttribute("transformerAttribute", transformerService.getById(id));
        return TRANSFORMER_FORM;
    }

    // Delete
    @RequestMapping(value = "{id}/delete", method = RequestMethod.GET)
    public String deleteTransformer(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", "transformer deleted successful");
        transformerService.delete(id);
        return REDIRECT_TRANSFORMERS;
    }

}

