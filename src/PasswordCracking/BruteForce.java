package PasswordCracking;

import java.util.Iterator;

public class BruteForce {
    public static String bruteForce(String plaintext){
        String temp = "";
        while(temp.length()<plaintext.length()){
            temp+="a";
        }

        for(int i=1;i<plaintext.length();i++){
            for(int x=1; x< 254;x++) {
              temp =  iteratePassword(temp, i);
                System.out.println(temp);
                if(temp.equals(plaintext)){
                    return temp;
                }
            }
        }
        return "";
    }
    public static String iteratePassword(String password, int place){
        System.out.println(password.charAt(place));
        return password.replaceFirst(password.charAt(place)+"",addOneToChar(password.charAt(place))+"");
    }
    public static char addOneToChar(char ourChar){
        return (char)(ourChar + 1);
    }

}
