package com.authorization.authorization.util;

import com.authorization.authorization.exception.BadUserFieldException;
import com.authorization.authorization.model.dto.RegistrationDataDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.experimental.UtilityClass;
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
public class UserUtil {

    public void validationUserFields(RegistrationDataDTO registrationDataDTO) {
        if (registrationDataDTO.getFirstName().length() > 15) {
            throw new BadUserFieldException("Firstname is too long!");
        } else if (registrationDataDTO.getSecondName().length() > 15) {
            throw new BadUserFieldException("Secondname is too long!");
        }
    }


    private static final int SALT_LENGTH = 16;
    private static final int ITERATION_COUNT = 64000;
    private static final String HASH_ALGORITHM = "PBKDF2WithHmacSHA512";
    private static final int KEY_LENGTH = 256;

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
