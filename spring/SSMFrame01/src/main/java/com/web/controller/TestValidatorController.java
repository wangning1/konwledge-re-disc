package com.web.controller;

import com.web.model.Goods;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.Date;

/**
 * Created by wangning on 2017/3/13.
 */
@Controller
@RequestMapping(value = "/validator")
public class TestValidatorController {

    private static final Logger logger = LoggerFactory.getLogger(TestValidatorController.class);

    @RequestMapping(value = "/getGoods")
    @ResponseBody
    public Goods testValidator(
            @Valid Goods goods, BindingResult bindingResult) {
//        Goods goods = new Goods();
//        goods.setId(1);
//        goods.setName("wer");

        if (bindingResult.hasErrors()) {
            for (FieldError error : bindingResult.getFieldErrors()){
                logger.info(error.getField() + "'s error is" + error.getDefaultMessage() );
            }
        }
        goods.setCreateTime(new Date(System.currentTimeMillis()));

        logger.info("--getGoods" + goods);
        return goods;
    }


}
