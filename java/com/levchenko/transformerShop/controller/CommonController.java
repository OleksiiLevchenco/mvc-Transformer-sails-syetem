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

    @RequestMapping(value = "/ok", method = RequestMethod.GET)
    public @ResponseBody
    String getOk(Model model) {
        return "it is ok!";
    }


}

