package ng.com.systemspecs.apigateway.domain;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import ng.com.systemspecs.apigateway.domain.enumeration.Gender;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Profile.class)
public abstract class Profile_ {

	public static volatile SetAttribute<Profile, WalletAccount> walletAccounts;
	public static volatile SingularAttribute<Profile, String> address;
	public static volatile SetAttribute<Profile, PaymentTransaction> paymentTransactions;
	public static volatile SingularAttribute<Profile, Gender> gender;
	public static volatile SingularAttribute<Profile, byte[]> photo;
	public static volatile SingularAttribute<Profile, LocalDate> dateOfBirth;
	public static volatile SingularAttribute<Profile, String> phoneNumber;
	public static volatile SingularAttribute<Profile, Integer> pin;
	public static volatile SetAttribute<Profile, BillerTransaction> billerTransactions;
	public static volatile SingularAttribute<Profile, ProfileType> profileType;
	public static volatile SingularAttribute<Profile, Kyclevel> kyc;
	public static volatile SingularAttribute<Profile, String> profileID;
	public static volatile SetAttribute<Profile, Customersubscription> customersubscriptions;
	public static volatile SingularAttribute<Profile, Long> id;
	public static volatile SingularAttribute<Profile, String> bvn;
	public static volatile SingularAttribute<Profile, String> photoContentType;
	public static volatile SingularAttribute<Profile, User> user;
	public static volatile SingularAttribute<Profile, String> validID;

	public static final String WALLET_ACCOUNTS = "walletAccounts";
	public static final String ADDRESS = "address";
	public static final String PAYMENT_TRANSACTIONS = "paymentTransactions";
	public static final String GENDER = "gender";
	public static final String PHOTO = "photo";
	public static final String DATE_OF_BIRTH = "dateOfBirth";
	public static final String PHONE_NUMBER = "phoneNumber";
	public static final String PIN = "pin";
	public static final String BILLER_TRANSACTIONS = "billerTransactions";
	public static final String PROFILE_TYPE = "profileType";
	public static final String KYC = "kyc";
	public static final String PROFILE_ID = "profileID";
	public static final String CUSTOMERSUBSCRIPTIONS = "customersubscriptions";
	public static final String ID = "id";
	public static final String BVN = "bvn";
	public static final String PHOTO_CONTENT_TYPE = "photoContentType";
	public static final String USER = "user";
	public static final String VALID_ID = "validID";

}

