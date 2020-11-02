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
import { getEntity, updateEntity, createEntity, reset } from './payment-transaction-my-suffix.reducer';
import { IPaymentTransactionMySuffix } from 'app/shared/model/payment-transaction-my-suffix.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IPaymentTransactionMySuffixUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const PaymentTransactionMySuffixUpdate = (props: IPaymentTransactionMySuffixUpdateProps) => {
  const [transactionOwnerId, setTransactionOwnerId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { paymentTransactionEntity, profiles, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/payment-transaction-my-suffix' + props.location.search);
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
        ...paymentTransactionEntity,
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
          <h2 id="apiGatewayApp.paymentTransaction.home.createOrEditLabel">
            <Translate contentKey="apiGatewayApp.paymentTransaction.home.createOrEditLabel">Create or edit a PaymentTransaction</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : paymentTransactionEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="payment-transaction-my-suffix-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="payment-transaction-my-suffix-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="paymenttransIDLabel" for="payment-transaction-my-suffix-paymenttransID">
                  <Translate contentKey="apiGatewayApp.paymentTransaction.paymenttransID">Paymenttrans ID</Translate>
                </Label>
                <AvField id="payment-transaction-my-suffix-paymenttransID" type="string" className="form-control" name="paymenttransID" />
              </AvGroup>
              <AvGroup>
                <Label id="transactionTypeLabel" for="payment-transaction-my-suffix-transactionType">
                  <Translate contentKey="apiGatewayApp.paymentTransaction.transactionType">Transaction Type</Translate>
                </Label>
                <AvInput
                  id="payment-transaction-my-suffix-transactionType"
                  type="select"
                  className="form-control"
                  name="transactionType"
                  value={(!isNew && paymentTransactionEntity.transactionType) || 'BANK_ACCOUNT_TRANSFER'}
                >
                  <option value="BANK_ACCOUNT_TRANSFER">{translate('apiGatewayApp.TransactionType.BANK_ACCOUNT_TRANSFER')}</option>
                  <option value="WALLET_TO_WALLET_TRANSFER">{translate('apiGatewayApp.TransactionType.WALLET_TO_WALLET_TRANSFER')}</option>
                  <option value="REQUEST_FUND">{translate('apiGatewayApp.TransactionType.REQUEST_FUND')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label id="transactionRefLabel" for="payment-transaction-my-suffix-transactionRef">
                  <Translate contentKey="apiGatewayApp.paymentTransaction.transactionRef">Transaction Ref</Translate>
                </Label>
                <AvField id="payment-transaction-my-suffix-transactionRef" type="text" name="transactionRef" />
              </AvGroup>
              <AvGroup>
                <Label id="amountLabel" for="payment-transaction-my-suffix-amount">
                  <Translate contentKey="apiGatewayApp.paymentTransaction.amount">Amount</Translate>
                </Label>
                <AvField
                  id="payment-transaction-my-suffix-amount"
                  type="text"
                  name="amount"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                    number: { value: true, errorMessage: translate('entity.validation.number') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="channelLabel" for="payment-transaction-my-suffix-channel">
                  <Translate contentKey="apiGatewayApp.paymentTransaction.channel">Channel</Translate>
                </Label>
                <AvField
                  id="payment-transaction-my-suffix-channel"
                  type="text"
                  name="channel"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="currencyLabel" for="payment-transaction-my-suffix-currency">
                  <Translate contentKey="apiGatewayApp.paymentTransaction.currency">Currency</Translate>
                </Label>
                <AvField
                  id="payment-transaction-my-suffix-currency"
                  type="text"
                  name="currency"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="sourceAccountLabel" for="payment-transaction-my-suffix-sourceAccount">
                  <Translate contentKey="apiGatewayApp.paymentTransaction.sourceAccount">Source Account</Translate>
                </Label>
                <AvField
                  id="payment-transaction-my-suffix-sourceAccount"
                  type="text"
                  name="sourceAccount"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="sourceAccountBankCodeLabel" for="payment-transaction-my-suffix-sourceAccountBankCode">
                  <Translate contentKey="apiGatewayApp.paymentTransaction.sourceAccountBankCode">Source Account Bank Code</Translate>
                </Label>
                <AvField
                  id="payment-transaction-my-suffix-sourceAccountBankCode"
                  type="text"
                  name="sourceAccountBankCode"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="sourceAccountNameLabel" for="payment-transaction-my-suffix-sourceAccountName">
                  <Translate contentKey="apiGatewayApp.paymentTransaction.sourceAccountName">Source Account Name</Translate>
                </Label>
                <AvField id="payment-transaction-my-suffix-sourceAccountName" type="text" name="sourceAccountName" />
              </AvGroup>
              <AvGroup>
                <Label id="sourceNarrationLabel" for="payment-transaction-my-suffix-sourceNarration">
                  <Translate contentKey="apiGatewayApp.paymentTransaction.sourceNarration">Source Narration</Translate>
                </Label>
                <AvField
                  id="payment-transaction-my-suffix-sourceNarration"
                  type="text"
                  name="sourceNarration"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="destinationAccountLabel" for="payment-transaction-my-suffix-destinationAccount">
                  <Translate contentKey="apiGatewayApp.paymentTransaction.destinationAccount">Destination Account</Translate>
                </Label>
                <AvField
                  id="payment-transaction-my-suffix-destinationAccount"
                  type="text"
                  name="destinationAccount"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="destinationAccountBankCodeLabel" for="payment-transaction-my-suffix-destinationAccountBankCode">
                  <Translate contentKey="apiGatewayApp.paymentTransaction.destinationAccountBankCode">
                    Destination Account Bank Code
                  </Translate>
                </Label>
                <AvField
                  id="payment-transaction-my-suffix-destinationAccountBankCode"
                  type="text"
                  name="destinationAccountBankCode"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="destinationAccountNameLabel" for="payment-transaction-my-suffix-destinationAccountName">
                  <Translate contentKey="apiGatewayApp.paymentTransaction.destinationAccountName">Destination Account Name</Translate>
                </Label>
                <AvField id="payment-transaction-my-suffix-destinationAccountName" type="text" name="destinationAccountName" />
              </AvGroup>
              <AvGroup>
                <Label id="destinationNarrationLabel" for="payment-transaction-my-suffix-destinationNarration">
                  <Translate contentKey="apiGatewayApp.paymentTransaction.destinationNarration">Destination Narration</Translate>
                </Label>
                <AvField
                  id="payment-transaction-my-suffix-destinationNarration"
                  type="text"
                  name="destinationNarration"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label for="payment-transaction-my-suffix-transactionOwner">
                  <Translate contentKey="apiGatewayApp.paymentTransaction.transactionOwner">Transaction Owner</Translate>
                </Label>
                <AvInput
                  id="payment-transaction-my-suffix-transactionOwner"
                  type="select"
                  className="form-control"
                  name="transactionOwnerId"
                >
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
              <Button tag={Link} id="cancel-save" to="/payment-transaction-my-suffix" replace color="info">
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
  paymentTransactionEntity: storeState.paymentTransaction.entity,
  loading: storeState.paymentTransaction.loading,
  updating: storeState.paymentTransaction.updating,
  updateSuccess: storeState.paymentTransaction.updateSuccess,
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

export default connect(mapStateToProps, mapDispatchToProps)(PaymentTransactionMySuffixUpdate);
