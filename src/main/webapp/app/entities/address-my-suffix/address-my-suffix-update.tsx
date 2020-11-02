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
import { getEntity, updateEntity, createEntity, reset } from './address-my-suffix.reducer';
import { IAddressMySuffix } from 'app/shared/model/address-my-suffix.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IAddressMySuffixUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const AddressMySuffixUpdate = (props: IAddressMySuffixUpdateProps) => {
  const [addressOwnerId, setAddressOwnerId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { addressEntity, profiles, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/address-my-suffix');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getProfiles();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...addressEntity,
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
          <h2 id="apiGatewayApp.address.home.createOrEditLabel">
            <Translate contentKey="apiGatewayApp.address.home.createOrEditLabel">Create or edit a Address</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : addressEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="address-my-suffix-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="address-my-suffix-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="stateLabel" for="address-my-suffix-state">
                  <Translate contentKey="apiGatewayApp.address.state">State</Translate>
                </Label>
                <AvField id="address-my-suffix-state" type="text" name="state" />
              </AvGroup>
              <AvGroup>
                <Label id="localGovtLabel" for="address-my-suffix-localGovt">
                  <Translate contentKey="apiGatewayApp.address.localGovt">Local Govt</Translate>
                </Label>
                <AvField id="address-my-suffix-localGovt" type="text" name="localGovt" />
              </AvGroup>
              <AvGroup>
                <Label id="latitudeLabel" for="address-my-suffix-latitude">
                  <Translate contentKey="apiGatewayApp.address.latitude">Latitude</Translate>
                </Label>
                <AvField id="address-my-suffix-latitude" type="string" className="form-control" name="latitude" />
              </AvGroup>
              <AvGroup>
                <Label id="longitudeLabel" for="address-my-suffix-longitude">
                  <Translate contentKey="apiGatewayApp.address.longitude">Longitude</Translate>
                </Label>
                <AvField id="address-my-suffix-longitude" type="string" className="form-control" name="longitude" />
              </AvGroup>
              <AvGroup>
                <Label id="addressLabel" for="address-my-suffix-address">
                  <Translate contentKey="apiGatewayApp.address.address">Address</Translate>
                </Label>
                <AvField id="address-my-suffix-address" type="text" name="address" />
              </AvGroup>
              <AvGroup>
                <Label for="address-my-suffix-addressOwner">
                  <Translate contentKey="apiGatewayApp.address.addressOwner">Address Owner</Translate>
                </Label>
                <AvInput id="address-my-suffix-addressOwner" type="select" className="form-control" name="addressOwnerId">
                  <option value="" key="0" />
                  {profiles
                    ? profiles.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.phoneNumber}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/address-my-suffix" replace color="info">
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
  addressEntity: storeState.address.entity,
  loading: storeState.address.loading,
  updating: storeState.address.updating,
  updateSuccess: storeState.address.updateSuccess,
});

const mapDispatchToProps = {
  getProfiles,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(AddressMySuffixUpdate);
