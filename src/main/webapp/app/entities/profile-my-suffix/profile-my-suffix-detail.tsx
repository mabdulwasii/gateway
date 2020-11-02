import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, openFile, byteSize, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './profile-my-suffix.reducer';
import { IProfileMySuffix } from 'app/shared/model/profile-my-suffix.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IProfileMySuffixDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ProfileMySuffixDetail = (props: IProfileMySuffixDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { profileEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="apiGatewayApp.profile.detail.title">Profile</Translate> [<b>{profileEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="profileID">
              <Translate contentKey="apiGatewayApp.profile.profileID">Profile ID</Translate>
            </span>
          </dt>
          <dd>{profileEntity.profileID}</dd>
          <dt>
            <span id="phoneNumber">
              <Translate contentKey="apiGatewayApp.profile.phoneNumber">Phone Number</Translate>
            </span>
          </dt>
          <dd>{profileEntity.phoneNumber}</dd>
          <dt>
            <span id="gender">
              <Translate contentKey="apiGatewayApp.profile.gender">Gender</Translate>
            </span>
          </dt>
          <dd>{profileEntity.gender}</dd>
          <dt>
            <span id="dateOfBirth">
              <Translate contentKey="apiGatewayApp.profile.dateOfBirth">Date Of Birth</Translate>
            </span>
          </dt>
          <dd>
            {profileEntity.dateOfBirth ? <TextFormat value={profileEntity.dateOfBirth} type="date" format={APP_LOCAL_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="address">
              <Translate contentKey="apiGatewayApp.profile.address">Address</Translate>
            </span>
          </dt>
          <dd>{profileEntity.address}</dd>
          <dt>
            <span id="photo">
              <Translate contentKey="apiGatewayApp.profile.photo">Photo</Translate>
            </span>
          </dt>
          <dd>
            {profileEntity.photo ? (
              <div>
                {profileEntity.photoContentType ? (
                  <a onClick={openFile(profileEntity.photoContentType, profileEntity.photo)}>
                    <img src={`data:${profileEntity.photoContentType};base64,${profileEntity.photo}`} style={{ maxHeight: '30px' }} />
                  </a>
                ) : null}
                <span>
                  {profileEntity.photoContentType}, {byteSize(profileEntity.photo)}
                </span>
              </div>
            ) : null}
          </dd>
          <dt>
            <span id="bvn">
              <Translate contentKey="apiGatewayApp.profile.bvn">Bvn</Translate>
            </span>
          </dt>
          <dd>{profileEntity.bvn}</dd>
          <dt>
            <span id="validID">
              <Translate contentKey="apiGatewayApp.profile.validID">Valid ID</Translate>
            </span>
          </dt>
          <dd>{profileEntity.validID}</dd>
          <dt>
            <Translate contentKey="apiGatewayApp.profile.user">User</Translate>
          </dt>
          <dd>{profileEntity.userLogin ? profileEntity.userLogin : ''}</dd>
          <dt>
            <Translate contentKey="apiGatewayApp.profile.profileType">Profile Type</Translate>
          </dt>
          <dd>{profileEntity.profileTypeId ? profileEntity.profileTypeId : ''}</dd>
          <dt>
            <Translate contentKey="apiGatewayApp.profile.kyc">Kyc</Translate>
          </dt>
          <dd>{profileEntity.kycId ? profileEntity.kycId : ''}</dd>
        </dl>
        <Button tag={Link} to="/profile-my-suffix" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/profile-my-suffix/${profileEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ profile }: IRootState) => ({
  profileEntity: profile.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ProfileMySuffixDetail);
