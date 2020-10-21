import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './address-my-suffix.reducer';
import { IAddressMySuffix } from 'app/shared/model/address-my-suffix.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IAddressMySuffixDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const AddressMySuffixDetail = (props: IAddressMySuffixDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { addressEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="apiGatewayApp.address.detail.title">Address</Translate> [<b>{addressEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="state">
              <Translate contentKey="apiGatewayApp.address.state">State</Translate>
            </span>
          </dt>
          <dd>{addressEntity.state}</dd>
          <dt>
            <span id="localGovt">
              <Translate contentKey="apiGatewayApp.address.localGovt">Local Govt</Translate>
            </span>
          </dt>
          <dd>{addressEntity.localGovt}</dd>
          <dt>
            <span id="latitude">
              <Translate contentKey="apiGatewayApp.address.latitude">Latitude</Translate>
            </span>
          </dt>
          <dd>{addressEntity.latitude}</dd>
          <dt>
            <span id="longitude">
              <Translate contentKey="apiGatewayApp.address.longitude">Longitude</Translate>
            </span>
          </dt>
          <dd>{addressEntity.longitude}</dd>
          <dt>
            <span id="address">
              <Translate contentKey="apiGatewayApp.address.address">Address</Translate>
            </span>
          </dt>
          <dd>{addressEntity.address}</dd>
          <dt>
            <Translate contentKey="apiGatewayApp.address.addressOwner">Address Owner</Translate>
          </dt>
          <dd>{addressEntity.addressOwnerPhoneNumber ? addressEntity.addressOwnerPhoneNumber : ''}</dd>
        </dl>
        <Button tag={Link} to="/address-my-suffix" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/address-my-suffix/${addressEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ address }: IRootState) => ({
  addressEntity: address.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(AddressMySuffixDetail);
