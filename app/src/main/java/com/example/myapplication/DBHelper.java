package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static android.drm.DrmStore.Action.OUTPUT;

/**
 * Created by Hp on 3/17/2016.
 */
public class DBHelper extends SQLiteOpenHelper {
    String DB_PATH = null;
    SQLiteDatabase db;
    private static final String TABLE_CREATE= "create table contacts (id integer primary key not null ," +
            "name text not null ,phonenumber1 text not null ,phonenumber2 text not null ,bday text not null ,bhistory text not null ,uname text not null ,pass text not null );";
    private SQLiteDatabase mydb;
    public DBHelper(Context context) {
        super(context, Contants.DB_NAME, null, Contants.DB_VERSION);
        //this.DB_PATH = "/data/data/" + context.getPackageName() + "/" + "databases/";
    }

    public void openDataBase() throws SQLException {
        String myPath = DB_PATH + Contants.DB_NAME;
        mydb = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

    }
    public void insertphoto(byte[] image,String gid){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO album VALUES (NULL,?,?)";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindBlob(1, image);
        statement.bindString(2, gid);

        statement.executeInsert();
    }
    public Cursor GetPhotos(String gid){
        SQLiteDatabase db = getReadableDatabase();
        String selection = "gid" + "=?";
        return db.query(Contants.ALBUM_TB_NAME,null,selection,new String[]{gid},null,null,null);
    }

    public void insertphoto2(byte[] image){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO FOOD2 VALUES (NULL,?)";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindBlob(1, image);

        statement.executeInsert();
    }

