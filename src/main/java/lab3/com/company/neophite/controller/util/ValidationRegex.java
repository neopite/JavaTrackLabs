package lab3.com.company.neophite.controller.util;

public interface ValidationRegex {
    String usernameRegex = "[a-zA-Z0-9\\._\\-]{3,}";
    String passwordRegex = ".{4,30}";
    String ageRegex = "\\d+";
    String emailRegex = "^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$";
    String stationRegex = "^[a-zA-Z]*$";
}