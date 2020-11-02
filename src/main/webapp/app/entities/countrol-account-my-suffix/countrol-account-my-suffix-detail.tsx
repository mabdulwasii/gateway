import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './countrol-account-my-suffix.reducer';
import { ICountrolAccountMySuffix } from 'app/shared/model/countrol-account-my-suffix.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ICountrolAccountMySuffixDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const CountrolAccountMySuffixDetail = (props: ICountrolAccountMySuffixDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { countrolAccountEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="apiGatewayApp.countrolAccount.detail.title">CountrolAccount</Translate> [<b>{countrolAccountEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="countrolAccountCode">
              <Translate contentKey="apiGatewayApp.countrolAccount.countrolAccountCode">Countrol Account Code</Translate>
            </span>
          </dt>
          <dd>{countrolAccountEntity.countrolAccountCode}</dd>
          <dt>
            <span id="countrolAccountName">
              <Translate contentKey="apiGatewayApp.countrolAccount.countrolAccountName">Countrol Account Name</Translate>
            </span>
          </dt>
          <dd>{countrolAccountEntity.countrolAccountName}</dd>
        </dl>
        <Button tag={Link} to="/countrol-account-my-suffix" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/countrol-account-my-suffix/${countrolAccountEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ countrolAccount }: IRootState) => ({
  countrolAccountEntity: countrolAccount.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(CountrolAccountMySuffixDetail);
