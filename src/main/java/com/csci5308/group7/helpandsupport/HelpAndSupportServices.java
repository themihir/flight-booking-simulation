/**
 * @author Mukund Sharma
 */
package com.csci5308.group7.helpandsupport;

import com.csci5308.group7.helpandsupport.interfaces.IHelpAndSupportServices;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class HelpAndSupportServices implements IHelpAndSupportServices {
    public static final int MINIMUM_DISCOUNT_COUPON = 10;
    public static final String VOUCHER_GENERATION_STRING = "S3XY290";
    public static final String NEGATIVE_WORDS_TXT = "AnalyticsData/negativeWords.txt";
    public static final String POSITIVE_WORDS_TXT = "AnalyticsData/positiveWords.txt";

    public static final List<String> STREET_NAMES = Arrays.asList("second", "first", "third", "main", "fourth", "maple", "fifth", "park", "church", "sixth", "park", "church", "sixth", "pine", "birch", "railway", "cedar", "seventh", "river", "spruce", "mill", "popular", "willow", "eighth", "eim", "tenth", "ninth", "victoria", "oak", "titus", "south park", "south street", "king", "sunset", "lake", "aspen", "centre", "mountain", "lakeview", "windsor", "queen", "eleventh", "james", "smith", "george", "martin", "albert", "campbell", "william", "north", "woodland", "wilson", "elizabeth", "riverside", "beach", "john", "scott", "ross", "taylor", "fraser");

    @Override
    public HashMap<String, String> calculateVoucherDiscount(int bookingsCounter){
        HashMap<String, String> offer = new HashMap<>();
        int discount;
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < VOUCHER_GENERATION_STRING.length(); i++) {
            stringBuilder.append(VOUCHER_GENERATION_STRING.charAt(random.nextInt(VOUCHER_GENERATION_STRING
                    .length())));
        }
        String voucher= "AD-".concat(stringBuilder.toString());
        switch (bookingsCounter){
            case 0:{
                discount = MINIMUM_DISCOUNT_COUPON;
                break;
            }
            case 1:{
                discount = MINIMUM_DISCOUNT_COUPON+10;
                break;
            }
            case 2:{
                discount = MINIMUM_DISCOUNT_COUPON+20;
                break;
            }
            default:{
                discount = MINIMUM_DISCOUNT_COUPON+30;
            }
        }
        try {
            offer.put("voucher", voucher);
            offer.put("discount", discount+"%");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return offer;
    }

    @Override
    public int calculatePaymentProbability(String address){
        String[] addressString = address.split(" ");
        float probability;
        int probabilityCounter = 0;
        for (String str : addressString) {
            if (STREET_NAMES.contains(str)) {
                probabilityCounter = probabilityCounter + 1;
            }
        }
        probability = ((float) probabilityCounter/addressString.length)*100;
        int probabilityPercentage;
        if (probability >= 10){
            probabilityPercentage = 80;
        }else if(probability >= 20){
            probabilityPercentage = 90;
        }else {
            probabilityPercentage = 70;
        }
        return probabilityPercentage;
    }

    @Override
    public String feedbackSentimentAnalysis(String feedback){
        int negativeCounter = 0;
        int positiveCounter = 0;
        ArrayList positiveWordList = analysisWordList(POSITIVE_WORDS_TXT);
        ArrayList negativeWordList = analysisWordList(NEGATIVE_WORDS_TXT);
        String[] addressString = feedback.split(" ");
        for (String word: addressString){
            String cleanWord = word.replaceAll("[^a-zA-Z0-9]", "");
            if (positiveWordList.contains(cleanWord.toLowerCase(Locale.ROOT))){
                positiveCounter = positiveCounter+1;
            }else if(negativeWordList.contains(cleanWord.toLowerCase(Locale.ROOT))){
                negativeCounter = negativeCounter+1;
            }
        }
        if (positiveCounter > negativeCounter){
            return "Positive Feedback";
        }else if (negativeCounter > positiveCounter){
            return "Negative Feedback";
        }
        else {
            return "Neutral Feedback";
        }
    }

    public static ArrayList analysisWordList(String filename) {
        ArrayList wordList = new ArrayList();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(filename));
            String word;
            while ((word = reader.readLine()) != null) {
                wordList.add(word);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return wordList;
    }
}
