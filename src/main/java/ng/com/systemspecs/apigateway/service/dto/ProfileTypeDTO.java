package ng.com.systemspecs.apigateway.service.dto;

import io.swagger.annotations.ApiModel;
import java.io.Serializable;

/**
 * A DTO for the {@link ng.com.systemspecs.apigateway.domain.ProfileType} entity.
 */
@ApiModel(description = "This defines different Profile Type - Customer, Agent or Admin")
public class ProfileTypeDTO implements Serializable {
    
    private Long id;

    private Long profiletypeID;

    private String profiletype;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProfiletypeID() {
        return profiletypeID;
    }

    public void setProfiletypeID(Long profiletypeID) {
        this.profiletypeID = profiletypeID;
    }

    public String getProfiletype() {
        return profiletype;
    }

    public void setProfiletype(String profiletype) {
        this.profiletype = profiletype;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProfileTypeDTO)) {
            return false;
        }

        return id != null && id.equals(((ProfileTypeDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProfileTypeDTO{" +
            "id=" + getId() +
            ", profiletypeID=" + getProfiletypeID() +
            ", profiletype='" + getProfiletype() + "'" +
            "}";
    }
}
