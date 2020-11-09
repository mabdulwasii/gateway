package ng.com.systemspecs.apigateway.service.accounting;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.mapstruct.ap.internal.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ng.com.systemspecs.apigateway.domain.Journal;
import ng.com.systemspecs.apigateway.domain.JournalLine;
import ng.com.systemspecs.apigateway.domain.WalletAccount;
import ng.com.systemspecs.apigateway.service.JournalLineService;
import ng.com.systemspecs.apigateway.service.JournalService;
import ng.com.systemspecs.apigateway.service.WalletAccountService;
import ng.com.systemspecs.apigateway.service.dto.FundDTO;
import ng.com.systemspecs.apigateway.service.dto.JournalLineDTO;
import ng.com.systemspecs.apigateway.service.dto.PaymentResponseDTO;
import ng.com.systemspecs.apigateway.service.dto.PaymentTransactionDTO;

@Service
@Transactional
public class AccountingService {

	private final WalletAccountService walletAccountService;
	private final JournalService journalService;
	private final JournalLineService journalLineService;

	public AccountingService(JournalService journalService, JournalLineService journalLineService,
			WalletAccountService walletAccountService) {
		this.walletAccountService = walletAccountService;
		this.journalService = journalService;
		this.journalLineService = journalLineService;
	}

	public String fundWallet(FundDTO fundDTO) {
		String response ="Success";
		if(Strings.isEmpty(fundDTO.getChannel()))
				return "Channel Cannot be empty";
		
		if(fundDTO.getChannel().equalsIgnoreCase("WalletToWallet")) {
			response = walletToWallet(fundDTO);
		}else if(fundDTO.getChannel().equalsIgnoreCase("BankToWallet")) {
			response = BankToWallet(fundDTO);
		}else if(fundDTO.getChannel().equalsIgnoreCase("WalletToBank"))
			response = WalletToBank(fundDTO);
		
		return response;
	}
	
	private String doubleEntry(List<JournalLineDTO> lines, String memo) {
		Random random = new Random();

		String generatedString = random.ints(97, 122 + 1).limit(15)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
		Journal journal = new Journal();
		journal.setReference(generatedString);
		journal.setTransDate(LocalDate.now());
		journal.setMemo(memo);
		journal = journalService.save(journal);
		for (JournalLineDTO line : lines) {
			JournalLine journalLine = new JournalLine();
			journalLine.setCredit(line.getCredit());
			journalLine.setDebit(line.getDebit());
			WalletAccount account = walletAccountService.findOneByAccountNumber(line.getAccountNumber());
			account.setCurrentBalance(account.getCurrentBalance() + journalLine.getCredit());
			account.setCurrentBalance(account.getCurrentBalance() - journalLine.getDebit());
			account = walletAccountService.save(account);
			journalLineService.save(journalLine, journal, account);

		}
		return "successful";
	}

	private String walletToWallet(FundDTO fundDTO) {
		String memo = "Wallet Transfer from Account " + fundDTO.getSourceAccountNumber() + " To "+fundDTO.getAccountNumber();
		ArrayList<JournalLineDTO> lines = new ArrayList<JournalLineDTO>();
		JournalLineDTO journalLine1DTO = new JournalLineDTO();

		journalLine1DTO.setAccountNumber(Long.parseLong(fundDTO.getSourceAccountNumber()));
		journalLine1DTO.setCredit(0.00);
		journalLine1DTO.setDebit(fundDTO.getAmount() + getIntraFee(fundDTO.getAmount()));

		JournalLineDTO journalLine2DTO = new JournalLineDTO();

		journalLine2DTO.setAccountNumber(fundDTO.getAccountNumber());
		journalLine2DTO.setCredit(fundDTO.getAmount());
		journalLine2DTO.setDebit(0.00);

		JournalLineDTO journalLine3DTO = new JournalLineDTO();

		journalLine3DTO.setAccountNumber(1000000010L);
		journalLine3DTO.setCredit(getIntraFee(fundDTO.getAmount()));
		journalLine3DTO.setDebit(0.00);

		lines.add(journalLine1DTO);
		lines.add(journalLine2DTO);
		lines.add(journalLine3DTO);
		return doubleEntry(lines, memo);

	}
	

	private String BankToWallet(FundDTO fundDTO) {
		String memo = "Inflow To "+ fundDTO.getAccountNumber();
		ArrayList<JournalLineDTO> lines = new ArrayList<JournalLineDTO>();
		JournalLineDTO journalLine1DTO = new JournalLineDTO();

		journalLine1DTO.setAccountNumber(1000000000L);
		journalLine1DTO.setCredit(0.00);
		journalLine1DTO.setDebit(fundDTO.getAmount() + 20.00);

		JournalLineDTO journalLine2DTO = new JournalLineDTO();

		journalLine2DTO.setAccountNumber(fundDTO.getAccountNumber());
		journalLine2DTO.setCredit(fundDTO.getAmount());
		journalLine2DTO.setDebit(0.00);

		JournalLineDTO journalLine3DTO = new JournalLineDTO();

		journalLine3DTO.setAccountNumber(1000000001L);
		journalLine3DTO.setCredit(20.00);
		journalLine3DTO.setDebit(0.00);

		lines.add(journalLine1DTO);
		lines.add(journalLine2DTO);
		lines.add(journalLine3DTO);
		return doubleEntry(lines, memo);

	}
	private String WalletToBank(FundDTO fundDTO) {
		String memo = "Inflow To "+ fundDTO.getAccountNumber();
		ArrayList<JournalLineDTO> lines = new ArrayList<JournalLineDTO>();
		JournalLineDTO journalLine1DTO = new JournalLineDTO();

		journalLine1DTO.setAccountNumber(1000000000L);
		journalLine1DTO.setCredit(fundDTO.getAmount() + 10.00);
		journalLine1DTO.setDebit(0.00);

		JournalLineDTO journalLine2DTO = new JournalLineDTO();

		journalLine2DTO.setAccountNumber(Long.parseLong(fundDTO.getSourceAccountNumber()));
		journalLine2DTO.setCredit(0.00);
		journalLine2DTO.setDebit(fundDTO.getAmount()+10.00);

		JournalLineDTO journalLine3DTO = new JournalLineDTO();

		journalLine3DTO.setAccountNumber(1000000001L);
		journalLine3DTO.setCredit(10.00);
		journalLine3DTO.setDebit(0.00);

		lines.add(journalLine1DTO);
		lines.add(journalLine2DTO);
		lines.add(journalLine3DTO);
		return doubleEntry(lines, memo);

	}
	private Double getIntraFee(Double amount) {
		Double fee = 0.00;
		Double percentFee = (1 * amount) / 100;
		fee = percentFee < 300.00 ? percentFee : 300;
		return fee;
	}

}
