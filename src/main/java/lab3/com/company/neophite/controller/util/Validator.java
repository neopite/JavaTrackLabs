package lab3.com.company.neophite.controller.util;

import lab3.com.company.neophite.model.entity.User;

import java.sql.Timestamp;
import java.util.Date;
import java.util.regex.Pattern;

public class Validator {
    public static void checkRegistrationCredentials(User user){
        if(Pattern.matches(ValidationRegex.emailRegex,user.getEmail()) &&
                Pattern.matches(ValidationRegex.usernameRegex,user.getUsername()) &&
                Pattern.matches(ValidationRegex.passwordRegex,user.getPasswd())){
         return;
        }
        throw new CustomException("Registration bad credential");
    }
    public static void checkStationName(String station){
        if(Pattern.matches(ValidationRegex.stationRegex,station) && station.length()!=0){
            return;
        }
        throw new CustomException("Bad station naming");
    }

    public static void checkDateSeq(Date dateFrom, Date dateTo){
        if((dateFrom!=null && dateTo!=null) && dateFrom.before(dateTo)){
         return;
        }
        throw new CustomException("Invalid Date Input");
    }

}
