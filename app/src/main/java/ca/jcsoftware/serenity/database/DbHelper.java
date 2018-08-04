package ca.jcsoftware.serenity.database;

import android.arch.persistence.room.Room;
import android.content.Context;

public class DbHelper {


    private static final String DATABASE_NAME = "serenity_sb";
    private Db serenityDB;

    public DbHelper(Context context){
        this.serenityDB = Room.databaseBuilder(context, Db.class, DATABASE_NAME).fallbackToDestructiveMigration().allowMainThreadQueries().build();
    }

    public Db getDB(){
        return serenityDB;
    }

}
