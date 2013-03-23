package jobs;

/**
 * User: JRI <julien.ripault@atos.net>
 * Date: 23/03/13
 */
public class SelfReferencingException extends RuntimeException {
    public SelfReferencingException() {
        super();    //To change body of overridden methods use File | Settings | File Templates.
    }

    public SelfReferencingException(String message) {
        super(message);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public SelfReferencingException(String message, Throwable cause) {
        super(message, cause);    //To change body of overridden methods use File | Settings | File Templates.
    }
}
