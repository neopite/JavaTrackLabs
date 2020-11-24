package lab1.com.company.neophite.utils;

public class Utils {

    public static boolean isNumber(String str) {
        boolean dotFlat = true;
        if (str == null || str.isEmpty()) return false;
        if(str.charAt(0)=='-') {
            if (itterateThroughtRow(str, dotFlat,1)) return false;
        }else{
            if (itterateThroughtRow(str, dotFlat,0)) return false;
        }
        return true;
    }

    private static boolean itterateThroughtRow(String str, boolean dotFlat,int it) {
        for (int i = it; i < str.length(); i++) {
            char currentChar = str.charAt(i);
            if (currentChar=='.' && dotFlat){
                dotFlat=false;
            } else if (!Character.isDigit(str.charAt(i))){
                return true;
            }
        }
        return false;
    }
}
