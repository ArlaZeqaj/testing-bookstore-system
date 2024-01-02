package model;

public class Author {
    private String firstName;
    private String middleName; //middle names should end with a period ex: J K. Rowling
    private String lastName;
    private static final String STRING_REGEX = "^[\\p{L}\\s'.-]+$";

    public Author(String firstName, String middleName, String lastName) {
        if(isValid(firstName, STRING_REGEX))
            this.firstName = firstName;
        this.middleName = middleName;
        if(isValid(lastName, STRING_REGEX))
            this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if(isValid(firstName, STRING_REGEX))
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
        if(isValid(lastName, STRING_REGEX))
            this.lastName = lastName;
    }

    @Override
    public String toString() {
        return firstName + " " + middleName  + lastName;
    }

    public static boolean isValid( String field, String regEx){
        if(field == null || field.trim().isEmpty()){
            throw new IllegalArgumentException("This field cannot be empty!");
        } else if (!(field.matches(regEx))){
            throw new IllegalArgumentException("The format of this field is incorrect!");
        }
        return true;
    }
}
