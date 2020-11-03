package ng.com.systemspecs.apigateway.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(BillerCategory.class)
public abstract class BillerCategory_ {

	public static volatile SingularAttribute<BillerCategory, String> billerCategory;
	public static volatile SingularAttribute<BillerCategory, Long> id;
	public static volatile SetAttribute<BillerCategory, Biller> billers;
	public static volatile SingularAttribute<BillerCategory, Long> billercategoryID;

	public static final String BILLER_CATEGORY = "billerCategory";
	public static final String ID = "id";
	public static final String BILLERS = "billers";
	public static final String BILLERCATEGORY_ID = "billercategoryID";

}

