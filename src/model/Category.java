package model;

import model.Utility.ValidationUtil;

import java.io.Serializable;

public class Category implements Serializable {
    private String name;

    public Category(String name) {
        if(ValidationUtil.isValid(name, ValidationUtil.STRING_REGEX))
            this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(ValidationUtil.isValid(name, ValidationUtil.STRING_REGEX))
            this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
