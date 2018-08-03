package com.hzyice.springbootrabbitmq.pojo;

import java.io.Serializable;



public class UserPojo implements Serializable{

    private static final long serialVersionUID = -8650059550673779926L;

    private String userName;
    private Integer age;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }


}
