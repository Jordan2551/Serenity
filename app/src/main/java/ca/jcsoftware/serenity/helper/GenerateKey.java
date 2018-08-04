package ca.jcsoftware.serenity.helper;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;

import ca.jcsoftware.serenity.R;

/**
 * @author Crunchify.com
 * Best way to generate very secure random Password automatically
 *
 */

public class GenerateKey {

    // SecureRandom() constructs a secure random number generator (RNG) implementing the default random number algorithm.
    private SecureRandom crunchifyRandomNo = new SecureRandom();

    private ArrayList<Object> crunchifyValueObj;

    // Just initialize ArrayList crunchifyValueObj and add ASCII Decimal Values
    public GenerateKey() {

        crunchifyValueObj = new ArrayList<>();

        // Adding ASCII Decimal value between 33 and 53
        for (int i = 33; i < 53; i++) {
            crunchifyValueObj.add((char) i);
        }

        // Adding ASCII Decimal value between 54 and 85
        for (int i = 54; i < 85; i++) {
            crunchifyValueObj.add((char) i);
        }

        // Adding ASCII Decimal value between 86 and 128
        for (int i = 86; i < 127; i++) {
            crunchifyValueObj.add((char) i);
        }
        crunchifyValueObj.add((char) 64);

        // rotate() rotates the elements in the specified list by the specified distance. This will create strong password
        Collections.rotate(crunchifyValueObj, 5);
    }

    // Get Char value from above added Decimal values
    public char crunchifyGetRandom() {
        char crunchifyChar = (char) this.crunchifyValueObj.get(crunchifyRandomNo.nextInt(this.crunchifyValueObj.size()));
        return crunchifyChar;
    }

    public String getSecurePassword(int passwordLength){

        StringBuilder crunchifyBuffer = new StringBuilder();

        // Let's print total 8 passwords
        for (int loop = 1; loop <= 8; loop++) {
            // Password length should be 23 characters
            for (int length = 0; length < 23; length++) {
                crunchifyBuffer.append(crunchifyGetRandom());
            }
            crunchifyBuffer.setLength(passwordLength);
        }

        return crunchifyBuffer.toString();

    }




}