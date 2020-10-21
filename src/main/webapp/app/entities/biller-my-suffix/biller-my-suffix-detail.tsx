import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './biller-my-suffix.reducer';
import { IBillerMySuffix } from 'app/shared/model/biller-my-suffix.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IBillerMySuffixDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const BillerMySuffixDetail = (props: IBillerMySuffixDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { billerEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="apiGatewayApp.biller.detail.title">Biller</Translate> [<b>{billerEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="billerID">
              <Translate contentKey="apiGatewayApp.biller.billerID">Biller ID</Translate>
            </span>
          </dt>
          <dd>{billerEntity.billerID}</dd>
          <dt>
            <span id="biller">
              <Translate contentKey="apiGatewayApp.biller.biller">Biller</Translate>
            </span>
          </dt>
          <dd>{billerEntity.biller}</dd>
          <dt>
            <span id="address">
              <Translate contentKey="apiGatewayApp.biller.address">Address</Translate>
            </span>
          </dt>
          <dd>{billerEntity.address}</dd>
          <dt>
            <span id="phoneNumber">
              <Translate contentKey="apiGatewayApp.biller.phoneNumber">Phone Number</Translate>
            </span>
          </dt>
          <dd>{billerEntity.phoneNumber}</dd>
          <dt>
            <Translate contentKey="apiGatewayApp.biller.billerCategory">Biller Category</Translate>
          </dt>
          <dd>{billerEntity.billerCategoryId ? billerEntity.billerCategoryId : ''}</dd>
        </dl>
        <Button tag={Link} to="/biller-my-suffix" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/biller-my-suffix/${billerEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ biller }: IRootState) => ({
  billerEntity: biller.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(BillerMySuffixDetail);
