package ng.com.systemspecs.apigateway.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(BillerTransaction.class)
public abstract class BillerTransaction_ {

	public static volatile SingularAttribute<BillerTransaction, Double> amount;
	public static volatile SingularAttribute<BillerTransaction, Profile> phoneNumber;
	public static volatile SingularAttribute<BillerTransaction, Long> billertransID;
	public static volatile SingularAttribute<BillerTransaction, String> narration;
	public static volatile SingularAttribute<BillerTransaction, String> transactionRef;
	public static volatile SingularAttribute<BillerTransaction, Long> id;

	public static final String AMOUNT = "amount";
	public static final String PHONE_NUMBER = "phoneNumber";
	public static final String BILLERTRANS_ID = "billertransID";
	public static final String NARRATION = "narration";
	public static final String TRANSACTION_REF = "transactionRef";
	public static final String ID = "id";

}

