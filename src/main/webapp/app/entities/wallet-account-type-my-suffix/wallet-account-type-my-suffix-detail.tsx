import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './wallet-account-type-my-suffix.reducer';
import { IWalletAccountTypeMySuffix } from 'app/shared/model/wallet-account-type-my-suffix.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IWalletAccountTypeMySuffixDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const WalletAccountTypeMySuffixDetail = (props: IWalletAccountTypeMySuffixDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { walletAccountTypeEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="apiGatewayApp.walletAccountType.detail.title">WalletAccountType</Translate> [
          <b>{walletAccountTypeEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="accountypeID">
              <Translate contentKey="apiGatewayApp.walletAccountType.accountypeID">Accountype ID</Translate>
            </span>
          </dt>
          <dd>{walletAccountTypeEntity.accountypeID}</dd>
          <dt>
            <span id="walletAccountType">
              <Translate contentKey="apiGatewayApp.walletAccountType.walletAccountType">Wallet Account Type</Translate>
            </span>
          </dt>
          <dd>{walletAccountTypeEntity.walletAccountType}</dd>
        </dl>
        <Button tag={Link} to="/wallet-account-type-my-suffix" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/wallet-account-type-my-suffix/${walletAccountTypeEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ walletAccountType }: IRootState) => ({
  walletAccountTypeEntity: walletAccountType.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(WalletAccountTypeMySuffixDetail);
