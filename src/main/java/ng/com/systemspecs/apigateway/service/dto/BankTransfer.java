package ng.com.systemspecs.apigateway.service.dto;

import java.util.Date;

public class BankTransfer  {

    private int amount;
    private String channel;
    private String currency;
    private String destinationaccount;
    private String destinationbankcode;

    private String destinationbankname;

    private String destinationaccountname;

    private String destinationnarration;

    private Date enddate;

    private String referenceid;
    private String sourceaccount;
    private String sourceaccountname;
    private String sourceamount;
    private String sourcecurrency;

    private String sourcenarration;

    private Date startdate;

    private Date transactiondate;

    private String statuswebhook;
    public void setAmount(int amount) {
         this.amount = amount;
     }
     public int getAmount() {
         return amount;
     }

    public void setChannel(String channel) {
         this.channel = channel;
     }
     public String getChannel() {
         return channel;
     }

    public void setCurrency(String currency) {
         this.currency = currency;
     }
     public String getCurrency() {
         return currency;
     }

    public void setDestinationaccount(String destinationaccount) {
         this.destinationaccount = destinationaccount;
     }
     public String getDestinationaccount() {
         return destinationaccount;
     }

    public void setDestinationbankcode(String destinationbankcode) {
         this.destinationbankcode = destinationbankcode;
     }
     public String getDestinationbankcode() {
         return destinationbankcode;
     }

    public void setDestinationbankname(String destinationbankname) {
         this.destinationbankname = destinationbankname;
     }
     public String getDestinationbankname() {
         return destinationbankname;
     }

    public void setDestinationaccountname(String destinationaccountname) {
         this.destinationaccountname = destinationaccountname;
     }
     public String getDestinationaccountname() {
         return destinationaccountname;
     }

    public void setDestinationnarration(String destinationnarration) {
         this.destinationnarration = destinationnarration;
     }
     public String getDestinationnarration() {
         return destinationnarration;
     }

    public void setEnddate(Date enddate) {
         this.enddate = enddate;
     }
     public Date getEnddate() {
         return enddate;
     }

    public void setReferenceid(String referenceid) {
         this.referenceid = referenceid;
     }
     public String getReferenceid() {
         return referenceid;
     }

    public void setSourceaccount(String sourceaccount) {
         this.sourceaccount = sourceaccount;
     }
     public String getSourceaccount() {
         return sourceaccount;
     }

    public void setSourceaccountname(String sourceaccountname) {
         this.sourceaccountname = sourceaccountname;
     }
     public String getSourceaccountname() {
         return sourceaccountname;
     }

    public void setSourceamount(String sourceamount) {
         this.sourceamount = sourceamount;
     }
     public String getSourceamount() {
         return sourceamount;
     }

    public void setSourcecurrency(String sourcecurrency) {
         this.sourcecurrency = sourcecurrency;
     }
     public String getSourcecurrency() {
         return sourcecurrency;
     }

    public void setSourcenarration(String sourcenarration) {
         this.sourcenarration = sourcenarration;
     }
     public String getSourcenarration() {
         return sourcenarration;
     }

    public void setStartdate(Date startdate) {
         this.startdate = startdate;
     }
     public Date getStartdate() {
         return startdate;
     }

    public void setTransactiondate(Date transactiondate) {
         this.transactiondate = transactiondate;
     }
     public Date getTransactiondate() {
         return transactiondate;
     }

    public void setStatuswebhook(String statuswebhook) {
         this.statuswebhook = statuswebhook;
     }
     public String getStatuswebhook() {
         return statuswebhook;
     }

}