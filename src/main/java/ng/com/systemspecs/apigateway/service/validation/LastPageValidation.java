package ng.com.systemspecs.apigateway.service.validation;

import org.apache.logging.log4j.util.Strings;

import ng.com.systemspecs.apigateway.service.dto.RegistrationLastPageDTO;

public class LastPageValidation {
	private String errors = "";
	private boolean isValid = true;
	private String MALE = "MALE";
	private String FEMALE = "FEMALE";
	private RegistrationLastPageDTO input;
	public LastPageValidation(RegistrationLastPageDTO data) {
		this.isValid = true;
		this.errors = "";
		this.input = data;
	}
	
	public boolean checkErrors() {
		if(Strings.isEmpty(this.input.getAddress())) {
			this.isValid = false;
			this.errors += " The address field is required!";
		}
		if(this.input.getLatitude() == null) {
			this.isValid = false;
			this.errors += " The Latitude field is required!";
		}
		if(this.input.getLongitude() == null) {
			this.isValid = false;
			this.errors += " The Longitude field is required!";
		}
		if(this.input.getDateOfBirth() == null) {
			this.isValid = false;
			this.errors += " The date of birth field is required!";
		}
		if(this.input.getGender() == null) {
			this.isValid = false;
			this.errors += " The gender field is required!";
		}
		if(!this.input.getGender().equals(FEMALE) && !this.input.getGender().equals(MALE)) {
			this.isValid = false;
			this.errors += " The gender must either be a MALE or a FEMALE";
		}
		return this.isValid;
	}
	
	public String getErrors() {
		return this.errors;
	}
}
