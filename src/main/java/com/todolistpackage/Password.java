package com.todolistpackage;

import java.security.NoSuchAlgorithmException;

/**
 * Created by megasoch on 24.12.2015.
 */
public class Password {

    public static String passwordHash(String password, int n) throws NoSuchAlgorithmException {
        java.security.MessageDigest d;
        d = java.security.MessageDigest.getInstance("SHA-1");
        String result = password;
        for (int i = 0; i < n; i++) {
            d.reset();
            d.update(result.getBytes());
            byte[] data = d.digest();
            result = bytesToHex(data);
        }
        return result;
    }

    private static String bytesToHex(byte[] b) {
        char hexDigit[] = {'0', '1', '2', '3', '4', '5', '6', '7',
                '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        StringBuffer buf = new StringBuffer();
        for (int j = 0; j < b.length; j++) {
            buf.append(hexDigit[(b[j] >> 4) & 0x0f]);
            buf.append(hexDigit[b[j] & 0x0f]);
        }
        return buf.toString();
    }
}
