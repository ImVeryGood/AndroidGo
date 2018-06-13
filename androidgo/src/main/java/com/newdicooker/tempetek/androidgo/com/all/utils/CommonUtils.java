package com.newdicooker.tempetek.androidgo.com.all.utils;

import android.graphics.Color;

import java.util.Random;

/**
 * Created by SunPengCheng
 * on 2018/6/13
 * 邮箱：13027699936@163.com.
 * version 2.0.4
 */

public class CommonUtils {
    /**
     * 获取随机rgb颜色值
     */
    public static int randomColor() {
        Random random = new Random();
        //0-190, 如果颜色值过大,就越接近白色,就看不清了,所以需要限定范围
        int red = random.nextInt(150);
        //0-190
        int green = random.nextInt(150);
        //0-190
        int blue = random.nextInt(150);
        //使用rgb混合生成一种新的颜色,Color.rgb生成的是一个int数
        return Color.rgb(red, green, blue);
    }

}
