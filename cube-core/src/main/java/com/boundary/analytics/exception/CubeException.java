package com.boundary.analytics.exception;

/***
 *
 * @author ray
 */
public class CubeException extends RuntimeException {

    public CubeException(String message) {
        super(message);
    }

    public CubeException(String message, Throwable cause) {
        super(message, cause);
    }

    public CubeException(Throwable cause) {
        super(cause);
    }
}
