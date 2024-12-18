package jwlee.swm_refactoring.domain.exceptions;

public class UnauthenticatedException extends RuntimeException {
    public UnauthenticatedException() {
        super();
    }

    public UnauthenticatedException(String message) {
        super("로그인이 필요합니다.");
    }
}
