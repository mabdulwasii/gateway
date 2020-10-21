import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './kyclevel-my-suffix.reducer';
import { IKyclevelMySuffix } from 'app/shared/model/kyclevel-my-suffix.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IKyclevelMySuffixDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const KyclevelMySuffixDetail = (props: IKyclevelMySuffixDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { kyclevelEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="apiGatewayApp.kyclevel.detail.title">Kyclevel</Translate> [<b>{kyclevelEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="kycID">
              <Translate contentKey="apiGatewayApp.kyclevel.kycID">Kyc ID</Translate>
            </span>
          </dt>
          <dd>{kyclevelEntity.kycID}</dd>
          <dt>
            <span id="kyc">
              <Translate contentKey="apiGatewayApp.kyclevel.kyc">Kyc</Translate>
            </span>
          </dt>
          <dd>{kyclevelEntity.kyc}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="apiGatewayApp.kyclevel.description">Description</Translate>
            </span>
          </dt>
          <dd>{kyclevelEntity.description}</dd>
          <dt>
            <span id="kycLevel">
              <Translate contentKey="apiGatewayApp.kyclevel.kycLevel">Kyc Level</Translate>
            </span>
          </dt>
          <dd>{kyclevelEntity.kycLevel}</dd>
          <dt>
            <span id="phoneNumber">
              <Translate contentKey="apiGatewayApp.kyclevel.phoneNumber">Phone Number</Translate>
            </span>
          </dt>
          <dd>{kyclevelEntity.phoneNumber ? 'true' : 'false'}</dd>
          <dt>
            <span id="emailAddress">
              <Translate contentKey="apiGatewayApp.kyclevel.emailAddress">Email Address</Translate>
            </span>
          </dt>
          <dd>{kyclevelEntity.emailAddress ? 'true' : 'false'}</dd>
          <dt>
            <span id="firstName">
              <Translate contentKey="apiGatewayApp.kyclevel.firstName">First Name</Translate>
            </span>
          </dt>
          <dd>{kyclevelEntity.firstName ? 'true' : 'false'}</dd>
          <dt>
            <span id="lastName">
              <Translate contentKey="apiGatewayApp.kyclevel.lastName">Last Name</Translate>
            </span>
          </dt>
          <dd>{kyclevelEntity.lastName ? 'true' : 'false'}</dd>
          <dt>
            <span id="gender">
              <Translate contentKey="apiGatewayApp.kyclevel.gender">Gender</Translate>
            </span>
          </dt>
          <dd>{kyclevelEntity.gender ? 'true' : 'false'}</dd>
          <dt>
            <span id="dateofBirth">
              <Translate contentKey="apiGatewayApp.kyclevel.dateofBirth">Dateof Birth</Translate>
            </span>
          </dt>
          <dd>{kyclevelEntity.dateofBirth ? 'true' : 'false'}</dd>
          <dt>
            <span id="address">
              <Translate contentKey="apiGatewayApp.kyclevel.address">Address</Translate>
            </span>
          </dt>
          <dd>{kyclevelEntity.address ? 'true' : 'false'}</dd>
          <dt>
            <span id="photoUpload">
              <Translate contentKey="apiGatewayApp.kyclevel.photoUpload">Photo Upload</Translate>
            </span>
          </dt>
          <dd>{kyclevelEntity.photoUpload ? 'true' : 'false'}</dd>
          <dt>
            <span id="verifiedBVN">
              <Translate contentKey="apiGatewayApp.kyclevel.verifiedBVN">Verified BVN</Translate>
            </span>
          </dt>
          <dd>{kyclevelEntity.verifiedBVN ? 'true' : 'false'}</dd>
          <dt>
            <span id="verifiedValidID">
              <Translate contentKey="apiGatewayApp.kyclevel.verifiedValidID">Verified Valid ID</Translate>
            </span>
          </dt>
          <dd>{kyclevelEntity.verifiedValidID ? 'true' : 'false'}</dd>
          <dt>
            <span id="evidenceofAddress">
              <Translate contentKey="apiGatewayApp.kyclevel.evidenceofAddress">Evidenceof Address</Translate>
            </span>
          </dt>
          <dd>{kyclevelEntity.evidenceofAddress ? 'true' : 'false'}</dd>
          <dt>
            <span id="verificationofAddress">
              <Translate contentKey="apiGatewayApp.kyclevel.verificationofAddress">Verificationof Address</Translate>
            </span>
          </dt>
          <dd>{kyclevelEntity.verificationofAddress ? 'true' : 'false'}</dd>
          <dt>
            <span id="employmentDetails">
              <Translate contentKey="apiGatewayApp.kyclevel.employmentDetails">Employment Details</Translate>
            </span>
          </dt>
          <dd>{kyclevelEntity.employmentDetails ? 'true' : 'false'}</dd>
          <dt>
            <span id="dailyTransactionLimit">
              <Translate contentKey="apiGatewayApp.kyclevel.dailyTransactionLimit">Daily Transaction Limit</Translate>
            </span>
          </dt>
          <dd>{kyclevelEntity.dailyTransactionLimit}</dd>
          <dt>
            <span id="cumulativeBalanceLimit">
              <Translate contentKey="apiGatewayApp.kyclevel.cumulativeBalanceLimit">Cumulative Balance Limit</Translate>
            </span>
          </dt>
          <dd>{kyclevelEntity.cumulativeBalanceLimit}</dd>
          <dt>
            <span id="paymentTransaction">
              <Translate contentKey="apiGatewayApp.kyclevel.paymentTransaction">Payment Transaction</Translate>
            </span>
          </dt>
          <dd>{kyclevelEntity.paymentTransaction ? 'true' : 'false'}</dd>
          <dt>
            <span id="billerTransaction">
              <Translate contentKey="apiGatewayApp.kyclevel.billerTransaction">Biller Transaction</Translate>
            </span>
          </dt>
          <dd>{kyclevelEntity.billerTransaction ? 'true' : 'false'}</dd>
        </dl>
        <Button tag={Link} to="/kyclevel-my-suffix" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/kyclevel-my-suffix/${kyclevelEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ kyclevel }: IRootState) => ({
  kyclevelEntity: kyclevel.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(KyclevelMySuffixDetail);
