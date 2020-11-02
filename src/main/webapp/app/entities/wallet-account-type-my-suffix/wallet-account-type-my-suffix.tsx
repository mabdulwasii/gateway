import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './wallet-account-type-my-suffix.reducer';
import { IWalletAccountTypeMySuffix } from 'app/shared/model/wallet-account-type-my-suffix.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IWalletAccountTypeMySuffixProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const WalletAccountTypeMySuffix = (props: IWalletAccountTypeMySuffixProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { walletAccountTypeList, match, loading } = props;
  return (
    <div>
      <h2 id="wallet-account-type-my-suffix-heading">
        <Translate contentKey="apiGatewayApp.walletAccountType.home.title">Wallet Account Types</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="apiGatewayApp.walletAccountType.home.createLabel">Create new Wallet Account Type</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {walletAccountTypeList && walletAccountTypeList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="apiGatewayApp.walletAccountType.accountypeID">Accountype ID</Translate>
                </th>
                <th>
                  <Translate contentKey="apiGatewayApp.walletAccountType.walletAccountType">Wallet Account Type</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {walletAccountTypeList.map((walletAccountType, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${walletAccountType.id}`} color="link" size="sm">
                      {walletAccountType.id}
                    </Button>
                  </td>
                  <td>{walletAccountType.accountypeID}</td>
                  <td>{walletAccountType.walletAccountType}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${walletAccountType.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${walletAccountType.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${walletAccountType.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="apiGatewayApp.walletAccountType.home.notFound">No Wallet Account Types found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ walletAccountType }: IRootState) => ({
  walletAccountTypeList: walletAccountType.entities,
  loading: walletAccountType.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(WalletAccountTypeMySuffix);
