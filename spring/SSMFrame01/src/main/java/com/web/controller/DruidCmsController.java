package com.web.controller;

import com.web.mapper.dbDruid.CmsModelCustomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by wangning on 2017/1/10.
 */
@Controller
@RequestMapping(value = "cms")
public class DruidCmsController {

  @Autowired
  private CmsModelCustomMapper cmsModelCustomMapper;

  @RequestMapping(value = "/getCmsModel")
  @ResponseBody
  public String getCmsModel(){
   List result =  cmsModelCustomMapper.getCmsModelCustom();
    System.out.println(result.size());
    return "test druid";
  }

}
