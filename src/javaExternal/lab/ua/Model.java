package javaExternal.lab.ua;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;


public class Model {

    private int sonnetFrom;
    private int sonnetTill;

    Map<String,Map> words = new HashMap<String, Map>();

    public Model setSonnetFrom(int sonnetFrom) {
        this.sonnetFrom = sonnetFrom;
        return this;
    }

    public Model setSonnetTill(int sonnetTill) {
        this.sonnetTill = sonnetTill;
        return this;
    }

    public void analyze() throws Exception {
        String http ="https://stihi-rus.ru/World/Shekspir/";
        String htm =".htm";
        for(int i = sonnetFrom; i < sonnetTill; i++) {
           String url = http + i + htm;
           String [] text = cleanText(getTextFromUrl(url));
           buildMap(text,url);
        }
    }

    public static String getTextFromUrl(String url) throws Exception {
        URL website = new URL(url);
        URLConnection connection = website.openConnection();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        connection.getInputStream(),"windows-1251"));

        StringBuilder response = new StringBuilder();
        String inputLine;

        while ((inputLine = in.readLine()) != null){
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }

    public String [] cleanText (String str) throws Exception {
        String textWithoutJs = str.replaceAll("<script.*?</script>" , " ");
        String textWithoutTag = textWithoutJs.replaceAll("\\<.*?>"," ");
        String textWithoutSpecialSymbol = textWithoutTag.replaceAll("&.*?;" , "");
        String [] text = textWithoutSpecialSymbol.toLowerCase().split("\\s*(\\s|,|!|\\.)\\s*");
        return text;
    }

    public void buildMap (String [] text, String url) throws Exception {
        for (String a : text) {
            if (words.get(a) == null) {
                words.put(a, new HashMap<String, Integer>());
                words.get(a).put(url, 1);
            } else {
                Integer freq = (Integer) words.get(a).get(url);
                words.get(a).put(url, (freq == null) ? 1 : ++freq);
            }
        }
    }

    public List searchWord(String input){
        String word= input.toLowerCase();
        Map<String, Integer> result = words.get(word);
        List resultList = new ArrayList(result.entrySet());
        Collections.sort(resultList, (Comparator<Map.Entry<String, Integer>>) (a, b) ->  b.getValue() - a.getValue() );
        return resultList;
    }
}
