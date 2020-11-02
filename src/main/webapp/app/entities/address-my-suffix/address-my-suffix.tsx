import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './address-my-suffix.reducer';
import { IAddressMySuffix } from 'app/shared/model/address-my-suffix.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IAddressMySuffixProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const AddressMySuffix = (props: IAddressMySuffixProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { addressList, match, loading } = props;
  return (
    <div>
      <h2 id="address-my-suffix-heading">
        <Translate contentKey="apiGatewayApp.address.home.title">Addresses</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="apiGatewayApp.address.home.createLabel">Create new Address</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {addressList && addressList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="apiGatewayApp.address.state">State</Translate>
                </th>
                <th>
                  <Translate contentKey="apiGatewayApp.address.localGovt">Local Govt</Translate>
                </th>
                <th>
                  <Translate contentKey="apiGatewayApp.address.latitude">Latitude</Translate>
                </th>
                <th>
                  <Translate contentKey="apiGatewayApp.address.longitude">Longitude</Translate>
                </th>
                <th>
                  <Translate contentKey="apiGatewayApp.address.address">Address</Translate>
                </th>
                <th>
                  <Translate contentKey="apiGatewayApp.address.addressOwner">Address Owner</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {addressList.map((address, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${address.id}`} color="link" size="sm">
                      {address.id}
                    </Button>
                  </td>
                  <td>{address.state}</td>
                  <td>{address.localGovt}</td>
                  <td>{address.latitude}</td>
                  <td>{address.longitude}</td>
                  <td>{address.address}</td>
                  <td>
                    {address.addressOwnerPhoneNumber ? (
                      <Link to={`profile-my-suffix/${address.addressOwnerId}`}>{address.addressOwnerPhoneNumber}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${address.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${address.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${address.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="apiGatewayApp.address.home.notFound">No Addresses found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ address }: IRootState) => ({
  addressList: address.entities,
  loading: address.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(AddressMySuffix);
