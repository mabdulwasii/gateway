import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './profile-type-my-suffix.reducer';
import { IProfileTypeMySuffix } from 'app/shared/model/profile-type-my-suffix.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IProfileTypeMySuffixProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const ProfileTypeMySuffix = (props: IProfileTypeMySuffixProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { profileTypeList, match, loading } = props;
  return (
    <div>
      <h2 id="profile-type-my-suffix-heading">
        <Translate contentKey="apiGatewayApp.profileType.home.title">Profile Types</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="apiGatewayApp.profileType.home.createLabel">Create new Profile Type</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {profileTypeList && profileTypeList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="apiGatewayApp.profileType.profiletypeID">Profiletype ID</Translate>
                </th>
                <th>
                  <Translate contentKey="apiGatewayApp.profileType.profiletype">Profiletype</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {profileTypeList.map((profileType, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${profileType.id}`} color="link" size="sm">
                      {profileType.id}
                    </Button>
                  </td>
                  <td>{profileType.profiletypeID}</td>
                  <td>{profileType.profiletype}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${profileType.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${profileType.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${profileType.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="apiGatewayApp.profileType.home.notFound">No Profile Types found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ profileType }: IRootState) => ({
  profileTypeList: profileType.entities,
  loading: profileType.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ProfileTypeMySuffix);
