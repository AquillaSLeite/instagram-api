package br.com.leite.aquilla.instagramapi.util;

public class UtilConstants {

    private static final String USER_NOT_FOUND = "? with id ? not found";
    public static final String USERNAME_ALREADY_EXISTS = "Username already exists";
    public static final String EMAIL_ALREADY_EXISTS = "Email already exists";
    public static final String IDENTITY_FOLLOW_USER = "You cannot follow yourself";

    public static String entityNotFoundReplace(final String field, final Long id) {
        return USER_NOT_FOUND.replaceFirst("[?]", field).replaceFirst("[?]", id.toString());
    }

    private UtilConstants() {
        throw new IllegalStateException("Utility class");
    }
}
