package cn.lhqs.lab.utils;

import java.util.HashMap;

/**
 * author : lhqs
 * email : lhqs1314@gmail.com
 * createTime : 2018-11-09 18:25
 * description : Constants.class
 * version : 1.0
 */
public class Constants {

    public static HashMap<String,String> maps = new HashMap<String,String>(){
        {
            put("experiment","实验");
            put("read","阅读");
            put("write","写作");
            put("reimbursement","报销");
            put("activity","实验");
            put("purchase","采购");
            put("academic","学术报告");
        }
    };
}
