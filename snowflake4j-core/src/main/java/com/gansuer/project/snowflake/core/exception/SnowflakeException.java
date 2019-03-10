package com.gansuer.project.snowflake.core.exception;

public class SnowflakeException extends RuntimeException {

    private static final long serialVersionUID = 3490218806486418076L;
    private int code = 0;

    public SnowflakeException(int code) {
        this.code = code;
    }

    public SnowflakeException(String message) {
        super(message);
    }

    public SnowflakeException(String message, int code) {
        super(message);
        this.code = code;
    }

    public SnowflakeException(String message, Throwable cause, int code) {
        super(message, cause);
        this.code = code;
    }

    public SnowflakeException(Throwable cause, int code) {
        super(cause);
        this.code = code;
    }

    public SnowflakeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace,
        int code) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
    }
}
