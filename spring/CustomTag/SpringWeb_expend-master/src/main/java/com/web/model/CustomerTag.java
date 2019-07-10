package com.web.model;

/**
 * Created by wangning on 2017/6/12.
 */
public class CustomerTag extends BaseObject {

    private String tagId;
    private String tagName;

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}
