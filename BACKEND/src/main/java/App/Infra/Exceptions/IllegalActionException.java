package App.Infra.Exceptions;

public class IllegalActionException extends RuntimeException {

    public IllegalActionException() {
        super("Ops...Algo errado");
    }

    public IllegalActionException(String message) {
        super(message);
    }

}
