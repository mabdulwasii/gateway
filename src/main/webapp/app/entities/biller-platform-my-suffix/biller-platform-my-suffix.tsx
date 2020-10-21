import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './biller-platform-my-suffix.reducer';
import { IBillerPlatformMySuffix } from 'app/shared/model/biller-platform-my-suffix.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IBillerPlatformMySuffixProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const BillerPlatformMySuffix = (props: IBillerPlatformMySuffixProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { billerPlatformList, match, loading } = props;
  return (
    <div>
      <h2 id="biller-platform-my-suffix-heading">
        <Translate contentKey="apiGatewayApp.billerPlatform.home.title">Biller Platforms</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="apiGatewayApp.billerPlatform.home.createLabel">Create new Biller Platform</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {billerPlatformList && billerPlatformList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="apiGatewayApp.billerPlatform.billerplatformID">Billerplatform ID</Translate>
                </th>
                <th>
                  <Translate contentKey="apiGatewayApp.billerPlatform.billerPlatform">Biller Platform</Translate>
                </th>
                <th>
                  <Translate contentKey="apiGatewayApp.billerPlatform.amount">Amount</Translate>
                </th>
                <th>
                  <Translate contentKey="apiGatewayApp.billerPlatform.biller">Biller</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {billerPlatformList.map((billerPlatform, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${billerPlatform.id}`} color="link" size="sm">
                      {billerPlatform.id}
                    </Button>
                  </td>
                  <td>{billerPlatform.billerplatformID}</td>
                  <td>{billerPlatform.billerPlatform}</td>
                  <td>{billerPlatform.amount}</td>
                  <td>
                    {billerPlatform.billerId ? (
                      <Link to={`biller-my-suffix/${billerPlatform.billerId}`}>{billerPlatform.billerId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${billerPlatform.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${billerPlatform.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${billerPlatform.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="apiGatewayApp.billerPlatform.home.notFound">No Biller Platforms found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ billerPlatform }: IRootState) => ({
  billerPlatformList: billerPlatform.entities,
  loading: billerPlatform.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(BillerPlatformMySuffix);
