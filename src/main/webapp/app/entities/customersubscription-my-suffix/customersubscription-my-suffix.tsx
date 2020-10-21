import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './customersubscription-my-suffix.reducer';
import { ICustomersubscriptionMySuffix } from 'app/shared/model/customersubscription-my-suffix.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ICustomersubscriptionMySuffixProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const CustomersubscriptionMySuffix = (props: ICustomersubscriptionMySuffixProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { customersubscriptionList, match, loading } = props;
  return (
    <div>
      <h2 id="customersubscription-my-suffix-heading">
        <Translate contentKey="apiGatewayApp.customersubscription.home.title">Customersubscriptions</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="apiGatewayApp.customersubscription.home.createLabel">Create new Customersubscription</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {customersubscriptionList && customersubscriptionList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="apiGatewayApp.customersubscription.uniqueID">Unique ID</Translate>
                </th>
                <th>
                  <Translate contentKey="apiGatewayApp.customersubscription.frequency">Frequency</Translate>
                </th>
                <th>
                  <Translate contentKey="apiGatewayApp.customersubscription.phoneNumber">Phone Number</Translate>
                </th>
                <th>
                  <Translate contentKey="apiGatewayApp.customersubscription.biller">Biller</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {customersubscriptionList.map((customersubscription, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${customersubscription.id}`} color="link" size="sm">
                      {customersubscription.id}
                    </Button>
                  </td>
                  <td>{customersubscription.uniqueID}</td>
                  <td>
                    <Translate contentKey={`apiGatewayApp.Frequency.${customersubscription.frequency}`} />
                  </td>
                  <td>
                    {customersubscription.phoneNumberId ? (
                      <Link to={`profile-my-suffix/${customersubscription.phoneNumberId}`}>{customersubscription.phoneNumberId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {customersubscription.billerId ? (
                      <Link to={`biller-my-suffix/${customersubscription.billerId}`}>{customersubscription.billerId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${customersubscription.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${customersubscription.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${customersubscription.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="apiGatewayApp.customersubscription.home.notFound">No Customersubscriptions found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ customersubscription }: IRootState) => ({
  customersubscriptionList: customersubscription.entities,
  loading: customersubscription.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(CustomersubscriptionMySuffix);
