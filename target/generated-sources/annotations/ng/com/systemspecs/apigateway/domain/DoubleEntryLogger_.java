package ng.com.systemspecs.apigateway.domain;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(DoubleEntryLogger.class)
public abstract class DoubleEntryLogger_ {

	public static volatile SingularAttribute<DoubleEntryLogger, LocalDate> dateEntered;
	public static volatile SingularAttribute<DoubleEntryLogger, Double> amount;
	public static volatile SingularAttribute<DoubleEntryLogger, String> narration;
	public static volatile SingularAttribute<DoubleEntryLogger, Long> id;
	public static volatile SingularAttribute<DoubleEntryLogger, CountrolAccount> debit;
	public static volatile SingularAttribute<DoubleEntryLogger, CountrolAccount> credit;
	public static volatile SingularAttribute<DoubleEntryLogger, String> doubleEntryCode;

	public static final String DATE_ENTERED = "dateEntered";
	public static final String AMOUNT = "amount";
	public static final String NARRATION = "narration";
	public static final String ID = "id";
	public static final String DEBIT = "debit";
	public static final String CREDIT = "credit";
	public static final String DOUBLE_ENTRY_CODE = "doubleEntryCode";

}

