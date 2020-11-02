import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './double-entry-logger-my-suffix.reducer';
import { IDoubleEntryLoggerMySuffix } from 'app/shared/model/double-entry-logger-my-suffix.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IDoubleEntryLoggerMySuffixProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const DoubleEntryLoggerMySuffix = (props: IDoubleEntryLoggerMySuffixProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { doubleEntryLoggerList, match, loading } = props;
  return (
    <div>
      <h2 id="double-entry-logger-my-suffix-heading">
        <Translate contentKey="apiGatewayApp.doubleEntryLogger.home.title">Double Entry Loggers</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="apiGatewayApp.doubleEntryLogger.home.createLabel">Create new Double Entry Logger</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {doubleEntryLoggerList && doubleEntryLoggerList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="apiGatewayApp.doubleEntryLogger.dateEntered">Date Entered</Translate>
                </th>
                <th>
                  <Translate contentKey="apiGatewayApp.doubleEntryLogger.doubleEntryCode">Double Entry Code</Translate>
                </th>
                <th>
                  <Translate contentKey="apiGatewayApp.doubleEntryLogger.amount">Amount</Translate>
                </th>
                <th>
                  <Translate contentKey="apiGatewayApp.doubleEntryLogger.narration">Narration</Translate>
                </th>
                <th>
                  <Translate contentKey="apiGatewayApp.doubleEntryLogger.debit">Debit</Translate>
                </th>
                <th>
                  <Translate contentKey="apiGatewayApp.doubleEntryLogger.credit">Credit</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {doubleEntryLoggerList.map((doubleEntryLogger, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${doubleEntryLogger.id}`} color="link" size="sm">
                      {doubleEntryLogger.id}
                    </Button>
                  </td>
                  <td>
                    {doubleEntryLogger.dateEntered ? (
                      <TextFormat type="date" value={doubleEntryLogger.dateEntered} format={APP_LOCAL_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{doubleEntryLogger.doubleEntryCode}</td>
                  <td>{doubleEntryLogger.amount}</td>
                  <td>{doubleEntryLogger.narration}</td>
                  <td>
                    {doubleEntryLogger.debitCountrolAccountCode ? (
                      <Link to={`countrol-account-my-suffix/${doubleEntryLogger.debitId}`}>
                        {doubleEntryLogger.debitCountrolAccountCode}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {doubleEntryLogger.creditCountrolAccountCode ? (
                      <Link to={`countrol-account-my-suffix/${doubleEntryLogger.creditId}`}>
                        {doubleEntryLogger.creditCountrolAccountCode}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${doubleEntryLogger.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${doubleEntryLogger.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${doubleEntryLogger.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="apiGatewayApp.doubleEntryLogger.home.notFound">No Double Entry Loggers found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ doubleEntryLogger }: IRootState) => ({
  doubleEntryLoggerList: doubleEntryLogger.entities,
  loading: doubleEntryLogger.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DoubleEntryLoggerMySuffix);
