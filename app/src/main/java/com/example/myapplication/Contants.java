package com.example.myapplication;

/**
 * Created by Hp on 3/17/2016.
 */
public class Contants {
    //COLUMNS
    static final String SROW_ID="sid";
    static final String UROW_ID="uid";
    static final String GROW_ID="gid";
    static final String LROW_ID="lid";
    static final String NAME = "name";
    static final String POSITION ="position";
    //DB PROPERTIES
    static final String DB_NAME="b_DB3.db";
    static final String SCENIC_TB_NAME1="scenic";
    static final String MEMBER_TB_NAME="contacts";
    static final String GROUP_TB_NAME="date";
    static final String LIST_TB_NAME="group_list";
    static final String COMMENT_TB_NAME="comment";
    static final String ALBUM_TB_NAME="album";
    static final int DB_VERSION='1';

    //DB ID
    public static  String uid;
    public static  String uaname;
    public static  String sid;
    public static  String gid;
    public static  int check;
    public static  String place;



    //CREATE TABLE STATEMENTS
    static final String CREATE_TB="CREATE TABLE data2(id INTEGER,"
            + "name TEXT,position);";
}

