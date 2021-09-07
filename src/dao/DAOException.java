package dao;

/**
 * Maneja las exepciones de DAO.
 */

public class DAOException extends Exception{

 static final long serialVersionUID = 1L;

	public DAOException(String message, Throwable cause) {
		super(message, cause);
	}

	public DAOException(String message) {
		super(message);
	}

	public DAOException(Throwable cause) {
		super(cause);
	}

	
}
