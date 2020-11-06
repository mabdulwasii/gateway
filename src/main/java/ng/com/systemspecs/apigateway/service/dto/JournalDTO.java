package ng.com.systemspecs.apigateway.service.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class JournalDTO implements Serializable {
    private Long id;	
    
	private String reference;
	private LocalDate transDate;
	private String memo;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public LocalDate getTransDate() {
		return transDate;
	}
	public void setTransDate(LocalDate transDate) {
		this.transDate = transDate;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
}
