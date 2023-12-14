package com.bank.resona.perdania.backofficebe.config;

import com.bank.resona.perdania.backofficebe.exception.UnAuthorizeException;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.http.HttpStatus;

@Slf4j
public class PasswordConfig {

    public static final String generatePassword(String password){
        log.info("plain text password = {} ",password);
        String hashPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        boolean valid = BCrypt.checkpw(password, hashPassword);
        if (valid){
            log.info("bcrypt password = {}",hashPassword);
            return hashPassword;
        }
        return null;
    }

    public static final boolean checkPassword(String hashedPassword, String plainText){
        boolean isValid = BCrypt.checkpw(plainText,hashedPassword);
        if (isValid){
            log.info("password match..");
            return true;
        }
        throw new UnAuthorizeException("Sorry username or password is wrong !", HttpStatus.UNAUTHORIZED.value());
    }
}
