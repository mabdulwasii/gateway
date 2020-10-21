import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './double-entry-logger-my-suffix.reducer';
import { IDoubleEntryLoggerMySuffix } from 'app/shared/model/double-entry-logger-my-suffix.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IDoubleEntryLoggerMySuffixDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const DoubleEntryLoggerMySuffixDetail = (props: IDoubleEntryLoggerMySuffixDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { doubleEntryLoggerEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="apiGatewayApp.doubleEntryLogger.detail.title">DoubleEntryLogger</Translate> [
          <b>{doubleEntryLoggerEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="dateEntered">
              <Translate contentKey="apiGatewayApp.doubleEntryLogger.dateEntered">Date Entered</Translate>
            </span>
          </dt>
          <dd>
            {doubleEntryLoggerEntity.dateEntered ? (
              <TextFormat value={doubleEntryLoggerEntity.dateEntered} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="doubleEntryCode">
              <Translate contentKey="apiGatewayApp.doubleEntryLogger.doubleEntryCode">Double Entry Code</Translate>
            </span>
          </dt>
          <dd>{doubleEntryLoggerEntity.doubleEntryCode}</dd>
          <dt>
            <span id="amount">
              <Translate contentKey="apiGatewayApp.doubleEntryLogger.amount">Amount</Translate>
            </span>
          </dt>
          <dd>{doubleEntryLoggerEntity.amount}</dd>
          <dt>
            <span id="narration">
              <Translate contentKey="apiGatewayApp.doubleEntryLogger.narration">Narration</Translate>
            </span>
          </dt>
          <dd>{doubleEntryLoggerEntity.narration}</dd>
          <dt>
            <Translate contentKey="apiGatewayApp.doubleEntryLogger.debit">Debit</Translate>
          </dt>
          <dd>{doubleEntryLoggerEntity.debitCountrolAccountCode ? doubleEntryLoggerEntity.debitCountrolAccountCode : ''}</dd>
          <dt>
            <Translate contentKey="apiGatewayApp.doubleEntryLogger.credit">Credit</Translate>
          </dt>
          <dd>{doubleEntryLoggerEntity.creditCountrolAccountCode ? doubleEntryLoggerEntity.creditCountrolAccountCode : ''}</dd>
        </dl>
        <Button tag={Link} to="/double-entry-logger-my-suffix" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/double-entry-logger-my-suffix/${doubleEntryLoggerEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ doubleEntryLogger }: IRootState) => ({
  doubleEntryLoggerEntity: doubleEntryLogger.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DoubleEntryLoggerMySuffixDetail);
