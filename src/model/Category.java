package model;

public class Category {
    private String name;

    public Category(String name) {
        if(name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("This field cannot be empty!");
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("This field cannot be empty!");
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
