package org.jboss.seam.solder.converter;

public class ConverterNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ConverterNotFoundException() {
        super();
    }

    public ConverterNotFoundException(Class<?> from, Class<?> to) {
        super(String.format("Converter not found for %s and %s", from, to));
    }

    public ConverterNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConverterNotFoundException(String message) {
        super(message);
    }

    public ConverterNotFoundException(Throwable cause) {
        super(cause);
    }

}
