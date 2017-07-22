package com.example.why.skindemo.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.why.skindemo.DayNight;

/**
 * Created by Long
 * on 2017/7/22.
 */

public class DayNightUtils {
    private final static String FILE_NAME = "settings";
    private final static String MODE = "day_night_mode";

    private SharedPreferences mSharedPreferences;

    public DayNightUtils(Context context) {
        this.mSharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
    }

    /**
     * 保存模式设置
     *
     * @param dayNightType
     * @return
     */
    public boolean setMode(@DayNight.DayNightType int dayNightType) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt(MODE, dayNightType);
        return editor.commit();
    }

    /**
     * 夜间模式
     *
     * @return      true 为夜间模式   false 为日间模式
     */
    public boolean isNight() {
        int mode = mSharedPreferences.getInt(MODE, DayNight.DAY);
        if (DayNight.NIGHT == mode) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 日间模式
     *
     * @return      true 为日间模式   false 为夜间模式
     */
    public boolean isDay() {
        int mode = mSharedPreferences.getInt(MODE, DayNight.DAY);
        if (DayNight.DAY == mode) {
            return true;
        } else {
            return false;
        }
    }
}
