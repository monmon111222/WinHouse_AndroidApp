package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Hp on 3/17/2016.
 */
public class DBAdapter {
    Context c;
    SQLiteDatabase db;
    DBHelper helper;

    public DBAdapter(Context ctx)
    {
        this.c=ctx;
        helper=new DBHelper(c);
    }

    //OPEN DB
    public DBAdapter openDB()
    {
        try
        {
            db=helper.getWritableDatabase();
        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return this;
    }

    //CLOSE
    public void close()
    {
        try
        {
            helper.close();
        }catch (SQLException e)
        {
            e.printStackTrace();
        }


    }

    //INSERT DATA TO DB
    public long add(String name,String pos)
    {
        try
        {
            ContentValues cv=new ContentValues();
            cv.put(Contants.NAME,name);
            cv.put(Contants.POSITION, pos);

            return db.insert(Contants.SCENIC_TB_NAME1,Contants.SROW_ID,cv);

        }catch (SQLException e)
        {
            e.printStackTrace();
        }


        return 0;
    }

    //RETRIEVE ALL PLAYERS
    public Cursor getAllPlayers()
    {
        //String[] columns={Contants.ROW_ID,Contants.NAME,Contants.POSITION};

        return db.query(Contants.SCENIC_TB_NAME1,null,null,null,null,null,null);
    }

    //SEARCH PLAYERS
    public Cursor selectPlayers(String name)
    {
        //String[] columns={Contants.ROW_ID,Contants.NAME,Contants.POSITION};

        return db.rawQuery("select * from " + Contants.SCENIC_TB_NAME1 + " where " + Contants.NAME + " like ?", new String[] { "%" + name + "%" });
    }

    //UPDATE
    public long UPDATE(int id,String name,String pos)
    {
        try
        {
            ContentValues cv=new ContentValues();
            cv.put(Contants.NAME,name);
            cv.put(Contants.POSITION, pos);

            return db.update(Contants.SCENIC_TB_NAME1,cv,Contants.SROW_ID+" =?",new String[]{String.valueOf(id)});

        }catch (SQLException e)
        {
            e.printStackTrace();
        }


        return 0;
    }

    //DELETE
    public long Delete(int id)
    {
        try
        {

            return db.delete(Contants.SCENIC_TB_NAME1,Contants.SROW_ID+" =?",new String[]{String.valueOf(id)});

        }catch (SQLException e)
        {
            e.printStackTrace();
        }


        return 0;
    }

}
