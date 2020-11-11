package ng.com.systemspecs.apigateway.service.dto;

public class UpgradeKYCLevelDTO {

    private BvnDTO bvn ;
    private String verificationId;
    private String employmentDetails;

    public UpgradeKYCLevelDTO() {
    }

    public BvnDTO getBvn() {
        return bvn;
    }

    public void setBvn(BvnDTO bvn) {
        this.bvn = bvn;
    }

    public String getVerificationId() {
        return verificationId;
    }

    public void setVerificationId(String verificationId) {
        this.verificationId = verificationId;
    }

    public String getEmploymentDetails() {
        return employmentDetails;
    }

    public void setEmploymentDetails(String employmentDetails) {
        this.employmentDetails = employmentDetails;
    }
}
