import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './wallet-account-type-my-suffix.reducer';
import { IWalletAccountTypeMySuffix } from 'app/shared/model/wallet-account-type-my-suffix.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IWalletAccountTypeMySuffixUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const WalletAccountTypeMySuffixUpdate = (props: IWalletAccountTypeMySuffixUpdateProps) => {
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { walletAccountTypeEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/wallet-account-type-my-suffix');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...walletAccountTypeEntity,
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
          <h2 id="apiGatewayApp.walletAccountType.home.createOrEditLabel">
            <Translate contentKey="apiGatewayApp.walletAccountType.home.createOrEditLabel">Create or edit a WalletAccountType</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : walletAccountTypeEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="wallet-account-type-my-suffix-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="wallet-account-type-my-suffix-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="accountypeIDLabel" for="wallet-account-type-my-suffix-accountypeID">
                  <Translate contentKey="apiGatewayApp.walletAccountType.accountypeID">Accountype ID</Translate>
                </Label>
                <AvField id="wallet-account-type-my-suffix-accountypeID" type="string" className="form-control" name="accountypeID" />
              </AvGroup>
              <AvGroup>
                <Label id="walletAccountTypeLabel" for="wallet-account-type-my-suffix-walletAccountType">
                  <Translate contentKey="apiGatewayApp.walletAccountType.walletAccountType">Wallet Account Type</Translate>
                </Label>
                <AvField id="wallet-account-type-my-suffix-walletAccountType" type="text" name="walletAccountType" />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/wallet-account-type-my-suffix" replace color="info">
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
  walletAccountTypeEntity: storeState.walletAccountType.entity,
  loading: storeState.walletAccountType.loading,
  updating: storeState.walletAccountType.updating,
  updateSuccess: storeState.walletAccountType.updateSuccess,
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(WalletAccountTypeMySuffixUpdate);
