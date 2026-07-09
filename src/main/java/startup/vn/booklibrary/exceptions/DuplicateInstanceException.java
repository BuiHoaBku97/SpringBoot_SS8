package startup.vn.booklibrary.exceptions;

public class DuplicateInstanceException extends RuntimeException {
    public DuplicateInstanceException(String message){
        super(message);
    }
}
