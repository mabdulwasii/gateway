package ng.com.systemspecs.apigateway.service.dto;

public class FingerDTO extends  java.util.concurrent.ConcurrentHashMap<String,String> {

	private String  key;
	private String  value;
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
}
