import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { ISchemeMySuffix } from 'app/shared/model/scheme-my-suffix.model';
import { getEntities as getSchemes } from 'app/entities/scheme-my-suffix/scheme-my-suffix.reducer';
import { IWalletAccountTypeMySuffix } from 'app/shared/model/wallet-account-type-my-suffix.model';
import { getEntities as getWalletAccountTypes } from 'app/entities/wallet-account-type-my-suffix/wallet-account-type-my-suffix.reducer';
import { IProfileMySuffix } from 'app/shared/model/profile-my-suffix.model';
import { getEntities as getProfiles } from 'app/entities/profile-my-suffix/profile-my-suffix.reducer';
import { getEntity, updateEntity, createEntity, reset } from './wallet-account-my-suffix.reducer';
import { IWalletAccountMySuffix } from 'app/shared/model/wallet-account-my-suffix.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IWalletAccountMySuffixUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const WalletAccountMySuffixUpdate = (props: IWalletAccountMySuffixUpdateProps) => {
  const [schemeId, setSchemeId] = useState('0');
  const [walletAccountTypeId, setWalletAccountTypeId] = useState('0');
  const [accountOwnerId, setAccountOwnerId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { walletAccountEntity, schemes, walletAccountTypes, profiles, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/wallet-account-my-suffix');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getSchemes();
    props.getWalletAccountTypes();
    props.getProfiles();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...walletAccountEntity,
        ...values,
      };

      if (isNew) {
        props.createEntity(entity);
      } else {
        props.updateEntity(entity);
      }
    }
  };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="apiGatewayApp.walletAccount.home.createOrEditLabel">
            <Translate contentKey="apiGatewayApp.walletAccount.home.createOrEditLabel">Create or edit a WalletAccount</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : walletAccountEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="wallet-account-my-suffix-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="wallet-account-my-suffix-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="accountNumberLabel" for="wallet-account-my-suffix-accountNumber">
                  <Translate contentKey="apiGatewayApp.walletAccount.accountNumber">Account Number</Translate>
                </Label>
                <AvField id="wallet-account-my-suffix-accountNumber" type="string" className="form-control" name="accountNumber" />
              </AvGroup>
              <AvGroup>
                <Label id="currentBalanceLabel" for="wallet-account-my-suffix-currentBalance">
                  <Translate contentKey="apiGatewayApp.walletAccount.currentBalance">Current Balance</Translate>
                </Label>
                <AvField id="wallet-account-my-suffix-currentBalance" type="string" className="form-control" name="currentBalance" />
              </AvGroup>
              <AvGroup>
                <Label id="dateOpenedLabel" for="wallet-account-my-suffix-dateOpened">
                  <Translate contentKey="apiGatewayApp.walletAccount.dateOpened">Date Opened</Translate>
                </Label>
                <AvField id="wallet-account-my-suffix-dateOpened" type="date" className="form-control" name="dateOpened" />
              </AvGroup>
              <AvGroup>
                <Label for="wallet-account-my-suffix-scheme">
                  <Translate contentKey="apiGatewayApp.walletAccount.scheme">Scheme</Translate>
                </Label>
                <AvInput id="wallet-account-my-suffix-scheme" type="select" className="form-control" name="schemeId">
                  <option value="" key="0" />
                  {schemes
                    ? schemes.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="wallet-account-my-suffix-walletAccountType">
                  <Translate contentKey="apiGatewayApp.walletAccount.walletAccountType">Wallet Account Type</Translate>
                </Label>
                <AvInput id="wallet-account-my-suffix-walletAccountType" type="select" className="form-control" name="walletAccountTypeId">
                  <option value="" key="0" />
                  {walletAccountTypes
                    ? walletAccountTypes.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="wallet-account-my-suffix-accountOwner">
                  <Translate contentKey="apiGatewayApp.walletAccount.accountOwner">Account Owner</Translate>
                </Label>
                <AvInput id="wallet-account-my-suffix-accountOwner" type="select" className="form-control" name="accountOwnerId">
                  <option value="" key="0" />
                  {profiles
                    ? profiles.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.phoneNumber}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/wallet-account-my-suffix" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </AvForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

const mapStateToProps = (storeState: IRootState) => ({
  schemes: storeState.scheme.entities,
  walletAccountTypes: storeState.walletAccountType.entities,
  profiles: storeState.profile.entities,
  walletAccountEntity: storeState.walletAccount.entity,
  loading: storeState.walletAccount.loading,
  updating: storeState.walletAccount.updating,
  updateSuccess: storeState.walletAccount.updateSuccess,
});

const mapDispatchToProps = {
  getSchemes,
  getWalletAccountTypes,
  getProfiles,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(WalletAccountMySuffixUpdate);
