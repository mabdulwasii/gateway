import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './customersubscription-my-suffix.reducer';
import { ICustomersubscriptionMySuffix } from 'app/shared/model/customersubscription-my-suffix.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ICustomersubscriptionMySuffixDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const CustomersubscriptionMySuffixDetail = (props: ICustomersubscriptionMySuffixDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { customersubscriptionEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="apiGatewayApp.customersubscription.detail.title">Customersubscription</Translate> [
          <b>{customersubscriptionEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="uniqueID">
              <Translate contentKey="apiGatewayApp.customersubscription.uniqueID">Unique ID</Translate>
            </span>
          </dt>
          <dd>{customersubscriptionEntity.uniqueID}</dd>
          <dt>
            <span id="frequency">
              <Translate contentKey="apiGatewayApp.customersubscription.frequency">Frequency</Translate>
            </span>
          </dt>
          <dd>{customersubscriptionEntity.frequency}</dd>
          <dt>
            <Translate contentKey="apiGatewayApp.customersubscription.phoneNumber">Phone Number</Translate>
          </dt>
          <dd>{customersubscriptionEntity.phoneNumberId ? customersubscriptionEntity.phoneNumberId : ''}</dd>
          <dt>
            <Translate contentKey="apiGatewayApp.customersubscription.biller">Biller</Translate>
          </dt>
          <dd>{customersubscriptionEntity.billerId ? customersubscriptionEntity.billerId : ''}</dd>
        </dl>
        <Button tag={Link} to="/customersubscription-my-suffix" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/customersubscription-my-suffix/${customersubscriptionEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ customersubscription }: IRootState) => ({
  customersubscriptionEntity: customersubscription.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(CustomersubscriptionMySuffixDetail);
