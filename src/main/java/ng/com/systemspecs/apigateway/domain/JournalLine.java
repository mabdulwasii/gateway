package ng.com.systemspecs.apigateway.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


@Entity
@Table(name = "jounal_line")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class JournalLine {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;
    
    @ManyToOne
   // @JsonIgnoreProperties(value = "jounalLines", allowSetters = true)
    private WalletAccount walletAccount;
    @Column(columnDefinition = "double default 0.00")
    private Double debit;
    
    @Column(columnDefinition = "double default 0.00")
    private Double credit;
    
    
    @ManyToOne
    //@JsonIgnoreProperties(value = "jounalLines", allowSetters = true)    
    private Journal jounal;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public Journal getJounal() {
		return jounal;
	}
	public void setJounal(Journal jounal) {
		this.jounal = jounal;
	}
	public WalletAccount getWalletAccount() {
		return walletAccount;
	}
	public void setWalletAccount(WalletAccount walletAccount) {
		this.walletAccount = walletAccount;
	}
}
