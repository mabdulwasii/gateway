package ng.com.systemspecs.apigateway.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import ng.com.systemspecs.apigateway.domain.enumeration.Frequency;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Customersubscription.class)
public abstract class Customersubscription_ {

	public static volatile SingularAttribute<Customersubscription, Biller> biller;
	public static volatile SingularAttribute<Customersubscription, Profile> phoneNumber;
	public static volatile SingularAttribute<Customersubscription, Long> id;
	public static volatile SingularAttribute<Customersubscription, String> uniqueID;
	public static volatile SingularAttribute<Customersubscription, Frequency> frequency;

	public static final String BILLER = "biller";
	public static final String PHONE_NUMBER = "phoneNumber";
	public static final String ID = "id";
	public static final String UNIQUE_ID = "uniqueID";
	public static final String FREQUENCY = "frequency";

}

