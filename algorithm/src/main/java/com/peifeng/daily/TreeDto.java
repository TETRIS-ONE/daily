package com.peifeng.daily;

import java.util.ArrayList;
import java.util.List;

public class TreeDto {
    private String id;
    private String pid;
    private List<TreeDto> children = new ArrayList<>();


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public List<TreeDto> getChildren() {
        return children;
    }

    public void setChildren(List<TreeDto> children) {
        this.children = children;
    }
}
