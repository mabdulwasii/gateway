package ng.com.systemspecs.apigateway.client;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "status",
    "responseCode",
    "responseMsg",
    "iResponseCode",
    "iResponseMessage",
    "appVersionCode",
    "responseData",
    "data"
})
public class InlineStatusResponse {

    @JsonProperty("status")
    private Object status;
    @JsonProperty("responseCode")
    private String responseCode;
    @JsonProperty("responseMsg")
    private String responseMsg;
    @JsonProperty("iResponseCode")
    private Object iResponseCode;
    @JsonProperty("iResponseMessage")
    private Object iResponseMessage;
    @JsonProperty("appVersionCode")
    private Object appVersionCode;
    @JsonProperty("responseData")
    private List<InlineResponseDatum> responseData = null;
    @JsonProperty("data")
    private Object data;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("status")
    public Object getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(Object status) {
        this.status = status;
    }

    @JsonProperty("responseCode")
    public String getResponseCode() {
        return responseCode;
    }

    @JsonProperty("responseCode")
    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    @JsonProperty("responseMsg")
    public String getResponseMsg() {
        return responseMsg;
    }

    @JsonProperty("responseMsg")
    public void setResponseMsg(String responseMsg) {
        this.responseMsg = responseMsg;
    }

    @JsonProperty("iResponseCode")
    public Object getIResponseCode() {
        return iResponseCode;
    }

    @JsonProperty("iResponseCode")
    public void setIResponseCode(Object iResponseCode) {
        this.iResponseCode = iResponseCode;
    }

    @JsonProperty("iResponseMessage")
    public Object getIResponseMessage() {
        return iResponseMessage;
    }

    @JsonProperty("iResponseMessage")
    public void setIResponseMessage(Object iResponseMessage) {
        this.iResponseMessage = iResponseMessage;
    }

    @JsonProperty("appVersionCode")
    public Object getAppVersionCode() {
        return appVersionCode;
    }

    @JsonProperty("appVersionCode")
    public void setAppVersionCode(Object appVersionCode) {
        this.appVersionCode = appVersionCode;
    }

    @JsonProperty("responseData")
    public List<InlineResponseDatum> getResponseData() {
        return responseData;
    }

    @JsonProperty("responseData")
    public void setResponseData(List<InlineResponseDatum> responseData) {
        this.responseData = responseData;
    }

    @JsonProperty("data")
    public Object getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(Object data) {
        this.data = data;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
