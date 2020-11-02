import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './profile-type-my-suffix.reducer';
import { IProfileTypeMySuffix } from 'app/shared/model/profile-type-my-suffix.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IProfileTypeMySuffixDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ProfileTypeMySuffixDetail = (props: IProfileTypeMySuffixDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { profileTypeEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="apiGatewayApp.profileType.detail.title">ProfileType</Translate> [<b>{profileTypeEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="profiletypeID">
              <Translate contentKey="apiGatewayApp.profileType.profiletypeID">Profiletype ID</Translate>
            </span>
          </dt>
          <dd>{profileTypeEntity.profiletypeID}</dd>
          <dt>
            <span id="profiletype">
              <Translate contentKey="apiGatewayApp.profileType.profiletype">Profiletype</Translate>
            </span>
          </dt>
          <dd>{profileTypeEntity.profiletype}</dd>
        </dl>
        <Button tag={Link} to="/profile-type-my-suffix" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/profile-type-my-suffix/${profileTypeEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ profileType }: IRootState) => ({
  profileTypeEntity: profileType.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ProfileTypeMySuffixDetail);
