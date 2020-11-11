package ng.com.systemspecs.apigateway.service.dto;

import java.util.List;

public class GenericResponseDTO {

    private String message;
    private String code;
    private Object data;

    public GenericResponseDTO() {
    }

    public GenericResponseDTO(String message, String code, List<Object> data) {
        this.message = message;
        this.code = code;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
