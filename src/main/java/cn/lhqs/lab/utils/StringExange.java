package cn.lhqs.lab.utils;

/**
 * author : lhqs
 * email : lhqs1314@gmail.com
 * createTime : 2018-09-03 1:09
 * description : StringExange.class
 * version : 1.0
 */
public class StringExange {
    public static String arrayToString(String[] strs) {
        String newStr = "";
        for (int i = 0; i < strs.length; i++) {
            if( i == strs.length-1 ) {
                newStr += strs[strs.length-1];
            } else {
                newStr += strs[i] + ",";
            }
        }
        return newStr;
    }
}
