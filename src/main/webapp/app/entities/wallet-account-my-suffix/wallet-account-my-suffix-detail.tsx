import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './wallet-account-my-suffix.reducer';
import { IWalletAccountMySuffix } from 'app/shared/model/wallet-account-my-suffix.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IWalletAccountMySuffixDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const WalletAccountMySuffixDetail = (props: IWalletAccountMySuffixDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { walletAccountEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="apiGatewayApp.walletAccount.detail.title">WalletAccount</Translate> [<b>{walletAccountEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="accountNumber">
              <Translate contentKey="apiGatewayApp.walletAccount.accountNumber">Account Number</Translate>
            </span>
          </dt>
          <dd>{walletAccountEntity.accountNumber}</dd>
          <dt>
            <span id="currentBalance">
              <Translate contentKey="apiGatewayApp.walletAccount.currentBalance">Current Balance</Translate>
            </span>
          </dt>
          <dd>{walletAccountEntity.currentBalance}</dd>
          <dt>
            <span id="dateOpened">
              <Translate contentKey="apiGatewayApp.walletAccount.dateOpened">Date Opened</Translate>
            </span>
          </dt>
          <dd>
            {walletAccountEntity.dateOpened ? (
              <TextFormat value={walletAccountEntity.dateOpened} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <Translate contentKey="apiGatewayApp.walletAccount.scheme">Scheme</Translate>
          </dt>
          <dd>{walletAccountEntity.schemeId ? walletAccountEntity.schemeId : ''}</dd>
          <dt>
            <Translate contentKey="apiGatewayApp.walletAccount.walletAccountType">Wallet Account Type</Translate>
          </dt>
          <dd>{walletAccountEntity.walletAccountTypeId ? walletAccountEntity.walletAccountTypeId : ''}</dd>
          <dt>
            <Translate contentKey="apiGatewayApp.walletAccount.accountOwner">Account Owner</Translate>
          </dt>
          <dd>{walletAccountEntity.accountOwnerPhoneNumber ? walletAccountEntity.accountOwnerPhoneNumber : ''}</dd>
        </dl>
        <Button tag={Link} to="/wallet-account-my-suffix" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/wallet-account-my-suffix/${walletAccountEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ walletAccount }: IRootState) => ({
  walletAccountEntity: walletAccount.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(WalletAccountMySuffixDetail);
