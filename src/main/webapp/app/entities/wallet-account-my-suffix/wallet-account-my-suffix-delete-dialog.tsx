import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { RouteComponentProps } from 'react-router-dom';
import { Modal, ModalHeader, ModalBody, ModalFooter, Button } from 'reactstrap';
import { Translate, ICrudGetAction, ICrudDeleteAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IWalletAccountMySuffix } from 'app/shared/model/wallet-account-my-suffix.model';
import { IRootState } from 'app/shared/reducers';
import { getEntity, deleteEntity } from './wallet-account-my-suffix.reducer';

export interface IWalletAccountMySuffixDeleteDialogProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const WalletAccountMySuffixDeleteDialog = (props: IWalletAccountMySuffixDeleteDialogProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const handleClose = () => {
    props.history.push('/wallet-account-my-suffix');
  };

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const confirmDelete = () => {
    props.deleteEntity(props.walletAccountEntity.id);
  };

  const { walletAccountEntity } = props;
  return (
    <Modal isOpen toggle={handleClose}>
      <ModalHeader toggle={handleClose}>
        <Translate contentKey="entity.delete.title">Confirm delete operation</Translate>
      </ModalHeader>
      <ModalBody id="apiGatewayApp.walletAccount.delete.question">
        <Translate contentKey="apiGatewayApp.walletAccount.delete.question" interpolate={{ id: walletAccountEntity.id }}>
          Are you sure you want to delete this WalletAccount?
        </Translate>
      </ModalBody>
      <ModalFooter>
        <Button color="secondary" onClick={handleClose}>
          <FontAwesomeIcon icon="ban" />
          &nbsp;
          <Translate contentKey="entity.action.cancel">Cancel</Translate>
        </Button>
        <Button id="jhi-confirm-delete-walletAccount" color="danger" onClick={confirmDelete}>
          <FontAwesomeIcon icon="trash" />
          &nbsp;
          <Translate contentKey="entity.action.delete">Delete</Translate>
        </Button>
      </ModalFooter>
    </Modal>
  );
};

const mapStateToProps = ({ walletAccount }: IRootState) => ({
  walletAccountEntity: walletAccount.entity,
  updateSuccess: walletAccount.updateSuccess,
});

const mapDispatchToProps = { getEntity, deleteEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(WalletAccountMySuffixDeleteDialog);
