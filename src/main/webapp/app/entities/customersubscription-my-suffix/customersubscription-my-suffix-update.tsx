import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IProfileMySuffix } from 'app/shared/model/profile-my-suffix.model';
import { getEntities as getProfiles } from 'app/entities/profile-my-suffix/profile-my-suffix.reducer';
import { IBillerMySuffix } from 'app/shared/model/biller-my-suffix.model';
import { getEntities as getBillers } from 'app/entities/biller-my-suffix/biller-my-suffix.reducer';
import { getEntity, updateEntity, createEntity, reset } from './customersubscription-my-suffix.reducer';
import { ICustomersubscriptionMySuffix } from 'app/shared/model/customersubscription-my-suffix.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ICustomersubscriptionMySuffixUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const CustomersubscriptionMySuffixUpdate = (props: ICustomersubscriptionMySuffixUpdateProps) => {
  const [phoneNumberId, setPhoneNumberId] = useState('0');
  const [billerId, setBillerId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { customersubscriptionEntity, profiles, billers, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/customersubscription-my-suffix');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getProfiles();
    props.getBillers();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...customersubscriptionEntity,
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
          <h2 id="apiGatewayApp.customersubscription.home.createOrEditLabel">
            <Translate contentKey="apiGatewayApp.customersubscription.home.createOrEditLabel">
              Create or edit a Customersubscription
            </Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : customersubscriptionEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="customersubscription-my-suffix-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="customersubscription-my-suffix-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="uniqueIDLabel" for="customersubscription-my-suffix-uniqueID">
                  <Translate contentKey="apiGatewayApp.customersubscription.uniqueID">Unique ID</Translate>
                </Label>
                <AvField id="customersubscription-my-suffix-uniqueID" type="text" name="uniqueID" />
              </AvGroup>
              <AvGroup>
                <Label id="frequencyLabel" for="customersubscription-my-suffix-frequency">
                  <Translate contentKey="apiGatewayApp.customersubscription.frequency">Frequency</Translate>
                </Label>
                <AvInput
                  id="customersubscription-my-suffix-frequency"
                  type="select"
                  className="form-control"
                  name="frequency"
                  value={(!isNew && customersubscriptionEntity.frequency) || 'DAILY'}
                >
                  <option value="DAILY">{translate('apiGatewayApp.Frequency.DAILY')}</option>
                  <option value="WEEKLY">{translate('apiGatewayApp.Frequency.WEEKLY')}</option>
                  <option value="MONTHLY">{translate('apiGatewayApp.Frequency.MONTHLY')}</option>
                  <option value="ANNUALLY">{translate('apiGatewayApp.Frequency.ANNUALLY')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="customersubscription-my-suffix-phoneNumber">
                  <Translate contentKey="apiGatewayApp.customersubscription.phoneNumber">Phone Number</Translate>
                </Label>
                <AvInput id="customersubscription-my-suffix-phoneNumber" type="select" className="form-control" name="phoneNumberId">
                  <option value="" key="0" />
                  {profiles
                    ? profiles.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="customersubscription-my-suffix-biller">
                  <Translate contentKey="apiGatewayApp.customersubscription.biller">Biller</Translate>
                </Label>
                <AvInput id="customersubscription-my-suffix-biller" type="select" className="form-control" name="billerId">
                  <option value="" key="0" />
                  {billers
                    ? billers.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/customersubscription-my-suffix" replace color="info">
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
  profiles: storeState.profile.entities,
  billers: storeState.biller.entities,
  customersubscriptionEntity: storeState.customersubscription.entity,
  loading: storeState.customersubscription.loading,
  updating: storeState.customersubscription.updating,
  updateSuccess: storeState.customersubscription.updateSuccess,
});

const mapDispatchToProps = {
  getProfiles,
  getBillers,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(CustomersubscriptionMySuffixUpdate);
