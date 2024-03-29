package ng.com.systemspecs.apigateway.service.kafka.deserializer;

public class DeserializationError {

    private byte[] data;

    private Exception exception;

    public DeserializationError(byte[] data, Exception exception) {
        this.data = data;
        this.exception = exception;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    @Override
    public String toString() {
        return "DeserializationError{" +
            "data='" + new String(data) + "'" +
            ", exception=" + exception +
            '}';
    }
}
