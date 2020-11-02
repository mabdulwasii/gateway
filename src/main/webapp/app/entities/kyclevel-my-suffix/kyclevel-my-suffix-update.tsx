import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './kyclevel-my-suffix.reducer';
import { IKyclevelMySuffix } from 'app/shared/model/kyclevel-my-suffix.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IKyclevelMySuffixUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const KyclevelMySuffixUpdate = (props: IKyclevelMySuffixUpdateProps) => {
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { kyclevelEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/kyclevel-my-suffix');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...kyclevelEntity,
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
          <h2 id="apiGatewayApp.kyclevel.home.createOrEditLabel">
            <Translate contentKey="apiGatewayApp.kyclevel.home.createOrEditLabel">Create or edit a Kyclevel</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : kyclevelEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="kyclevel-my-suffix-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="kyclevel-my-suffix-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="kycIDLabel" for="kyclevel-my-suffix-kycID">
                  <Translate contentKey="apiGatewayApp.kyclevel.kycID">Kyc ID</Translate>
                </Label>
                <AvField id="kyclevel-my-suffix-kycID" type="string" className="form-control" name="kycID" />
              </AvGroup>
              <AvGroup>
                <Label id="kycLabel" for="kyclevel-my-suffix-kyc">
                  <Translate contentKey="apiGatewayApp.kyclevel.kyc">Kyc</Translate>
                </Label>
                <AvField id="kyclevel-my-suffix-kyc" type="text" name="kyc" />
              </AvGroup>
              <AvGroup>
                <Label id="descriptionLabel" for="kyclevel-my-suffix-description">
                  <Translate contentKey="apiGatewayApp.kyclevel.description">Description</Translate>
                </Label>
                <AvField id="kyclevel-my-suffix-description" type="text" name="description" />
              </AvGroup>
              <AvGroup>
                <Label id="kycLevelLabel" for="kyclevel-my-suffix-kycLevel">
                  <Translate contentKey="apiGatewayApp.kyclevel.kycLevel">Kyc Level</Translate>
                </Label>
                <AvField id="kyclevel-my-suffix-kycLevel" type="string" className="form-control" name="kycLevel" />
              </AvGroup>
              <AvGroup check>
                <Label id="phoneNumberLabel">
                  <AvInput id="kyclevel-my-suffix-phoneNumber" type="checkbox" className="form-check-input" name="phoneNumber" />
                  <Translate contentKey="apiGatewayApp.kyclevel.phoneNumber">Phone Number</Translate>
                </Label>
              </AvGroup>
              <AvGroup check>
                <Label id="emailAddressLabel">
                  <AvInput id="kyclevel-my-suffix-emailAddress" type="checkbox" className="form-check-input" name="emailAddress" />
                  <Translate contentKey="apiGatewayApp.kyclevel.emailAddress">Email Address</Translate>
                </Label>
              </AvGroup>
              <AvGroup check>
                <Label id="firstNameLabel">
                  <AvInput id="kyclevel-my-suffix-firstName" type="checkbox" className="form-check-input" name="firstName" />
                  <Translate contentKey="apiGatewayApp.kyclevel.firstName">First Name</Translate>
                </Label>
              </AvGroup>
              <AvGroup check>
                <Label id="lastNameLabel">
                  <AvInput id="kyclevel-my-suffix-lastName" type="checkbox" className="form-check-input" name="lastName" />
                  <Translate contentKey="apiGatewayApp.kyclevel.lastName">Last Name</Translate>
                </Label>
              </AvGroup>
              <AvGroup check>
                <Label id="genderLabel">
                  <AvInput id="kyclevel-my-suffix-gender" type="checkbox" className="form-check-input" name="gender" />
                  <Translate contentKey="apiGatewayApp.kyclevel.gender">Gender</Translate>
                </Label>
              </AvGroup>
              <AvGroup check>
                <Label id="dateofBirthLabel">
                  <AvInput id="kyclevel-my-suffix-dateofBirth" type="checkbox" className="form-check-input" name="dateofBirth" />
                  <Translate contentKey="apiGatewayApp.kyclevel.dateofBirth">Dateof Birth</Translate>
                </Label>
              </AvGroup>
              <AvGroup check>
                <Label id="addressLabel">
                  <AvInput id="kyclevel-my-suffix-address" type="checkbox" className="form-check-input" name="address" />
                  <Translate contentKey="apiGatewayApp.kyclevel.address">Address</Translate>
                </Label>
              </AvGroup>
              <AvGroup check>
                <Label id="photoUploadLabel">
                  <AvInput id="kyclevel-my-suffix-photoUpload" type="checkbox" className="form-check-input" name="photoUpload" />
                  <Translate contentKey="apiGatewayApp.kyclevel.photoUpload">Photo Upload</Translate>
                </Label>
              </AvGroup>
              <AvGroup check>
                <Label id="verifiedBVNLabel">
                  <AvInput id="kyclevel-my-suffix-verifiedBVN" type="checkbox" className="form-check-input" name="verifiedBVN" />
                  <Translate contentKey="apiGatewayApp.kyclevel.verifiedBVN">Verified BVN</Translate>
                </Label>
              </AvGroup>
              <AvGroup check>
                <Label id="verifiedValidIDLabel">
                  <AvInput id="kyclevel-my-suffix-verifiedValidID" type="checkbox" className="form-check-input" name="verifiedValidID" />
                  <Translate contentKey="apiGatewayApp.kyclevel.verifiedValidID">Verified Valid ID</Translate>
                </Label>
              </AvGroup>
              <AvGroup check>
                <Label id="evidenceofAddressLabel">
                  <AvInput
                    id="kyclevel-my-suffix-evidenceofAddress"
                    type="checkbox"
                    className="form-check-input"
                    name="evidenceofAddress"
                  />
                  <Translate contentKey="apiGatewayApp.kyclevel.evidenceofAddress">Evidenceof Address</Translate>
                </Label>
              </AvGroup>
              <AvGroup check>
                <Label id="verificationofAddressLabel">
                  <AvInput
                    id="kyclevel-my-suffix-verificationofAddress"
                    type="checkbox"
                    className="form-check-input"
                    name="verificationofAddress"
                  />
                  <Translate contentKey="apiGatewayApp.kyclevel.verificationofAddress">Verificationof Address</Translate>
                </Label>
              </AvGroup>
              <AvGroup check>
                <Label id="employmentDetailsLabel">
                  <AvInput
                    id="kyclevel-my-suffix-employmentDetails"
                    type="checkbox"
                    className="form-check-input"
                    name="employmentDetails"
                  />
                  <Translate contentKey="apiGatewayApp.kyclevel.employmentDetails">Employment Details</Translate>
                </Label>
              </AvGroup>
              <AvGroup>
                <Label id="dailyTransactionLimitLabel" for="kyclevel-my-suffix-dailyTransactionLimit">
                  <Translate contentKey="apiGatewayApp.kyclevel.dailyTransactionLimit">Daily Transaction Limit</Translate>
                </Label>
                <AvField
                  id="kyclevel-my-suffix-dailyTransactionLimit"
                  type="string"
                  className="form-control"
                  name="dailyTransactionLimit"
                />
              </AvGroup>
              <AvGroup>
                <Label id="cumulativeBalanceLimitLabel" for="kyclevel-my-suffix-cumulativeBalanceLimit">
                  <Translate contentKey="apiGatewayApp.kyclevel.cumulativeBalanceLimit">Cumulative Balance Limit</Translate>
                </Label>
                <AvField
                  id="kyclevel-my-suffix-cumulativeBalanceLimit"
                  type="string"
                  className="form-control"
                  name="cumulativeBalanceLimit"
                />
              </AvGroup>
              <AvGroup check>
                <Label id="paymentTransactionLabel">
                  <AvInput
                    id="kyclevel-my-suffix-paymentTransaction"
                    type="checkbox"
                    className="form-check-input"
                    name="paymentTransaction"
                  />
                  <Translate contentKey="apiGatewayApp.kyclevel.paymentTransaction">Payment Transaction</Translate>
                </Label>
              </AvGroup>
              <AvGroup check>
                <Label id="billerTransactionLabel">
                  <AvInput
                    id="kyclevel-my-suffix-billerTransaction"
                    type="checkbox"
                    className="form-check-input"
                    name="billerTransaction"
                  />
                  <Translate contentKey="apiGatewayApp.kyclevel.billerTransaction">Biller Transaction</Translate>
                </Label>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/kyclevel-my-suffix" replace color="info">
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
  kyclevelEntity: storeState.kyclevel.entity,
  loading: storeState.kyclevel.loading,
  updating: storeState.kyclevel.updating,
  updateSuccess: storeState.kyclevel.updateSuccess,
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(KyclevelMySuffixUpdate);
