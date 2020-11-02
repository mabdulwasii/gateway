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
import { getEntity, updateEntity, createEntity, reset } from './biller-transaction-my-suffix.reducer';
import { IBillerTransactionMySuffix } from 'app/shared/model/biller-transaction-my-suffix.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IBillerTransactionMySuffixUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const BillerTransactionMySuffixUpdate = (props: IBillerTransactionMySuffixUpdateProps) => {
  const [phoneNumberId, setPhoneNumberId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { billerTransactionEntity, profiles, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/biller-transaction-my-suffix' + props.location.search);
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
        ...billerTransactionEntity,
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
          <h2 id="apiGatewayApp.billerTransaction.home.createOrEditLabel">
            <Translate contentKey="apiGatewayApp.billerTransaction.home.createOrEditLabel">Create or edit a BillerTransaction</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : billerTransactionEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="biller-transaction-my-suffix-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="biller-transaction-my-suffix-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="billertransIDLabel" for="biller-transaction-my-suffix-billertransID">
                  <Translate contentKey="apiGatewayApp.billerTransaction.billertransID">Billertrans ID</Translate>
                </Label>
                <AvField id="biller-transaction-my-suffix-billertransID" type="string" className="form-control" name="billertransID" />
              </AvGroup>
              <AvGroup>
                <Label id="transactionRefLabel" for="biller-transaction-my-suffix-transactionRef">
                  <Translate contentKey="apiGatewayApp.billerTransaction.transactionRef">Transaction Ref</Translate>
                </Label>
                <AvField id="biller-transaction-my-suffix-transactionRef" type="text" name="transactionRef" />
              </AvGroup>
              <AvGroup>
                <Label id="narrationLabel" for="biller-transaction-my-suffix-narration">
                  <Translate contentKey="apiGatewayApp.billerTransaction.narration">Narration</Translate>
                </Label>
                <AvField id="biller-transaction-my-suffix-narration" type="text" name="narration" />
              </AvGroup>
              <AvGroup>
                <Label id="amountLabel" for="biller-transaction-my-suffix-amount">
                  <Translate contentKey="apiGatewayApp.billerTransaction.amount">Amount</Translate>
                </Label>
                <AvField id="biller-transaction-my-suffix-amount" type="string" className="form-control" name="amount" />
              </AvGroup>
              <AvGroup>
                <Label for="biller-transaction-my-suffix-phoneNumber">
                  <Translate contentKey="apiGatewayApp.billerTransaction.phoneNumber">Phone Number</Translate>
                </Label>
                <AvInput id="biller-transaction-my-suffix-phoneNumber" type="select" className="form-control" name="phoneNumberId">
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
              <Button tag={Link} id="cancel-save" to="/biller-transaction-my-suffix" replace color="info">
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
  billerTransactionEntity: storeState.billerTransaction.entity,
  loading: storeState.billerTransaction.loading,
  updating: storeState.billerTransaction.updating,
  updateSuccess: storeState.billerTransaction.updateSuccess,
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

export default connect(mapStateToProps, mapDispatchToProps)(BillerTransactionMySuffixUpdate);
