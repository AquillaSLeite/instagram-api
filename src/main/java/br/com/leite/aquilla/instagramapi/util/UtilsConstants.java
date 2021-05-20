package br.com.leite.aquilla.instagramapi.util;

public interface UtilsConstants {

    String USER_NOT_FOUND = "? with id ? not found";
    String USERNAME_ALREADY_EXISTS = "Username already exists";
    String EMAIL_ALREADY_EXISTS = "Email already exists";
    String IDENTITY_FOLLOW_USER = "You cannot follow yourself";

    static String entityNotFoundReplace(final String field, final Long id) {
        return USER_NOT_FOUND.replaceFirst("[?]", field).replaceFirst("[?]", id.toString());
    }
}
