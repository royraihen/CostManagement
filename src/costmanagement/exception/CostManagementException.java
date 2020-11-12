package costmanagement.exception;


public class CostManagementException extends Exception {

    /**
     * This method throw an exception if an database error has occurs
     * @param message the message of the error
     */
    public CostManagementException(String message) {
        super(message);
    }

    /**
     *  This method throw an exception if an database error has occurs
     * @param message the message of the error
     * @param rootCause the cause of the error
     */
    public CostManagementException(String message, Throwable rootCause) {
        super(message, rootCause);
    }
}
