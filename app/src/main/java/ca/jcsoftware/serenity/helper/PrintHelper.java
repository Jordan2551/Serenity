package ca.jcsoftware.serenity.helper;

import android.content.Context;
import android.content.Intent;
import android.hardware.fingerprint.FingerprintManager;
import android.os.CancellationSignal;
import android.widget.Toast;

import ca.jcsoftware.serenity.PrintAuth;
import ca.jcsoftware.serenity.Vault;

public class PrintHelper extends FingerprintManager.AuthenticationCallback {

    private CancellationSignal cancellationSignal;
    private Context context;

    public PrintHelper(Context mContext) {
        context = mContext;
    }

    //Implement the startAuth method, which is responsible for starting the fingerprint authentication process//

    public void startAuth(FingerprintManager manager, FingerprintManager.CryptoObject cryptoObject) {
        cancellationSignal = new CancellationSignal();
        manager.authenticate(cryptoObject, cancellationSignal, 0, this, null);
    }

    @Override
    //onAuthenticationError is called when a fatal error has occurred. It provides the error code and error message as its parameters//

    public void onAuthenticationError(int errMsgId, CharSequence errString) {
        Toast.makeText(context, "Authentication error\n" + errString, Toast.LENGTH_LONG).show();
    }

    @Override

    //onAuthenticationFailed is called when the fingerprint doesn’t match with any of the fingerprints registered on the device//

    public void onAuthenticationFailed() {
        Toast.makeText(context, "Authentication failed", Toast.LENGTH_LONG).show();
    }

    @Override

    //onAuthenticationHelp is called when a non-fatal error has occurred. This method provides additional information about the error,
    //so to provide the user with as much feedback as possible I’m incorporating this information into my toast//
    public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
        Toast.makeText(context, "Authentication help\n" + helpString, Toast.LENGTH_LONG).show();
    }

    //onAuthenticationSucceeded is called when a fingerprint has been successfully matched to one of the fingerprints stored on the user’s device//
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
        Toast.makeText(context, "Welcome to the vault", Toast.LENGTH_LONG).show();
        context.startActivity(new Intent(context, Vault.class));
//        printAuth.finish();//Kill the print auth activity from the stack so that the user can't go back.
    }


}
