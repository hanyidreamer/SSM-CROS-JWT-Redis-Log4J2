package com.demo.utils;


/**
 * The type Id number utils.
 *
 * @author tuzhengsong
 */
public class IdNumberUtils {

    /**
     * 18位身份证
     * 获取 生日
     *
     * @param cardcode 身份证号
     * @return 生日
     */
    public static String getBirthdayByIdCard18(String cardcode) {

        //捕捉非空判断


        //身份证上的年月日
        String idyear = cardcode.substring(6).substring(0, 4);
        // 得到年份
        String idyue = cardcode.substring(10).substring(0, 2);
        // 得到月份
        String idday = cardcode.substring(12).substring(0, 2);
        //得到日
        return idyear + "-" + idyue + "-" + idday;
    }

    /**
     * 18位身份证
     * 获取 性别
     *
     * @param cardcode 身份证号
     * @return 生日
     */
    public static String getSexByIdCard18(String cardcode) {

        int sixteen = 16;
        int two = 2;

        //未知
        String sex;
        // 判断性别
        if (Integer.parseInt(cardcode.substring(sixteen).substring(0, 1)) % two == 0) {
            //女
            sex = "0";
        } else {
            //男
            sex = "1";
        }
        return sex;
    }
}
