import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './biller-platform-my-suffix.reducer';
import { IBillerPlatformMySuffix } from 'app/shared/model/biller-platform-my-suffix.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IBillerPlatformMySuffixDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const BillerPlatformMySuffixDetail = (props: IBillerPlatformMySuffixDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { billerPlatformEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="apiGatewayApp.billerPlatform.detail.title">BillerPlatform</Translate> [<b>{billerPlatformEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="billerplatformID">
              <Translate contentKey="apiGatewayApp.billerPlatform.billerplatformID">Billerplatform ID</Translate>
            </span>
          </dt>
          <dd>{billerPlatformEntity.billerplatformID}</dd>
          <dt>
            <span id="billerPlatform">
              <Translate contentKey="apiGatewayApp.billerPlatform.billerPlatform">Biller Platform</Translate>
            </span>
          </dt>
          <dd>{billerPlatformEntity.billerPlatform}</dd>
          <dt>
            <span id="amount">
              <Translate contentKey="apiGatewayApp.billerPlatform.amount">Amount</Translate>
            </span>
          </dt>
          <dd>{billerPlatformEntity.amount}</dd>
          <dt>
            <Translate contentKey="apiGatewayApp.billerPlatform.biller">Biller</Translate>
          </dt>
          <dd>{billerPlatformEntity.billerId ? billerPlatformEntity.billerId : ''}</dd>
        </dl>
        <Button tag={Link} to="/biller-platform-my-suffix" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/biller-platform-my-suffix/${billerPlatformEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ billerPlatform }: IRootState) => ({
  billerPlatformEntity: billerPlatform.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(BillerPlatformMySuffixDetail);
