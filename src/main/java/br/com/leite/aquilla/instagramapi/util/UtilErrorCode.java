package br.com.leite.aquilla.instagramapi.util;

public class UtilErrorCode {
    private static final String ERROR_MESSAGE = "Unexpected error, contact support stating the value ";
    public static final String AWS_SERVICE_EXCEPTION = ERROR_MESSAGE + "5930[01]";
    public static final String AWS_SDK_EXCEPTION = ERROR_MESSAGE + "5930[02]";

    private UtilErrorCode() {
        throw new IllegalStateException("Utility class");
    }
}
