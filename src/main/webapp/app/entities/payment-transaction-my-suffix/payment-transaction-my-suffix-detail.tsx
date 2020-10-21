import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './payment-transaction-my-suffix.reducer';
import { IPaymentTransactionMySuffix } from 'app/shared/model/payment-transaction-my-suffix.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IPaymentTransactionMySuffixDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const PaymentTransactionMySuffixDetail = (props: IPaymentTransactionMySuffixDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { paymentTransactionEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="apiGatewayApp.paymentTransaction.detail.title">PaymentTransaction</Translate> [
          <b>{paymentTransactionEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="paymenttransID">
              <Translate contentKey="apiGatewayApp.paymentTransaction.paymenttransID">Paymenttrans ID</Translate>
            </span>
          </dt>
          <dd>{paymentTransactionEntity.paymenttransID}</dd>
          <dt>
            <span id="transactionType">
              <Translate contentKey="apiGatewayApp.paymentTransaction.transactionType">Transaction Type</Translate>
            </span>
          </dt>
          <dd>{paymentTransactionEntity.transactionType}</dd>
          <dt>
            <span id="transactionRef">
              <Translate contentKey="apiGatewayApp.paymentTransaction.transactionRef">Transaction Ref</Translate>
            </span>
          </dt>
          <dd>{paymentTransactionEntity.transactionRef}</dd>
          <dt>
            <span id="amount">
              <Translate contentKey="apiGatewayApp.paymentTransaction.amount">Amount</Translate>
            </span>
          </dt>
          <dd>{paymentTransactionEntity.amount}</dd>
          <dt>
            <span id="channel">
              <Translate contentKey="apiGatewayApp.paymentTransaction.channel">Channel</Translate>
            </span>
          </dt>
          <dd>{paymentTransactionEntity.channel}</dd>
          <dt>
            <span id="currency">
              <Translate contentKey="apiGatewayApp.paymentTransaction.currency">Currency</Translate>
            </span>
          </dt>
          <dd>{paymentTransactionEntity.currency}</dd>
          <dt>
            <span id="sourceAccount">
              <Translate contentKey="apiGatewayApp.paymentTransaction.sourceAccount">Source Account</Translate>
            </span>
          </dt>
          <dd>{paymentTransactionEntity.sourceAccount}</dd>
          <dt>
            <span id="sourceAccountBankCode">
              <Translate contentKey="apiGatewayApp.paymentTransaction.sourceAccountBankCode">Source Account Bank Code</Translate>
            </span>
          </dt>
          <dd>{paymentTransactionEntity.sourceAccountBankCode}</dd>
          <dt>
            <span id="sourceAccountName">
              <Translate contentKey="apiGatewayApp.paymentTransaction.sourceAccountName">Source Account Name</Translate>
            </span>
          </dt>
          <dd>{paymentTransactionEntity.sourceAccountName}</dd>
          <dt>
            <span id="sourceNarration">
              <Translate contentKey="apiGatewayApp.paymentTransaction.sourceNarration">Source Narration</Translate>
            </span>
          </dt>
          <dd>{paymentTransactionEntity.sourceNarration}</dd>
          <dt>
            <span id="destinationAccount">
              <Translate contentKey="apiGatewayApp.paymentTransaction.destinationAccount">Destination Account</Translate>
            </span>
          </dt>
          <dd>{paymentTransactionEntity.destinationAccount}</dd>
          <dt>
            <span id="destinationAccountBankCode">
              <Translate contentKey="apiGatewayApp.paymentTransaction.destinationAccountBankCode">Destination Account Bank Code</Translate>
            </span>
          </dt>
          <dd>{paymentTransactionEntity.destinationAccountBankCode}</dd>
          <dt>
            <span id="destinationAccountName">
              <Translate contentKey="apiGatewayApp.paymentTransaction.destinationAccountName">Destination Account Name</Translate>
            </span>
          </dt>
          <dd>{paymentTransactionEntity.destinationAccountName}</dd>
          <dt>
            <span id="destinationNarration">
              <Translate contentKey="apiGatewayApp.paymentTransaction.destinationNarration">Destination Narration</Translate>
            </span>
          </dt>
          <dd>{paymentTransactionEntity.destinationNarration}</dd>
          <dt>
            <Translate contentKey="apiGatewayApp.paymentTransaction.transactionOwner">Transaction Owner</Translate>
          </dt>
          <dd>{paymentTransactionEntity.transactionOwnerPhoneNumber ? paymentTransactionEntity.transactionOwnerPhoneNumber : ''}</dd>
        </dl>
        <Button tag={Link} to="/payment-transaction-my-suffix" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/payment-transaction-my-suffix/${paymentTransactionEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ paymentTransaction }: IRootState) => ({
  paymentTransactionEntity: paymentTransaction.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(PaymentTransactionMySuffixDetail);
