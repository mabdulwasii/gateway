import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './biller-transaction-my-suffix.reducer';
import { IBillerTransactionMySuffix } from 'app/shared/model/biller-transaction-my-suffix.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IBillerTransactionMySuffixDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const BillerTransactionMySuffixDetail = (props: IBillerTransactionMySuffixDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { billerTransactionEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="apiGatewayApp.billerTransaction.detail.title">BillerTransaction</Translate> [
          <b>{billerTransactionEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="billertransID">
              <Translate contentKey="apiGatewayApp.billerTransaction.billertransID">Billertrans ID</Translate>
            </span>
          </dt>
          <dd>{billerTransactionEntity.billertransID}</dd>
          <dt>
            <span id="transactionRef">
              <Translate contentKey="apiGatewayApp.billerTransaction.transactionRef">Transaction Ref</Translate>
            </span>
          </dt>
          <dd>{billerTransactionEntity.transactionRef}</dd>
          <dt>
            <span id="narration">
              <Translate contentKey="apiGatewayApp.billerTransaction.narration">Narration</Translate>
            </span>
          </dt>
          <dd>{billerTransactionEntity.narration}</dd>
          <dt>
            <span id="amount">
              <Translate contentKey="apiGatewayApp.billerTransaction.amount">Amount</Translate>
            </span>
          </dt>
          <dd>{billerTransactionEntity.amount}</dd>
          <dt>
            <Translate contentKey="apiGatewayApp.billerTransaction.phoneNumber">Phone Number</Translate>
          </dt>
          <dd>{billerTransactionEntity.phoneNumberId ? billerTransactionEntity.phoneNumberId : ''}</dd>
        </dl>
        <Button tag={Link} to="/biller-transaction-my-suffix" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/biller-transaction-my-suffix/${billerTransactionEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ billerTransaction }: IRootState) => ({
  billerTransactionEntity: billerTransaction.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(BillerTransactionMySuffixDetail);
