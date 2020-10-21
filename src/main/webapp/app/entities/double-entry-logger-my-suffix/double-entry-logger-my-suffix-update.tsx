import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { ICountrolAccountMySuffix } from 'app/shared/model/countrol-account-my-suffix.model';
import { getEntities as getCountrolAccounts } from 'app/entities/countrol-account-my-suffix/countrol-account-my-suffix.reducer';
import { getEntity, updateEntity, createEntity, reset } from './double-entry-logger-my-suffix.reducer';
import { IDoubleEntryLoggerMySuffix } from 'app/shared/model/double-entry-logger-my-suffix.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IDoubleEntryLoggerMySuffixUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const DoubleEntryLoggerMySuffixUpdate = (props: IDoubleEntryLoggerMySuffixUpdateProps) => {
  const [debitId, setDebitId] = useState('0');
  const [creditId, setCreditId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { doubleEntryLoggerEntity, countrolAccounts, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/double-entry-logger-my-suffix');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getCountrolAccounts();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...doubleEntryLoggerEntity,
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
          <h2 id="apiGatewayApp.doubleEntryLogger.home.createOrEditLabel">
            <Translate contentKey="apiGatewayApp.doubleEntryLogger.home.createOrEditLabel">Create or edit a DoubleEntryLogger</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : doubleEntryLoggerEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="double-entry-logger-my-suffix-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="double-entry-logger-my-suffix-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="dateEnteredLabel" for="double-entry-logger-my-suffix-dateEntered">
                  <Translate contentKey="apiGatewayApp.doubleEntryLogger.dateEntered">Date Entered</Translate>
                </Label>
                <AvField id="double-entry-logger-my-suffix-dateEntered" type="date" className="form-control" name="dateEntered" />
              </AvGroup>
              <AvGroup>
                <Label id="doubleEntryCodeLabel" for="double-entry-logger-my-suffix-doubleEntryCode">
                  <Translate contentKey="apiGatewayApp.doubleEntryLogger.doubleEntryCode">Double Entry Code</Translate>
                </Label>
                <AvField id="double-entry-logger-my-suffix-doubleEntryCode" type="text" name="doubleEntryCode" />
              </AvGroup>
              <AvGroup>
                <Label id="amountLabel" for="double-entry-logger-my-suffix-amount">
                  <Translate contentKey="apiGatewayApp.doubleEntryLogger.amount">Amount</Translate>
                </Label>
                <AvField id="double-entry-logger-my-suffix-amount" type="string" className="form-control" name="amount" />
              </AvGroup>
              <AvGroup>
                <Label id="narrationLabel" for="double-entry-logger-my-suffix-narration">
                  <Translate contentKey="apiGatewayApp.doubleEntryLogger.narration">Narration</Translate>
                </Label>
                <AvField id="double-entry-logger-my-suffix-narration" type="text" name="narration" />
              </AvGroup>
              <AvGroup>
                <Label for="double-entry-logger-my-suffix-debit">
                  <Translate contentKey="apiGatewayApp.doubleEntryLogger.debit">Debit</Translate>
                </Label>
                <AvInput id="double-entry-logger-my-suffix-debit" type="select" className="form-control" name="debitId">
                  <option value="" key="0" />
                  {countrolAccounts
                    ? countrolAccounts.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.countrolAccountCode}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="double-entry-logger-my-suffix-credit">
                  <Translate contentKey="apiGatewayApp.doubleEntryLogger.credit">Credit</Translate>
                </Label>
                <AvInput id="double-entry-logger-my-suffix-credit" type="select" className="form-control" name="creditId">
                  <option value="" key="0" />
                  {countrolAccounts
                    ? countrolAccounts.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.countrolAccountCode}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/double-entry-logger-my-suffix" replace color="info">
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
  countrolAccounts: storeState.countrolAccount.entities,
  doubleEntryLoggerEntity: storeState.doubleEntryLogger.entity,
  loading: storeState.doubleEntryLogger.loading,
  updating: storeState.doubleEntryLogger.updating,
  updateSuccess: storeState.doubleEntryLogger.updateSuccess,
});

const mapDispatchToProps = {
  getCountrolAccounts,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DoubleEntryLoggerMySuffixUpdate);
