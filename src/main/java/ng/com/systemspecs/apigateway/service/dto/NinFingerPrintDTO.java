package ng.com.systemspecs.apigateway.service.dto;

import java.io.Serializable;

public class NinFingerPrintDTO implements Serializable {
  
	 private String  refId;
	 private String  authorisationCode;
	 private String  authorisationChannel;
	 private FingerDTO[]   customFields = new FingerDTO[3];
	 
	public String getRefId() {
		return refId;
	}
	public void setRefId(String refId) {
		this.refId = refId;
	}
	public String getAuthorisationCode() {
		return authorisationCode;
	}
	public void setAuthorisationCode(String authorisationCode) {
		this.authorisationCode = authorisationCode;
	}
	public String getAuthorisationChannel() {
		return authorisationChannel;
	}
	public void setAuthorisationChannel(String authorisationChannel) {
		this.authorisationChannel = authorisationChannel;
	}
	public FingerDTO[] getCustomFields() {
		return customFields;
	}
	public void setCustomFields(FingerDTO[] customFields) {
		this.customFields = customFields;
	}

	 
}
