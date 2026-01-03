package com.example.ltwebnhom23.controller.auth;

public class Validation {

    public boolean isEmail(String email){
        if(email.contains("@")) return true;
        return false;
    }
    public boolean isPhone(String phone){
        if(phone.length() == 10) return true;
        return false;
    }
    public boolean rePass(String pass, String rePass){
        if(pass.equals(rePass)) return true;
        return false;
    }
    public boolean passLength(String pass, int length){
        if(pass.length() >= length) return true;
        return false;
    }
    public boolean containChar(String pass){
        boolean hasUpper = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;

        for (char c : pass.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUpper = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            } else if (!Character.isLetterOrDigit(c)) {
                hasSpecial = true;
            }
        }

        if(hasUpper == true && hasDigit == true && hasSpecial == true) return true;
        return false;
    }
}