    public boolean insertComment (String uid,String sid,String content) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        String query="select * from comment";
        Cursor cursor=db.rawQuery(query,null);
        int count=cursor.getCount();
        contentValues.put("uid", uid);
        contentValues.put("sid", sid);
        contentValues.put("content", content);
        db.insert(Contants.COMMENT_TB_NAME, null, contentValues);
        return true;
    }
    public ArrayList<String> showComment (String sid) {
        ArrayList<String> array_list = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        //String query="SELECT content FROM comment WHERE sid = ?";
        Cursor cursor=db.rawQuery("SELECT content,name FROM comment,contacts ON comment.uid=contacts.uid WHERE sid = ?",new String[] {sid});
        //int count=cursor.getCount();
        //cursor.moveToFirst();
        while(cursor.moveToNext()){
            array_list.add(cursor.getString(1)+" 說: "+cursor.getString(0));
        }

        return array_list;
    }
    public boolean insertContact2 (String name,String phone1,String phone2,String birhd,String history,String username,String password,String re) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        String query="select * from contacts";
        Cursor cursor=db.rawQuery(query,null);
        int count=cursor.getCount();
        contentValues.put("name", name);
        contentValues.put("phonenumber1", phone1);
        contentValues.put("phonenumber2", phone2);
        contentValues.put("relationship", re);
        contentValues.put("bday", birhd);
        contentValues.put("bhistory", history);
        contentValues.put("uname", username);
        contentValues.put("pass", password);
        db.insert(Contants.MEMBER_TB_NAME, null, contentValues);
        return true;
    }
    public boolean updateContact (String uid, String name, String phone1, String phone2,String re,String bd,String his) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("phonenumber1", phone1);
        contentValues.put("phonenumber2", phone2);
        contentValues.put("bday", bd);
        contentValues.put("bhistory", his);
        contentValues.put("relationship", re);
        db.update(Contants.MEMBER_TB_NAME, contentValues, "uid = ? ", new String[] {uid} );
        return true;
    }

    public Boolean checkUser(String username, String password){
        String[] columns = {"uid"};
        SQLiteDatabase db = getReadableDatabase();
        String selection = "uname" + "=?" + " and " +"pass" + "=?";
        String[] selectionArgs = { username, password };
        Cursor cursor = db.query(Contants.MEMBER_TB_NAME,null,selection,selectionArgs,null,null,null);
        int count = cursor.getCount();

        if(count>0)
            return  true;
        else
            return  false;
    }

    public Cursor GetUser(String username){
        String[] columns = {"uid","name"};
        SQLiteDatabase db = getReadableDatabase();
        String selection = "uname" + "=?";
        String[] selectionArgs = { username};
        return db.query(Contants.MEMBER_TB_NAME,columns,selection,selectionArgs,null,null,null);
    }

    public Cursor GetUserInfo(String uid){
        SQLiteDatabase db = getReadableDatabase();
        String selection = "uid" + "=?";
        return db.query(Contants.MEMBER_TB_NAME,null,selection,new String[]{uid},null,null,null);
    }
    //RETRIEVE ALL SCENIC
    public Cursor getSingleScenic(String sid)
    {
        SQLiteDatabase db =getReadableDatabase();
        return db.query(Contants.SCENIC_TB_NAME1,null,"sid = ?",new String[]{sid},null,null,null);
    }
    //RETRIEVE ALL SCENIC
    public Cursor getAllScenic()
    {

        SQLiteDatabase db =getReadableDatabase();
        return db.query(Contants.SCENIC_TB_NAME1,null,null,null,null,null,null);
    }
    //1-6古蹟
    public Cursor getAllScenicP1()
    {   int idend=6;
        SQLiteDatabase db =getReadableDatabase();
        return db.rawQuery("select * from " + Contants.SCENIC_TB_NAME1 + " where " + Contants.SROW_ID + "<="+idend,null);
    }
    //7-14夜市商圈
    public Cursor getAllScenicP2()
    {   int idstart=7;
        int idend=41;

        SQLiteDatabase db =getReadableDatabase();
        return db.rawQuery("select * from " + Contants.SCENIC_TB_NAME1 + " where " + Contants.SROW_ID + " BETWEEN "+idstart+" AND "+idend,null);
    }
    //42-88無障礙
    public Cursor getAllScenicP3()
    {   int idstart=42;
        int idend=88;

        SQLiteDatabase db =getReadableDatabase();
        return db.rawQuery("select * from " + Contants.SCENIC_TB_NAME1 + " where " + Contants.SROW_ID + " BETWEEN "+idstart+" AND "+idend,null);
    }
    //89-93溫泉
    public Cursor getAllScenicP4()
    {   int idstart=89;
        int idend=93;

        SQLiteDatabase db =getReadableDatabase();
        return db.rawQuery("select * from " + Contants.SCENIC_TB_NAME1 + " where " + Contants.SROW_ID + " BETWEEN "+idstart+" AND "+idend,null);
    }
    //94-124單車藍色公路
    public Cursor getAllScenicP5()
    {   int idstart=94;
        int idend=124;

        SQLiteDatabase db =getReadableDatabase();
        return db.rawQuery("select * from " + Contants.SCENIC_TB_NAME1 + " where " + Contants.SROW_ID + " BETWEEN "+idstart+" AND "+idend,null);
    }
    //SEARCH All SCENIC
    public Cursor selectScenic(String name)
    {
        SQLiteDatabase db =getReadableDatabase();
        return db.rawQuery("select * from " + Contants.SCENIC_TB_NAME1 + " where " + Contants.NAME + " like ?", new String[] { "%" + name + "%" });
    }

    //SEARCH SCENICP1
    public Cursor selectScenicP1(String name)
    {
        int idend=6;
        SQLiteDatabase db =getReadableDatabase();
        return db.rawQuery("select * from " + Contants.SCENIC_TB_NAME1 + " where " + Contants.NAME + " like ?" + " AND " + Contants.SROW_ID + "<="+idend, new String[] { "%" + name + "%" });
    }
    //SEARCH SCENICP2
    public Cursor selectScenicP2(String name)
    {   int idstart=7;
        int idend=41;
        SQLiteDatabase db =getReadableDatabase();
        return db.rawQuery("select * from " + Contants.SCENIC_TB_NAME1 + " where " + Contants.NAME + " like ?" + " AND " + Contants.SROW_ID + " BETWEEN "+idstart+" AND "+idend, new String[] { "%" + name + "%" });
    }
    //SEARCH SCENICP3
    public Cursor selectScenicP3(String name)
    {   int idstart=42;
        int idend=88;
        SQLiteDatabase db =getReadableDatabase();
        return db.rawQuery("select * from " + Contants.SCENIC_TB_NAME1 + " where " + Contants.NAME + " like ?" + " AND " + Contants.SROW_ID + " BETWEEN "+idstart+" AND "+idend, new String[] { "%" + name + "%" });
    }
    //SEARCH SCENICP4
    public Cursor selectScenicP4(String name)
    {   int idstart=89;
        int idend=93;

        SQLiteDatabase db =getReadableDatabase();
        return db.rawQuery("select * from " + Contants.SCENIC_TB_NAME1 + " where " + Contants.NAME + " like ?" + " AND " + Contants.SROW_ID + " BETWEEN "+idstart+" AND "+idend, new String[] { "%" + name + "%" });
    }
    //SEARCH SCENICP5
    public Cursor selectScenicP5(String name)
    {   int idstart=94;
        int idend=124;
        SQLiteDatabase db =getReadableDatabase();
        return db.rawQuery("select * from " + Contants.SCENIC_TB_NAME1 + " where " + Contants.NAME + " like ?" + " AND " + Contants.SROW_ID + " BETWEEN "+idstart+" AND "+idend, new String[] { "%" + name + "%" });
    }
    //WHEN TB IS CREATED
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db=db;

    }
    public boolean checkdoubleUser(String username){
        String[] columns = {"uid"};
        SQLiteDatabase db = getReadableDatabase();
        String selection = "uname" + "=?" ;
        String[] selectionArgs = {username};
        Cursor cursor = db.query(Contants.MEMBER_TB_NAME,columns,selection,selectionArgs,null,null,null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        if(count>0)
            return  true;
        else
            return  false;
    }
    public Cursor getGroup(String fromid)
    {
        SQLiteDatabase db =getReadableDatabase();
        return db.query(Contants.GROUP_TB_NAME,null,"sid =?",new String[]{fromid},null,null,null);
    }
    public Cursor getGroupFromUser(String fromid)
    {
        SQLiteDatabase db =getReadableDatabase();
        return db.query(Contants.GROUP_TB_NAME,null,"gid =?",new String[]{fromid},null,null,null);
    }
    public Cursor getGroupFromUserID(String fromid)
    {
        SQLiteDatabase db =getReadableDatabase();
        return  db.rawQuery("SELECT date.time,scenic.name,date.gid FROM date,scenic,group_list  ON group_list.gid = date.gid AND date.sid = scenic.sid WHERE group_list.uid = ? " , new String[] {fromid});
    }
    public boolean insertGroup (String time,String eat,String gather,String sid,String uid) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("time", time);
        contentValues.put("eat", eat);
        contentValues.put("gather",gather);
        contentValues.put("sid", sid);
        contentValues.put("uid",uid);
        contentValues.put("bol","3");
        db.insert(Contants.GROUP_TB_NAME, Contants.GROW_ID, contentValues);
        return true;
    }
    public boolean insertGroupInList (String uid,String gid) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("gid", gid);
        contentValues.put("uid",uid);
        db.insert(Contants.LIST_TB_NAME, Contants.GROW_ID, contentValues);
        return true;
    }
    public boolean joinGroup (String gid,String uid) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("gid",gid);
        contentValues.put("uid",uid);
        db.insert(Contants.LIST_TB_NAME, Contants.LROW_ID, contentValues);
        return true;
    }
    public boolean outGroup (String gid,String uid) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("gid",gid);
        contentValues.put("uid",uid);
        db.delete(Contants.LIST_TB_NAME, "gid = ? AND uid = ?", new String[] {gid,uid});
        return true;
    }
    public boolean joinGroupcheck (String gid,String uid) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = {"gid","uid"};
        String selection = "gid" + "=?" + " and " +"uid" + "=?";
        String[] selectionArgs = { gid, uid };
        Cursor c=db.query(Contants.LIST_TB_NAME,null,selection,selectionArgs,null,null,null);
        int count = c.getCount();

        if(count>0)
            return  true;
        else
            return  false;
    }
    public boolean deleteGroup (String gid) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Contants.GROUP_TB_NAME,"gid = ? ",new String[] {gid});
        return true;
    }
    public boolean deleteGroupInList (String gid) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Contants.LIST_TB_NAME,"gid = ? ",new String[] {gid});
        return true;
    }
    public boolean updateGroup (String gid,String gather,String check) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("gather", gather);
        contentValues.put("check", check);
        db.update(Contants.GROUP_TB_NAME, contentValues, "gid = ? ", new String[] {gid} );
        return true;
    }

    public Cursor getGroupName(String uid) {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("SELECT date.time,scenic.name,date.gid FROM date,scenic,group_list  ON group_list.gid = date.gid AND date.sid = scenic.sid WHERE group_list.uid = ? " , new String[] {uid});
        return res;
    }

    public ArrayList<String> getList2(String uid) {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("SELECT date.time,scenic.name FROM date,scenic,group_list  ON group_list.gid = date.gid AND date.sid = scenic.sid WHERE group_list.uid = ? " , new String[] {uid});
        //res.moveToFirst();

        while(res.moveToNext()){
            array_list.add(res.getString(1)+"  "+res.getString(0));
        }
        return array_list;
    }
    public ArrayList<String> getListGid(String uid) {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("SELECT date.gid,date.time,date.sid FROM date,scenic,group_list  ON group_list.gid = date.gid AND date.sid = scenic.sid WHERE group_list.uid = ? " , new String[] {uid});
        //res.moveToFirst();

        while(res.moveToNext()){
            array_list.add(res.getString(0));
        }
        return array_list;
    }
    public ArrayList<String> getListSid(String uid) {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("SELECT date.sid FROM date,scenic,group_list  ON group_list.gid = date.gid AND date.sid = scenic.sid WHERE group_list.uid = ? " , new String[] {uid});
        //res.moveToFirst();

        while(res.moveToNext()){
            array_list.add(res.getString(0));
        }
        return array_list;
    }

    //UPGRADE TB
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       db.execSQL("DROP TABLE IF"+Contants.SCENIC_TB_NAME1);
       this.onCreate(db);
    }
    public  ArrayList<String>  GetUserNameInGroup(String gid){
        ArrayList<String> array_list = new ArrayList<String>();

        SQLiteDatabase db = getReadableDatabase();

        Cursor res =  db.rawQuery("SELECT contacts.name,group_list.gid FROM contacts,group_list ON group_list.uid = contacts.uid WHERE group_list.gid = ? " ,new String[] {gid});
        while(res.moveToNext()){
            array_list.add(res.getString(0));
        }
        return array_list;

    }
    public  ArrayList<String>  GetUserNameDetailInGroup(String gid){
        ArrayList<String> array_list = new ArrayList<String>();

        SQLiteDatabase db = getReadableDatabase();

        Cursor res =  db.rawQuery("SELECT contacts.name,contacts.phonenumber1,contacts.phonenumber2,contacts.relationship,group_list.gid FROM contacts,group_list ON group_list.uid = contacts.uid WHERE group_list.gid = ? " ,new String[] {gid});
        while(res.moveToNext()){
            array_list.add("姓名:"+res.getString(0)+"\n"+"連絡電話:"+res.getString(1)+"\n"+"緊急連絡電話:"+res.getString(2)+"\n"+"緊急連絡人關係:"+res.getString(3));
        }
        return array_list;

    }
    public  byte[]  GetPhoto(String pid){
        //ArrayList<String> array_list = new ArrayList<String>();

        SQLiteDatabase db = getReadableDatabase();

        Cursor res =  db.rawQuery("SELECT album.image FROM album WHERE album.id = ? " ,new String[] {pid});
        res.moveToFirst();
        return res.getBlob(0);

    }


}
