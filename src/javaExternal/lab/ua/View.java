package javaExternal.lab.ua;

import java.util.List;
import java.util.Map;

public class View {
    public static final String CHOOSE_SONNET= "Which sonnets do you want to analyze? ";
    public static final String FROM = "From";
    public static final String TILL= "Till";
    public static final String WRONG= "Wrong input! ";
    public static final String WORD= "Input the word please";

    public void printMessage(String message){
        System.out.println(message);
    }
    public void printResult (List list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        System.out.println(list.size());
    }
}

/*for(Map.Entry<String, Integer> item : map.entrySet()){
        System.out.printf("%s  Frequency: %s \n", item.getKey(), item.getValue());
        }*/