package ng.com.systemspecs.apigateway.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(BillerPlatform.class)
public abstract class BillerPlatform_ {

	public static volatile SingularAttribute<BillerPlatform, Biller> biller;
	public static volatile SingularAttribute<BillerPlatform, Long> billerplatformID;
	public static volatile SingularAttribute<BillerPlatform, Double> amount;
	public static volatile SingularAttribute<BillerPlatform, Long> id;
	public static volatile SingularAttribute<BillerPlatform, String> billerPlatform;

	public static final String BILLER = "biller";
	public static final String BILLERPLATFORM_ID = "billerplatformID";
	public static final String AMOUNT = "amount";
	public static final String ID = "id";
	public static final String BILLER_PLATFORM = "billerPlatform";

}

