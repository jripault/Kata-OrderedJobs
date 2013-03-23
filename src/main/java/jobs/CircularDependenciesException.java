package jobs;

/**
 * User: JRI <julien.ripault@atos.net>
 * Date: 23/03/13
 */
public class CircularDependenciesException extends RuntimeException {
    public CircularDependenciesException() {
        super();    //To change body of overridden methods use File | Settings | File Templates.
    }

    public CircularDependenciesException(String message) {
        super(message);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public CircularDependenciesException(String message, Throwable cause) {
        super(message, cause);    //To change body of overridden methods use File | Settings | File Templates.
    }
}
