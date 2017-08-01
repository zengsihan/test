package com.zsh.learn.work;

import java.io.Serializable;

/**
 * Created by legend on 2017/3/13.
 * 开机启动服务存数据的类
 */

public class ServiceInfo implements Serializable{
    private String id;
    private String content;

    public ServiceInfo(String id, String content) {
        this.id = id;
        this.content = content;
    }
    public ServiceInfo() {

    }

    @Override
    public String toString() {
        return "ServiceInfo{" +
                "id='" + id + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
