package ca.jcsoftware.serenity.model;
import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

@Entity(tableName = "secrets")
public class Secret implements Serializable {
    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public String getSecretName() {
        return secretName;
    }

    public void setSecretName(String secretName) {
        this.secretName = secretName;
    }

    public String getSecretUserName() {
        return secretUserName;
    }

    public void setSecretUserName(String secretUserName) {
        this.secretUserName = secretUserName;
    }

    public String getSecretPassword() {
        return secretPassword;
    }

    public void setSecretPassword(String secretPassword) {
        this.secretPassword = secretPassword;
    }

    public String getSecretAnswers() {
        return secretAnswers;
    }

    public void setSecretAnswers(String secretAnswers) {
        this.secretAnswers = secretAnswers;
    }


    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String secretName;
    private String secretUserName;
    private String secretPassword;
    private String secretAnswers;

}


