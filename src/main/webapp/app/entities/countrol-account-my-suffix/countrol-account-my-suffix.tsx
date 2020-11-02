import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './countrol-account-my-suffix.reducer';
import { ICountrolAccountMySuffix } from 'app/shared/model/countrol-account-my-suffix.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ICountrolAccountMySuffixProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const CountrolAccountMySuffix = (props: ICountrolAccountMySuffixProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { countrolAccountList, match, loading } = props;
  return (
    <div>
      <h2 id="countrol-account-my-suffix-heading">
        <Translate contentKey="apiGatewayApp.countrolAccount.home.title">Countrol Accounts</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="apiGatewayApp.countrolAccount.home.createLabel">Create new Countrol Account</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {countrolAccountList && countrolAccountList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="apiGatewayApp.countrolAccount.countrolAccountCode">Countrol Account Code</Translate>
                </th>
                <th>
                  <Translate contentKey="apiGatewayApp.countrolAccount.countrolAccountName">Countrol Account Name</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {countrolAccountList.map((countrolAccount, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${countrolAccount.id}`} color="link" size="sm">
                      {countrolAccount.id}
                    </Button>
                  </td>
                  <td>{countrolAccount.countrolAccountCode}</td>
                  <td>{countrolAccount.countrolAccountName}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${countrolAccount.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${countrolAccount.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${countrolAccount.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="apiGatewayApp.countrolAccount.home.notFound">No Countrol Accounts found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ countrolAccount }: IRootState) => ({
  countrolAccountList: countrolAccount.entities,
  loading: countrolAccount.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(CountrolAccountMySuffix);
