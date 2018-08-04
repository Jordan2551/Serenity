package ca.jcsoftware.serenity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import ca.jcsoftware.serenity.controller.Vault;
import ca.jcsoftware.serenity.database.DbHelper;
import ca.jcsoftware.serenity.helper.GenerateKey;
import ca.jcsoftware.serenity.model.Secret;

public class CreateSecret extends Activity {

    private DbHelper dbHelper;
    private GenerateKey passwordGenerator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_secret);
        dbHelper = new DbHelper(getApplicationContext());
        passwordGenerator = new GenerateKey();
    }

    public void createSecretClick(View view){
        Secret secret = new Secret();
        secret.setSecretName(getTextView(R.id.secretName));
        secret.setSecretUserName(getTextView(R.id.secretUsername));
        secret.setSecretPassword(getTextView(R.id.secretPassword));
        secret.setSecretAnswers(getTextView(R.id.secretAnswers));
        dbHelper.getDB().daoAccess().insertSecret(secret);
        Toast.makeText(this, R.string.secret_created, Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, Vault.class);
        startActivity(intent);
    }

    public void generatePassword(View view){
        setTextView(passwordGenerator.getSecurePassword(Integer.parseInt(getTextView(R.id.secretLength))), R.id.secretPassword);
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
