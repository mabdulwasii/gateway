import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './kyclevel-my-suffix.reducer';
import { IKyclevelMySuffix } from 'app/shared/model/kyclevel-my-suffix.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IKyclevelMySuffixProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const KyclevelMySuffix = (props: IKyclevelMySuffixProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { kyclevelList, match, loading } = props;
  return (
    <div>
      <h2 id="kyclevel-my-suffix-heading">
        <Translate contentKey="apiGatewayApp.kyclevel.home.title">Kyclevels</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="apiGatewayApp.kyclevel.home.createLabel">Create new Kyclevel</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {kyclevelList && kyclevelList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="apiGatewayApp.kyclevel.kycID">Kyc ID</Translate>
                </th>
                <th>
                  <Translate contentKey="apiGatewayApp.kyclevel.kyc">Kyc</Translate>
                </th>
                <th>
                  <Translate contentKey="apiGatewayApp.kyclevel.description">Description</Translate>
                </th>
                <th>
                  <Translate contentKey="apiGatewayApp.kyclevel.kycLevel">Kyc Level</Translate>
                </th>
                <th>
                  <Translate contentKey="apiGatewayApp.kyclevel.phoneNumber">Phone Number</Translate>
                </th>
                <th>
                  <Translate contentKey="apiGatewayApp.kyclevel.emailAddress">Email Address</Translate>
                </th>
                <th>
                  <Translate contentKey="apiGatewayApp.kyclevel.firstName">First Name</Translate>
                </th>
                <th>
                  <Translate contentKey="apiGatewayApp.kyclevel.lastName">Last Name</Translate>
                </th>
                <th>
                  <Translate contentKey="apiGatewayApp.kyclevel.gender">Gender</Translate>
                </th>
                <th>
                  <Translate contentKey="apiGatewayApp.kyclevel.dateofBirth">Dateof Birth</Translate>
                </th>
                <th>
                  <Translate contentKey="apiGatewayApp.kyclevel.address">Address</Translate>
                </th>
                <th>
                  <Translate contentKey="apiGatewayApp.kyclevel.photoUpload">Photo Upload</Translate>
                </th>
                <th>
                  <Translate contentKey="apiGatewayApp.kyclevel.verifiedBVN">Verified BVN</Translate>
                </th>
                <th>
                  <Translate contentKey="apiGatewayApp.kyclevel.verifiedValidID">Verified Valid ID</Translate>
                </th>
                <th>
                  <Translate contentKey="apiGatewayApp.kyclevel.evidenceofAddress">Evidenceof Address</Translate>
                </th>
                <th>
                  <Translate contentKey="apiGatewayApp.kyclevel.verificationofAddress">Verificationof Address</Translate>
                </th>
                <th>
                  <Translate contentKey="apiGatewayApp.kyclevel.employmentDetails">Employment Details</Translate>
                </th>
                <th>
                  <Translate contentKey="apiGatewayApp.kyclevel.dailyTransactionLimit">Daily Transaction Limit</Translate>
                </th>
                <th>
                  <Translate contentKey="apiGatewayApp.kyclevel.cumulativeBalanceLimit">Cumulative Balance Limit</Translate>
                </th>
                <th>
                  <Translate contentKey="apiGatewayApp.kyclevel.paymentTransaction">Payment Transaction</Translate>
                </th>
                <th>
                  <Translate contentKey="apiGatewayApp.kyclevel.billerTransaction">Biller Transaction</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {kyclevelList.map((kyclevel, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${kyclevel.id}`} color="link" size="sm">
                      {kyclevel.id}
                    </Button>
                  </td>
                  <td>{kyclevel.kycID}</td>
                  <td>{kyclevel.kyc}</td>
                  <td>{kyclevel.description}</td>
                  <td>{kyclevel.kycLevel}</td>
                  <td>{kyclevel.phoneNumber ? 'true' : 'false'}</td>
                  <td>{kyclevel.emailAddress ? 'true' : 'false'}</td>
                  <td>{kyclevel.firstName ? 'true' : 'false'}</td>
                  <td>{kyclevel.lastName ? 'true' : 'false'}</td>
                  <td>{kyclevel.gender ? 'true' : 'false'}</td>
                  <td>{kyclevel.dateofBirth ? 'true' : 'false'}</td>
                  <td>{kyclevel.address ? 'true' : 'false'}</td>
                  <td>{kyclevel.photoUpload ? 'true' : 'false'}</td>
                  <td>{kyclevel.verifiedBVN ? 'true' : 'false'}</td>
                  <td>{kyclevel.verifiedValidID ? 'true' : 'false'}</td>
                  <td>{kyclevel.evidenceofAddress ? 'true' : 'false'}</td>
                  <td>{kyclevel.verificationofAddress ? 'true' : 'false'}</td>
                  <td>{kyclevel.employmentDetails ? 'true' : 'false'}</td>
                  <td>{kyclevel.dailyTransactionLimit}</td>
                  <td>{kyclevel.cumulativeBalanceLimit}</td>
                  <td>{kyclevel.paymentTransaction ? 'true' : 'false'}</td>
                  <td>{kyclevel.billerTransaction ? 'true' : 'false'}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${kyclevel.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${kyclevel.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${kyclevel.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="apiGatewayApp.kyclevel.home.notFound">No Kyclevels found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ kyclevel }: IRootState) => ({
  kyclevelList: kyclevel.entities,
  loading: kyclevel.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(KyclevelMySuffix);
