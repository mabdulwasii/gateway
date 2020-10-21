import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './scheme-my-suffix.reducer';
import { ISchemeMySuffix } from 'app/shared/model/scheme-my-suffix.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ISchemeMySuffixProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const SchemeMySuffix = (props: ISchemeMySuffixProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { schemeList, match, loading } = props;
  return (
    <div>
      <h2 id="scheme-my-suffix-heading">
        <Translate contentKey="apiGatewayApp.scheme.home.title">Schemes</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="apiGatewayApp.scheme.home.createLabel">Create new Scheme</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {schemeList && schemeList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="apiGatewayApp.scheme.schemeID">Scheme ID</Translate>
                </th>
                <th>
                  <Translate contentKey="apiGatewayApp.scheme.scheme">Scheme</Translate>
                </th>
                <th>
                  <Translate contentKey="apiGatewayApp.scheme.schemeCategory">Scheme Category</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {schemeList.map((scheme, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${scheme.id}`} color="link" size="sm">
                      {scheme.id}
                    </Button>
                  </td>
                  <td>{scheme.schemeID}</td>
                  <td>{scheme.scheme}</td>
                  <td>
                    {scheme.schemeCategoryId ? (
                      <Link to={`scheme-category-my-suffix/${scheme.schemeCategoryId}`}>{scheme.schemeCategoryId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${scheme.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${scheme.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${scheme.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="apiGatewayApp.scheme.home.notFound">No Schemes found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ scheme }: IRootState) => ({
  schemeList: scheme.entities,
  loading: scheme.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(SchemeMySuffix);
