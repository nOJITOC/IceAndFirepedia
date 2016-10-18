package com.softdesign.skillbranch.iceandfirepedia.utils;

import com.softdesign.skillbranch.iceandfirepedia.R;

import java.util.HashMap;

/**
 * Created by Иван on 14.10.2016.
 */
public abstract class ConstantManager {
    public static final String CHARACTER_ARRAY = "CHARACTER_ARRAY";
    static public Long[] HOUSES_ID = {229l,//Lannister
            362l,//Stark
            378l//Targaryen
    };
    public static int[] HOUSES_DRAWABLE_ID = {
            R.drawable.lanister_icon,
            R.drawable.stark_icon,
            R.drawable.targaryen_icon
    };
    public static HashMap<Long,Integer> HERBS = new HashMap<>();
    static{
        HERBS.put(HOUSES_ID[0],R.drawable.lannister);
        HERBS.put(HOUSES_ID[1],R.drawable.stark);
        HERBS.put(HOUSES_ID[2],R.drawable.targarien);
    }
    public static long DELAY = 3000;
    public static String DB_COMPLETE = "DB_COMPLETE";
    public static String CHARACTER_ID = "CHARACTER_ID";
    public static String TAG_PREFIX = "IceAndFire";
    public static long RUN_DELAY = 1500;
}
