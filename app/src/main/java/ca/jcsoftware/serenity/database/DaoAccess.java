package ca.jcsoftware.serenity.database;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.ArrayList;
import java.util.List;

import ca.jcsoftware.serenity.model.Secret;

@Dao
public interface DaoAccess {

    @Query("SELECT * FROM secrets where id=:secretId")
    Secret fetchOneSecretbyId (int secretId);

    @Query("SELECT * FROM secrets")
    List<Secret> fetchAllSecrets();

    @Insert
    void insertSecret(Secret... secrets);

    @Update
    void updateSecret(Secret... secrets);

    @Delete
    void deleteSecret(Secret secret);

    @Query("DELETE FROM secrets")
    void deleteAllSecrets();


}
