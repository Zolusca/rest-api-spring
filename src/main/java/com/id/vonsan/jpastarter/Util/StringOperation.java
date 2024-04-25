package com.id.vonsan.jpastarter.Util;

import java.util.StringTokenizer;

public class StringOperation {
    /**
     * Pembuatan capitalize string
     * <br>
     * (ini contoh) -> (Ini Contoh)
     * </br>
     * @param data string yang ingin di capitalize
     * @param delemiter pemisah {haslam} dari string
     * @return string yang tercapitalize
     */
    public static String capitalizeSentence(String data, String delemiter){
        StringBuilder stringBuilder = new StringBuilder();
        StringTokenizer stringTokenizer = new StringTokenizer(data,delemiter);

        while (stringTokenizer.hasMoreTokens()){
            String pieceOfdata = stringTokenizer.nextToken();
            String stringCapitalize =
                            pieceOfdata.substring(0,1).toUpperCase()+
                            pieceOfdata.substring(1);
            stringBuilder.append(stringCapitalize);
            stringBuilder.append(delemiter);
        }
        return stringBuilder.toString();
    }

    /**
     * capitalisasi huruf pertama
     * <p>
     *     Ex : hero -> Hero
     * </p>
     */
    public static String capitalizeFirstWord(String word){
        return word
                .substring(0,1)
                .toUpperCase()
                +
                word.substring(1);
    }
}
