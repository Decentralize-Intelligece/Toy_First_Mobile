package com.example.toyfirstmobile.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    public static boolean isValidEmail(String email){
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        Pattern p = java.util.regex.Pattern.compile(ePattern);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    public static boolean isValidUserName(String userName){
        Pattern pattern = Pattern.compile("[A-Za-z0-9_]+");
        return (userName != null) && pattern.matcher(userName).matches();
    }

    public static boolean isValidMobileNumber(String mobileNumber){
        String pattern = "(0|94)?[0-9][0-9]{9}";
        Pattern p = Pattern.compile(pattern);
        Matcher matcher = p.matcher(mobileNumber);
        return matcher.matches();
    }

    public boolean isUniqueEmail(String email){

        return true;
    }

    public boolean isUniqueUserName(String userName){

        return true;
    }


}
