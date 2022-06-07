package jpabookk.jpashoppp.exception;

public class NotenoughStockException extends RuntimeException{

    public NotenoughStockException() {
        super();
    }

    public NotenoughStockException(String message) {
        super(message);
    }

    public NotenoughStockException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotenoughStockException(Throwable cause) {
        super(cause);
    }

}
