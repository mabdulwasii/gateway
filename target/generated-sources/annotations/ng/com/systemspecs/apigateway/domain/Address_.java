package ng.com.systemspecs.apigateway.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Address.class)
public abstract class Address_ {

	public static volatile SingularAttribute<Address, String> address;
	public static volatile SingularAttribute<Address, String> localGovt;
	public static volatile SingularAttribute<Address, Double> latitude;
	public static volatile SingularAttribute<Address, Long> id;
	public static volatile SingularAttribute<Address, String> state;
	public static volatile SingularAttribute<Address, Double> longitude;
	public static volatile SingularAttribute<Address, Profile> addressOwner;

	public static final String ADDRESS = "address";
	public static final String LOCAL_GOVT = "localGovt";
	public static final String LATITUDE = "latitude";
	public static final String ID = "id";
	public static final String STATE = "state";
	public static final String LONGITUDE = "longitude";
	public static final String ADDRESS_OWNER = "addressOwner";

}

