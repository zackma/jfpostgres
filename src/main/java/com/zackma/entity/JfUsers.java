package com.zackma.entity;

import com.jfinal.plugin.activerecord.Model;

public class JfUsers extends Model<JfUsers>{
    public static final String tableName = "jfusers";
    public static final JfUsers Dao=new JfUsers();
}
