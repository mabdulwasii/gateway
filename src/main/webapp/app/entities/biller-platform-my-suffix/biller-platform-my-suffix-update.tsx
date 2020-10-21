import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IBillerMySuffix } from 'app/shared/model/biller-my-suffix.model';
import { getEntities as getBillers } from 'app/entities/biller-my-suffix/biller-my-suffix.reducer';
import { getEntity, updateEntity, createEntity, reset } from './biller-platform-my-suffix.reducer';
import { IBillerPlatformMySuffix } from 'app/shared/model/biller-platform-my-suffix.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IBillerPlatformMySuffixUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const BillerPlatformMySuffixUpdate = (props: IBillerPlatformMySuffixUpdateProps) => {
  const [billerId, setBillerId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { billerPlatformEntity, billers, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/biller-platform-my-suffix');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

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
        ...billerPlatformEntity,
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
          <h2 id="apiGatewayApp.billerPlatform.home.createOrEditLabel">
            <Translate contentKey="apiGatewayApp.billerPlatform.home.createOrEditLabel">Create or edit a BillerPlatform</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : billerPlatformEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="biller-platform-my-suffix-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="biller-platform-my-suffix-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="billerplatformIDLabel" for="biller-platform-my-suffix-billerplatformID">
                  <Translate contentKey="apiGatewayApp.billerPlatform.billerplatformID">Billerplatform ID</Translate>
                </Label>
                <AvField id="biller-platform-my-suffix-billerplatformID" type="string" className="form-control" name="billerplatformID" />
              </AvGroup>
              <AvGroup>
                <Label id="billerPlatformLabel" for="biller-platform-my-suffix-billerPlatform">
                  <Translate contentKey="apiGatewayApp.billerPlatform.billerPlatform">Biller Platform</Translate>
                </Label>
                <AvField id="biller-platform-my-suffix-billerPlatform" type="text" name="billerPlatform" />
              </AvGroup>
              <AvGroup>
                <Label id="amountLabel" for="biller-platform-my-suffix-amount">
                  <Translate contentKey="apiGatewayApp.billerPlatform.amount">Amount</Translate>
                </Label>
                <AvField id="biller-platform-my-suffix-amount" type="string" className="form-control" name="amount" />
              </AvGroup>
              <AvGroup>
                <Label for="biller-platform-my-suffix-biller">
                  <Translate contentKey="apiGatewayApp.billerPlatform.biller">Biller</Translate>
                </Label>
                <AvInput id="biller-platform-my-suffix-biller" type="select" className="form-control" name="billerId">
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
              <Button tag={Link} id="cancel-save" to="/biller-platform-my-suffix" replace color="info">
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
  billers: storeState.biller.entities,
  billerPlatformEntity: storeState.billerPlatform.entity,
  loading: storeState.billerPlatform.loading,
  updating: storeState.billerPlatform.updating,
  updateSuccess: storeState.billerPlatform.updateSuccess,
});

const mapDispatchToProps = {
  getBillers,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(BillerPlatformMySuffixUpdate);
