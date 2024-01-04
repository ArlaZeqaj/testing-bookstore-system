package model.Utility;

public class ValidationUtil {
    public static final String ISBN_13_REGEX = "^(978|979)-\\d{1,5}-\\d{1,7}-\\d{1,7}-\\d{1,7}-\\d$"; //13 character ISBN with hyphens
    public static final String STRING_REGEX = "^[\\p{L}.-]+$"; //must consist only of letters, dots or hyphens
    public static final String BOOK_TITLE_REGEX = "^[\\p{L}\\s'.-]+$"; //must consist only of Unicode letters, whitespaces, single quotes, periods, or hyphens
    public static final String PHONE_REGEX = "^(\\+355|0)6[6-9]\\d{7}$"; //albanian phone number format

    //method for validating string fields to match a pattern
    public static boolean isValid(String field, String regEx){
        if(field == null || field.trim().isEmpty()){
            throw new IllegalArgumentException("This field cannot be empty!");
        } else if (!(field.matches(regEx))){
            throw new IllegalArgumentException("The format of this field is incorrect!");
        }
        return true;
    }
}
