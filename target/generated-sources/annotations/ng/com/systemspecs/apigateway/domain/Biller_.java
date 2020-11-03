package ng.com.systemspecs.apigateway.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Biller.class)
public abstract class Biller_ {

	public static volatile SingularAttribute<Biller, BillerCategory> billerCategory;
	public static volatile SingularAttribute<Biller, Long> billerID;
	public static volatile SingularAttribute<Biller, String> biller;
	public static volatile SingularAttribute<Biller, String> address;
	public static volatile SingularAttribute<Biller, String> phoneNumber;
	public static volatile SetAttribute<Biller, BillerPlatform> billerPlatforms;
	public static volatile SetAttribute<Biller, Customersubscription> customersubscriptions;
	public static volatile SingularAttribute<Biller, Long> id;

	public static final String BILLER_CATEGORY = "billerCategory";
	public static final String BILLER_ID = "billerID";
	public static final String BILLER = "biller";
	public static final String ADDRESS = "address";
	public static final String PHONE_NUMBER = "phoneNumber";
	public static final String BILLER_PLATFORMS = "billerPlatforms";
	public static final String CUSTOMERSUBSCRIPTIONS = "customersubscriptions";
	public static final String ID = "id";

}

