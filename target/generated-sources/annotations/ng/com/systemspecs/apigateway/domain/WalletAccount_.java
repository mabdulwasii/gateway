package ng.com.systemspecs.apigateway.domain;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(WalletAccount.class)
public abstract class WalletAccount_ {

	public static volatile SingularAttribute<WalletAccount, LocalDate> dateOpened;
	public static volatile SingularAttribute<WalletAccount, Scheme> scheme;
	public static volatile SingularAttribute<WalletAccount, String> accountName;
	public static volatile SingularAttribute<WalletAccount, Double> currentBalance;
	public static volatile SingularAttribute<WalletAccount, WalletAccountType> walletAccountType;
	public static volatile SingularAttribute<WalletAccount, Long> id;
	public static volatile SingularAttribute<WalletAccount, Profile> accountOwner;
	public static volatile SingularAttribute<WalletAccount, Long> accountNumber;

	public static final String DATE_OPENED = "dateOpened";
	public static final String SCHEME = "scheme";
	public static final String ACCOUNT_NAME = "accountName";
	public static final String CURRENT_BALANCE = "currentBalance";
	public static final String WALLET_ACCOUNT_TYPE = "walletAccountType";
	public static final String ID = "id";
	public static final String ACCOUNT_OWNER = "accountOwner";
	public static final String ACCOUNT_NUMBER = "accountNumber";

}

