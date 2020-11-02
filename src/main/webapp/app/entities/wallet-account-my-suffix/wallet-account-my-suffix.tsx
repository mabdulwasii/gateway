import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './wallet-account-my-suffix.reducer';
import { IWalletAccountMySuffix } from 'app/shared/model/wallet-account-my-suffix.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IWalletAccountMySuffixProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const WalletAccountMySuffix = (props: IWalletAccountMySuffixProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { walletAccountList, match, loading } = props;
  return (
    <div>
      <h2 id="wallet-account-my-suffix-heading">
        <Translate contentKey="apiGatewayApp.walletAccount.home.title">Wallet Accounts</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="apiGatewayApp.walletAccount.home.createLabel">Create new Wallet Account</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {walletAccountList && walletAccountList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="apiGatewayApp.walletAccount.accountNumber">Account Number</Translate>
                </th>
                <th>
                  <Translate contentKey="apiGatewayApp.walletAccount.currentBalance">Current Balance</Translate>
                </th>
                <th>
                  <Translate contentKey="apiGatewayApp.walletAccount.dateOpened">Date Opened</Translate>
                </th>
                <th>
                  <Translate contentKey="apiGatewayApp.walletAccount.scheme">Scheme</Translate>
                </th>
                <th>
                  <Translate contentKey="apiGatewayApp.walletAccount.walletAccountType">Wallet Account Type</Translate>
                </th>
                <th>
                  <Translate contentKey="apiGatewayApp.walletAccount.accountOwner">Account Owner</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {walletAccountList.map((walletAccount, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${walletAccount.id}`} color="link" size="sm">
                      {walletAccount.id}
                    </Button>
                  </td>
                  <td>{walletAccount.accountNumber}</td>
                  <td>{walletAccount.currentBalance}</td>
                  <td>
                    {walletAccount.dateOpened ? (
                      <TextFormat type="date" value={walletAccount.dateOpened} format={APP_LOCAL_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>
                    {walletAccount.schemeId ? <Link to={`scheme-my-suffix/${walletAccount.schemeId}`}>{walletAccount.schemeId}</Link> : ''}
                  </td>
                  <td>
                    {walletAccount.walletAccountTypeId ? (
                      <Link to={`wallet-account-type-my-suffix/${walletAccount.walletAccountTypeId}`}>
                        {walletAccount.walletAccountTypeId}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {walletAccount.accountOwnerPhoneNumber ? (
                      <Link to={`profile-my-suffix/${walletAccount.accountOwnerId}`}>{walletAccount.accountOwnerPhoneNumber}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${walletAccount.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${walletAccount.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${walletAccount.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="apiGatewayApp.walletAccount.home.notFound">No Wallet Accounts found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ walletAccount }: IRootState) => ({
  walletAccountList: walletAccount.entities,
  loading: walletAccount.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(WalletAccountMySuffix);
