package fr.jodev.elite.exceptions;

/**
 * Represents Exceptions thrown by the Data Access Layer.
 * @see <a href="http://www.java2s.com/Code/Java/Hibernate/GenericDaoFindAll.htm">GenericDao</a>
 */
public class DataAccessLayerException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DataAccessLayerException() {
    }

    public DataAccessLayerException(String message) {
        super(message);
    }

    public DataAccessLayerException(Throwable cause) {
        super(cause);
    }

    public DataAccessLayerException(String message, Throwable cause) {
        super(message, cause);
    }
}
