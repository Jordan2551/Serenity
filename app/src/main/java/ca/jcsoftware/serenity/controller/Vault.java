package ca.jcsoftware.serenity.controller;

import android.app.Activity;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ca.jcsoftware.serenity.CreateSecret;
import ca.jcsoftware.serenity.R;
import ca.jcsoftware.serenity.SecretAdapter;
import ca.jcsoftware.serenity.database.Db;
import ca.jcsoftware.serenity.database.DbHelper;
import ca.jcsoftware.serenity.model.Secret;

public class Vault extends Activity {

    private DbHelper dbHelper;
    private ListView listView;
    List<Secret> secretList;
    private SecretAdapter secretAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vault);
        dbHelper = new DbHelper(getApplicationContext());
        listView = (ListView) findViewById(R.id.secretsList);

        secretList = dbHelper.getDB().daoAccess().fetchAllSecrets();

        if(secretList != null){
            secretAdapter = new SecretAdapter(this, (ArrayList<Secret>) secretList);
            listView.setAdapter(secretAdapter);
        }

    }

    public void createSecretClick(View view){
        Intent intent = new Intent(this, CreateSecret.class);
        startActivity(intent);
    }

    private void setTextView(String text, int id){
        TextView textView = findViewById(id);
        textView.setText(text);
    }

    private String getTextView(int id){
        TextView textView = findViewById(id);
        return textView.getText().toString();
    }


}


