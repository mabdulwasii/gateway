package ng.com.systemspecs.apigateway.service.dto;

import java.io.Serializable;

public class JournalLineDTO implements Serializable{
    private Long id;
    private WalletAccountDTO walletAccountDTO;
    private Double debit;
    private Double credit;
    private JournalDTO jounalDTO;
    private Long accountNumber;
    private String journalReferences;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public WalletAccountDTO getWalletAccountDTO() {
		return walletAccountDTO;
	}
	public void setWalletAccountDTO(WalletAccountDTO walletAccountDTO) {
		this.walletAccountDTO = walletAccountDTO;
	}
	public Double getDebit() {
		return debit;
	}
	public void setDebit(Double debit) {
		this.debit = debit;
	}
	public Double getCredit() {
		return credit;
	}
	public void setCredit(Double credit) {
		this.credit = credit;
	}
	public JournalDTO getJounalDTO() {
		return jounalDTO;
	}
	public void setJounalDTO(JournalDTO jounalDTO) {
		this.jounalDTO = jounalDTO;
	}
	public Long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getJournalReferences() {
		return journalReferences;
	}
	public void setJournalReferences(String journalReferences) {
		this.journalReferences = journalReferences;
	}
}
