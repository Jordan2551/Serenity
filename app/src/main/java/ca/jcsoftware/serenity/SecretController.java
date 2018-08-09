package ca.jcsoftware.serenity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import ca.jcsoftware.serenity.database.DbHelper;
import ca.jcsoftware.serenity.helper.Helper;
import ca.jcsoftware.serenity.model.Secret;

public class SecretController extends Activity {

    private DbHelper dbHelper;
    private Secret secret;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secret_controller);
        dbHelper = new DbHelper(getApplicationContext());
        Intent i = getIntent();
        this.secret = (Secret)i.getSerializableExtra("secret");
        setTextView("Viewing " + secret.getSecretName(), R.id.secretName);
        setTextView(secret.getSecretUserName(), R.id.secretUsername);
        setTextView(secret.getSecretPassword(), R.id.secretPassword);
        if(secret.getSecretAnswers().length() > 0)
            setTextView(secret.getSecretAnswers(), R.id.secretAnswers);
        else
            setTextView("This secret does not have any security questions", R.id.secretAnswers);
    }

    public void editSecretClick(View view){
        Intent intent = new Intent(this, EditSecret.class);
        intent.putExtra("secret", secret);
        startActivity(intent);
    }

    public void deleteSecretClick(View view){
        dbHelper.getDB().daoAccess().deleteSecret(this.secret);
        Toast.makeText(this, R.string.secret_deleted, Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, Vault.class);
        startActivity(intent);
    }

    public void copySecretUsernameClick(View view){
        Helper.copy(this, getTextView(R.id.secretUsername), this.getString(R.string.clipboard_username_copy));
    }

    public void copySecretPasswordClick(View view){
        Helper.copy(this, getTextView(R.id.secretPassword), this.getString(R.string.clipboard_password_copy));
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
