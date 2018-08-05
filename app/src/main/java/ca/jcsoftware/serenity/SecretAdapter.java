package ca.jcsoftware.serenity;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.content.ClipboardManager;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

import ca.jcsoftware.serenity.controller.SecretController;
import ca.jcsoftware.serenity.helper.Helper;
import ca.jcsoftware.serenity.model.Secret;

public class SecretAdapter extends ArrayAdapter<Secret> {

    private Context context;
    private List<Secret> secretList;


    public SecretAdapter(@NonNull Context context, ArrayList<Secret>secretList) {
        super(context, 0, secretList);
        this.context = context;
        this.secretList = secretList;
    }

   public View getView(int position, View listItem, ViewGroup parent){
        if(listItem == null)
            listItem = LayoutInflater.from(context).inflate(R.layout.secret_item, parent, false);

        final Secret currentSecret = secretList.get(position);
        TextView secretName = (TextView) listItem.findViewById(R.id.secretName);
        TextView secretPassword = (TextView) listItem.findViewById(R.id.secretPassword);
        ImageButton copyButton = (ImageButton)listItem.findViewById(R.id.copyButton);
        copyButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Helper.copy(getContext(), currentSecret.getSecretPassword(), context.getString(R.string.clipboard_password_copy));
            }
        });
       ImageButton viewButton = (ImageButton)listItem.findViewById(R.id.viewButton);
       viewButton.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(getContext(), SecretController.class);
               intent.putExtra("secret", currentSecret);
               context.startActivity(intent);
           }
       });

       secretName.setText(currentSecret.getSecretName());
       secretPassword.setText(currentSecret.getSecretPassword());
       return listItem;

   }





}
