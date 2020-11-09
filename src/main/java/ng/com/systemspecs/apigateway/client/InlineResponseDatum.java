package ng.com.systemspecs.apigateway.client;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "paymentReference",
    "amount",
    "paymentState",
    "paymentDate",
    "processorId",
    "transactionId",
    "tokenized",
    "paymentToken",
    "cardType",
    "debitedAmount",
    "message",
    "paymentChannel",
    "customerId",
    "firstName",
    "lastName",
    "phoneNumber",
    "email",
    "narration"
})
public class InlineResponseDatum {

    @JsonProperty("paymentReference")
    private String paymentReference;
    @JsonProperty("amount")
    private Double amount;
    @JsonProperty("paymentState")
    private String paymentState;
    @JsonProperty("paymentDate")
    private String paymentDate;
    @JsonProperty("processorId")
    private String processorId;
    @JsonProperty("transactionId")
    private String transactionId;
    @JsonProperty("tokenized")
    private Boolean tokenized;
    @JsonProperty("paymentToken")
    private Object paymentToken;
    @JsonProperty("cardType")
    private Object cardType;
    @JsonProperty("debitedAmount")
    private Double debitedAmount;
    @JsonProperty("message")
    private String message;
    @JsonProperty("paymentChannel")
    private Object paymentChannel;
    @JsonProperty("customerId")
    private String customerId;
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("phoneNumber")
    private String phoneNumber;
    @JsonProperty("email")
    private String email;
    @JsonProperty("narration")
    private String narration;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("paymentReference")
    public String getPaymentReference() {
        return paymentReference;
    }

    @JsonProperty("paymentReference")
    public void setPaymentReference(String paymentReference) {
        this.paymentReference = paymentReference;
    }

    @JsonProperty("amount")
    public Double getAmount() {
        return amount;
    }

    @JsonProperty("amount")
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @JsonProperty("paymentState")
    public String getPaymentState() {
        return paymentState;
    }

    @JsonProperty("paymentState")
    public void setPaymentState(String paymentState) {
        this.paymentState = paymentState;
    }

    @JsonProperty("paymentDate")
    public String getPaymentDate() {
        return paymentDate;
    }

    @JsonProperty("paymentDate")
    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    @JsonProperty("processorId")
    public String getProcessorId() {
        return processorId;
    }

    @JsonProperty("processorId")
    public void setProcessorId(String processorId) {
        this.processorId = processorId;
    }

    @JsonProperty("transactionId")
    public String getTransactionId() {
        return transactionId;
    }

    @JsonProperty("transactionId")
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    @JsonProperty("tokenized")
    public Boolean getTokenized() {
        return tokenized;
    }

    @JsonProperty("tokenized")
    public void setTokenized(Boolean tokenized) {
        this.tokenized = tokenized;
    }

    @JsonProperty("paymentToken")
    public Object getPaymentToken() {
        return paymentToken;
    }

    @JsonProperty("paymentToken")
    public void setPaymentToken(Object paymentToken) {
        this.paymentToken = paymentToken;
    }

    @JsonProperty("cardType")
    public Object getCardType() {
        return cardType;
    }

    @JsonProperty("cardType")
    public void setCardType(Object cardType) {
        this.cardType = cardType;
    }

    @JsonProperty("debitedAmount")
    public Double getDebitedAmount() {
        return debitedAmount;
    }

    @JsonProperty("debitedAmount")
    public void setDebitedAmount(Double debitedAmount) {
        this.debitedAmount = debitedAmount;
    }

    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    @JsonProperty("message")
    public void setMessage(String message) {
        this.message = message;
    }

    @JsonProperty("paymentChannel")
    public Object getPaymentChannel() {
        return paymentChannel;
    }

    @JsonProperty("paymentChannel")
    public void setPaymentChannel(Object paymentChannel) {
        this.paymentChannel = paymentChannel;
    }

    @JsonProperty("customerId")
    public String getCustomerId() {
        return customerId;
    }

    @JsonProperty("customerId")
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @JsonProperty("firstName")
    public String getFirstName() {
        return firstName;
    }

    @JsonProperty("firstName")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @JsonProperty("lastName")
    public String getLastName() {
        return lastName;
    }

    @JsonProperty("lastName")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonProperty("phoneNumber")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @JsonProperty("phoneNumber")
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("email")
    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty("narration")
    public String getNarration() {
        return narration;
    }

    @JsonProperty("narration")
    public void setNarration(String narration) {
        this.narration = narration;
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
