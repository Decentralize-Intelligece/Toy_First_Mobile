package com.example.toyfirstmobile.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    public boolean isValidEmail(String email){
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        Pattern p = java.util.regex.Pattern.compile(ePattern);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    public boolean isValidUserName(String userName){
        Pattern pattern = Pattern.compile("[A-Za-z0-9_]+");
        return (userName != null) && pattern.matcher(userName).matches();
    }

    public boolean isValidMobileNumber(String mobileNumber){
        String pattern = "\\+\\d{1,3}-[()\\d\\s-]+";
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
