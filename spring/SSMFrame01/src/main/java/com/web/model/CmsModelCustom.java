package com.web.model;

import org.apache.ibatis.type.Alias;

/**
 * Created by wangning on 2017/1/10.
 */
@Alias("cmsModelCustom")
public class CmsModelCustom {

  private Long fModelId;
  private String fKey;
  private String fValue;

  public Long getfModelId() {
    return fModelId;
  }

  public void setfModelId(Long fModelId) {
    this.fModelId = fModelId;
  }

  public String getfKey() {
    return fKey;
  }

  public void setfKey(String fKey) {
    this.fKey = fKey;
  }

  public String getfValue() {
    return fValue;
  }

  public void setfValue(String fValue) {
    this.fValue = fValue;
  }
}
