import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, setFileData, openFile, byteSize, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IUser } from 'app/shared/model/user.model';
import { getUsers } from 'app/modules/administration/user-management/user-management.reducer';
import { IProfileTypeMySuffix } from 'app/shared/model/profile-type-my-suffix.model';
import { getEntities as getProfileTypes } from 'app/entities/profile-type-my-suffix/profile-type-my-suffix.reducer';
import { IKyclevelMySuffix } from 'app/shared/model/kyclevel-my-suffix.model';
import { getEntities as getKyclevels } from 'app/entities/kyclevel-my-suffix/kyclevel-my-suffix.reducer';
import { getEntity, updateEntity, createEntity, setBlob, reset } from './profile-my-suffix.reducer';
import { IProfileMySuffix } from 'app/shared/model/profile-my-suffix.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IProfileMySuffixUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ProfileMySuffixUpdate = (props: IProfileMySuffixUpdateProps) => {
  const [userId, setUserId] = useState('0');
  const [profileTypeId, setProfileTypeId] = useState('0');
  const [kycId, setKycId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { profileEntity, users, profileTypes, kyclevels, loading, updating } = props;

  const { photo, photoContentType } = profileEntity;

  const handleClose = () => {
    props.history.push('/profile-my-suffix' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getUsers();
    props.getProfileTypes();
    props.getKyclevels();
  }, []);

  const onBlobChange = (isAnImage, name) => event => {
    setFileData(event, (contentType, data) => props.setBlob(name, data, contentType), isAnImage);
  };

  const clearBlob = name => () => {
    props.setBlob(name, undefined, undefined);
  };

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...profileEntity,
        ...values,
      };

      if (isNew) {
        props.createEntity(entity);
      } else {
        props.updateEntity(entity);
      }
    }
  };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="apiGatewayApp.profile.home.createOrEditLabel">
            <Translate contentKey="apiGatewayApp.profile.home.createOrEditLabel">Create or edit a Profile</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : profileEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="profile-my-suffix-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="profile-my-suffix-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="profileIDLabel" for="profile-my-suffix-profileID">
                  <Translate contentKey="apiGatewayApp.profile.profileID">Profile ID</Translate>
                </Label>
                <AvField id="profile-my-suffix-profileID" type="text" name="profileID" />
              </AvGroup>
              <AvGroup>
                <Label id="phoneNumberLabel" for="profile-my-suffix-phoneNumber">
                  <Translate contentKey="apiGatewayApp.profile.phoneNumber">Phone Number</Translate>
                </Label>
                <AvField id="profile-my-suffix-phoneNumber" type="text" name="phoneNumber" />
              </AvGroup>
              <AvGroup>
                <Label id="genderLabel" for="profile-my-suffix-gender">
                  <Translate contentKey="apiGatewayApp.profile.gender">Gender</Translate>
                </Label>
                <AvInput
                  id="profile-my-suffix-gender"
                  type="select"
                  className="form-control"
                  name="gender"
                  value={(!isNew && profileEntity.gender) || 'FEMALE'}
                >
                  <option value="FEMALE">{translate('apiGatewayApp.Gender.FEMALE')}</option>
                  <option value="MALE">{translate('apiGatewayApp.Gender.MALE')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label id="dateOfBirthLabel" for="profile-my-suffix-dateOfBirth">
                  <Translate contentKey="apiGatewayApp.profile.dateOfBirth">Date Of Birth</Translate>
                </Label>
                <AvField id="profile-my-suffix-dateOfBirth" type="date" className="form-control" name="dateOfBirth" />
              </AvGroup>
              <AvGroup>
                <Label id="addressLabel" for="profile-my-suffix-address">
                  <Translate contentKey="apiGatewayApp.profile.address">Address</Translate>
                </Label>
                <AvField id="profile-my-suffix-address" type="text" name="address" />
              </AvGroup>
              <AvGroup>
                <AvGroup>
                  <Label id="photoLabel" for="photo">
                    <Translate contentKey="apiGatewayApp.profile.photo">Photo</Translate>
                  </Label>
                  <br />
                  {photo ? (
                    <div>
                      {photoContentType ? (
                        <a onClick={openFile(photoContentType, photo)}>
                          <img src={`data:${photoContentType};base64,${photo}`} style={{ maxHeight: '100px' }} />
                        </a>
                      ) : null}
                      <br />
                      <Row>
                        <Col md="11">
                          <span>
                            {photoContentType}, {byteSize(photo)}
                          </span>
                        </Col>
                        <Col md="1">
                          <Button color="danger" onClick={clearBlob('photo')}>
                            <FontAwesomeIcon icon="times-circle" />
                          </Button>
                        </Col>
                      </Row>
                    </div>
                  ) : null}
                  <input id="file_photo" type="file" onChange={onBlobChange(true, 'photo')} accept="image/*" />
                  <AvInput type="hidden" name="photo" value={photo} />
                </AvGroup>
              </AvGroup>
              <AvGroup>
                <Label id="bvnLabel" for="profile-my-suffix-bvn">
                  <Translate contentKey="apiGatewayApp.profile.bvn">Bvn</Translate>
                </Label>
                <AvField id="profile-my-suffix-bvn" type="text" name="bvn" />
              </AvGroup>
              <AvGroup>
                <Label id="validIDLabel" for="profile-my-suffix-validID">
                  <Translate contentKey="apiGatewayApp.profile.validID">Valid ID</Translate>
                </Label>
                <AvField id="profile-my-suffix-validID" type="text" name="validID" />
              </AvGroup>
              <AvGroup>
                <Label for="profile-my-suffix-user">
                  <Translate contentKey="apiGatewayApp.profile.user">User</Translate>
                </Label>
                <AvInput id="profile-my-suffix-user" type="select" className="form-control" name="userId">
                  <option value="" key="0" />
                  {users
                    ? users.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.login}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="profile-my-suffix-profileType">
                  <Translate contentKey="apiGatewayApp.profile.profileType">Profile Type</Translate>
                </Label>
                <AvInput id="profile-my-suffix-profileType" type="select" className="form-control" name="profileTypeId">
                  <option value="" key="0" />
                  {profileTypes
                    ? profileTypes.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="profile-my-suffix-kyc">
                  <Translate contentKey="apiGatewayApp.profile.kyc">Kyc</Translate>
                </Label>
                <AvInput id="profile-my-suffix-kyc" type="select" className="form-control" name="kycId">
                  <option value="" key="0" />
                  {kyclevels
                    ? kyclevels.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/profile-my-suffix" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </AvForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

const mapStateToProps = (storeState: IRootState) => ({
  users: storeState.userManagement.users,
  profileTypes: storeState.profileType.entities,
  kyclevels: storeState.kyclevel.entities,
  profileEntity: storeState.profile.entity,
  loading: storeState.profile.loading,
  updating: storeState.profile.updating,
  updateSuccess: storeState.profile.updateSuccess,
});

const mapDispatchToProps = {
  getUsers,
  getProfileTypes,
  getKyclevels,
  getEntity,
  updateEntity,
  setBlob,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ProfileMySuffixUpdate);
