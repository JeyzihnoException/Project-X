package com.platform.platform.util;

import jakarta.servlet.http.HttpServletRequest;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.List;

@UtilityClass
public class Util {


    private static final int SALT_LENGTH = 16;
    private static final int ITERATION_COUNT = 64000;
    private static final String HASH_ALGORITHM = "PBKDF2WithHmacSHA512";
    private static final int KEY_LENGTH = 256;
    public static String getRequestRemoteAddr() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .currentRequestAttributes()).getRequest();
        return request.getRemoteAddr();
    }

    public static List<String> getCurrentUserPrivileges() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
    }

    public static String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_LENGTH];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    public static String hashPassword(String password, String salt)
            throws NoSuchAlgorithmException, InvalidKeySpecException {

        char[] passwordChars = password.toCharArray();
        byte[] saltBytes = Base64.getDecoder().decode(salt);

        PBEKeySpec spec = new PBEKeySpec(passwordChars, saltBytes, ITERATION_COUNT, KEY_LENGTH);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(HASH_ALGORITHM);
        byte[] hashedPassword = keyFactory.generateSecret(spec).getEncoded();

        return Base64.getEncoder().encodeToString(hashedPassword);
    }
}
