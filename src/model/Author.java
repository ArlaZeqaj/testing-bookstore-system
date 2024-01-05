package model;

import model.Utility.ValidationUtil;
import java.io.Serializable;

public class Author implements Serializable{
    private String firstName;
    private String middleName; //author middle name should end with a period ex: J K. Rowling
    private String lastName;

    public Author(String firstName, String middleName, String lastName) {
        if(ValidationUtil.isValid(firstName, ValidationUtil.STRING_REGEX))
            this.firstName = firstName;
        this.middleName = middleName;
        if(ValidationUtil.isValid(lastName, ValidationUtil.STRING_REGEX))
            this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if(ValidationUtil.isValid(firstName, ValidationUtil.STRING_REGEX))
            this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if(ValidationUtil.isValid(lastName, ValidationUtil.STRING_REGEX))
            this.lastName = lastName;
    }

    @Override
    public String toString() {
        return firstName + " " + middleName  + lastName;
    }

}
