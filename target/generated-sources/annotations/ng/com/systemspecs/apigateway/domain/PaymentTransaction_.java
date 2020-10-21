package ng.com.systemspecs.apigateway.domain;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import ng.com.systemspecs.apigateway.domain.enumeration.TransactionType;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PaymentTransaction.class)
public abstract class PaymentTransaction_ {

	public static volatile SingularAttribute<PaymentTransaction, String> sourceAccountBankCode;
	public static volatile SingularAttribute<PaymentTransaction, BigDecimal> amount;
	public static volatile SingularAttribute<PaymentTransaction, String> destinationAccountBankCode;
	public static volatile SingularAttribute<PaymentTransaction, Profile> transactionOwner;
	public static volatile SingularAttribute<PaymentTransaction, String> sourceAccount;
	public static volatile SingularAttribute<PaymentTransaction, String> destinationNarration;
	public static volatile SingularAttribute<PaymentTransaction, String> channel;
	public static volatile SingularAttribute<PaymentTransaction, Long> paymenttransID;
	public static volatile SingularAttribute<PaymentTransaction, String> transactionRef;
	public static volatile SingularAttribute<PaymentTransaction, String> sourceNarration;
	public static volatile SingularAttribute<PaymentTransaction, String> destinationAccountName;
	public static volatile SingularAttribute<PaymentTransaction, TransactionType> transactionType;
	public static volatile SingularAttribute<PaymentTransaction, String> currency;
	public static volatile SingularAttribute<PaymentTransaction, Long> id;
	public static volatile SingularAttribute<PaymentTransaction, String> destinationAccount;
	public static volatile SingularAttribute<PaymentTransaction, String> sourceAccountName;

	public static final String SOURCE_ACCOUNT_BANK_CODE = "sourceAccountBankCode";
	public static final String AMOUNT = "amount";
	public static final String DESTINATION_ACCOUNT_BANK_CODE = "destinationAccountBankCode";
	public static final String TRANSACTION_OWNER = "transactionOwner";
	public static final String SOURCE_ACCOUNT = "sourceAccount";
	public static final String DESTINATION_NARRATION = "destinationNarration";
	public static final String CHANNEL = "channel";
	public static final String PAYMENTTRANS_ID = "paymenttransID";
	public static final String TRANSACTION_REF = "transactionRef";
	public static final String SOURCE_NARRATION = "sourceNarration";
	public static final String DESTINATION_ACCOUNT_NAME = "destinationAccountName";
	public static final String TRANSACTION_TYPE = "transactionType";
	public static final String CURRENCY = "currency";
	public static final String ID = "id";
	public static final String DESTINATION_ACCOUNT = "destinationAccount";
	public static final String SOURCE_ACCOUNT_NAME = "sourceAccountName";

}

