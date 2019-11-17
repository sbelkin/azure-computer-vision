package io.sbelkin.azure.computervision.exceptions;

public class AzureComputerVisionException extends Exception {

    public AzureComputerVisionException() {
        super();
    }

    public AzureComputerVisionException(String message) {
        super(message);
    }

    public AzureComputerVisionException(String message, Throwable cause) {
        super(message, cause);
    }

    public AzureComputerVisionException(Throwable cause) {
        super(cause);
    }
}
