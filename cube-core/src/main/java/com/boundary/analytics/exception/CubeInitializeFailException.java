package com.boundary.analytics.exception;

/**
 * cube初始化异常
 *
 * @author ray
 */
public class CubeInitializeFailException extends CubeException {

    public CubeInitializeFailException(String message) {
        super(message);
    }

    public CubeInitializeFailException(String message, Throwable cause) {
        super(message, cause);
    }

    public CubeInitializeFailException(Throwable cause) {
        super(cause);
    }
}
