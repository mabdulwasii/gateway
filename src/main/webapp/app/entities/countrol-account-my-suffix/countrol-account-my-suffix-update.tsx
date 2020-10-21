import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './countrol-account-my-suffix.reducer';
import { ICountrolAccountMySuffix } from 'app/shared/model/countrol-account-my-suffix.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ICountrolAccountMySuffixUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const CountrolAccountMySuffixUpdate = (props: ICountrolAccountMySuffixUpdateProps) => {
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { countrolAccountEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/countrol-account-my-suffix');
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
        ...countrolAccountEntity,
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
          <h2 id="apiGatewayApp.countrolAccount.home.createOrEditLabel">
            <Translate contentKey="apiGatewayApp.countrolAccount.home.createOrEditLabel">Create or edit a CountrolAccount</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : countrolAccountEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="countrol-account-my-suffix-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="countrol-account-my-suffix-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="countrolAccountCodeLabel" for="countrol-account-my-suffix-countrolAccountCode">
                  <Translate contentKey="apiGatewayApp.countrolAccount.countrolAccountCode">Countrol Account Code</Translate>
                </Label>
                <AvField id="countrol-account-my-suffix-countrolAccountCode" type="text" name="countrolAccountCode" />
              </AvGroup>
              <AvGroup>
                <Label id="countrolAccountNameLabel" for="countrol-account-my-suffix-countrolAccountName">
                  <Translate contentKey="apiGatewayApp.countrolAccount.countrolAccountName">Countrol Account Name</Translate>
                </Label>
                <AvField id="countrol-account-my-suffix-countrolAccountName" type="text" name="countrolAccountName" />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/countrol-account-my-suffix" replace color="info">
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
  countrolAccountEntity: storeState.countrolAccount.entity,
  loading: storeState.countrolAccount.loading,
  updating: storeState.countrolAccount.updating,
  updateSuccess: storeState.countrolAccount.updateSuccess,
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(CountrolAccountMySuffixUpdate);
