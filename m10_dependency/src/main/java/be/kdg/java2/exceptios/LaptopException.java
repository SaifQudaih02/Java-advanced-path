package be.kdg.java2.exceptios;

public class LaptopException extends RuntimeException{

    public LaptopException() {
    }

    public LaptopException(String message) {
        super(message);
    }

    public LaptopException(String message, Throwable cause) {
        super(message, cause);
    }

    public LaptopException(Throwable cause) {
        super(cause);
    }

    public LaptopException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
