package com.example.why.skindemo;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Long
 * on 2017/7/22.
 */

public class DayNight {

    // 1.声明必要的int常量
    public static final int DAY = 0;
    public static final int NIGHT = 1;

    // 2.声明一个注解为LightColors
    // 3.使用@IntDef修饰LightColors,参数设置为待枚举的集合
    // 4.使用@Retention(RetentionPolicy.SOURCE)指定注解仅存在与源码中,不加入到class文件中
    @IntDef({DAY, NIGHT})
    @Retention(RetentionPolicy.SOURCE)
    public @interface DayNightType{}

    private int typeCode;       // 0 为日间模式  1 为夜间模式

    public int getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(int typeCode) {
        this.typeCode = typeCode;
    }
}
