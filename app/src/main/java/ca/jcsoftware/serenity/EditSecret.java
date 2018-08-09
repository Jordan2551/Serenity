package ca.jcsoftware.serenity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.InvocationTargetException;

import ca.jcsoftware.serenity.database.DbHelper;
import ca.jcsoftware.serenity.helper.GenerateKey;
import ca.jcsoftware.serenity.model.Secret;

public class EditSecret extends Activity {

    private DbHelper dbHelper;
    private GenerateKey passwordGenerator;
    private Secret secret;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_secret);
        dbHelper = new DbHelper(getApplicationContext());
        passwordGenerator = new GenerateKey();
        Intent i = getIntent();
        this.secret = (Secret)i.getSerializableExtra("secret");
        setTextView("Edit " + secret.getSecretName(), R.id.secretNameTitle);
        setTextView(secret.getSecretName(), R.id.secretName);
        setTextView(secret.getSecretUserName(), R.id.secretUsername);
        setTextView(secret.getSecretPassword(), R.id.secretPassword);
        setTextView(secret.getSecretAnswers(), R.id.secretAnswers);
    }

    public void editSecretClick(View view){
        secret.setSecretUserName(getTextView(R.id.secretUsername));
        secret.setSecretPassword(getTextView(R.id.secretPassword));
        secret.setSecretName(getTextView(R.id.secretName));
        secret.setSecretAnswers(getTextView(R.id.secretAnswers));
        dbHelper.getDB().daoAccess().updateSecret(secret);
        Toast.makeText(this, R.string.secret_updated, Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, Vault.class);
        startActivity(intent);
    }

    public void generatePassword(View view){

        if(getTextView(R.id.secretLength).length() > 0){

            int secretLength = Integer.parseInt(getTextView(R.id.secretLength));

            if(secretLength >= GenerateKey.PASSWORD_MIN_LENGTH && secretLength <= GenerateKey.PASSWORD_MAX_LENGTH){
                setTextView(passwordGenerator.getSecurePassword(secretLength), R.id.secretPassword);
            }
            else{
                Toast.makeText(this, "Password length must be between " + GenerateKey.PASSWORD_MIN_LENGTH + " and " + GenerateKey.PASSWORD_MAX_LENGTH + " (inclusive)", Toast.LENGTH_LONG).show();
            }

        }

        else{
            Toast.makeText(this, "Please choose a password length between " + GenerateKey.PASSWORD_MIN_LENGTH + " and " + GenerateKey.PASSWORD_MAX_LENGTH + " (inclusive)", Toast.LENGTH_LONG).show();
        }

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
