package ca.jcsoftware.serenity.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import ca.jcsoftware.serenity.model.Secret;


@Database (entities = {Secret.class}, version = 5, exportSchema = false)
public abstract class Db extends RoomDatabase {
    public abstract DaoAccess daoAccess() ;
}
