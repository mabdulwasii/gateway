import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { RouteComponentProps } from 'react-router-dom';
import { Modal, ModalHeader, ModalBody, ModalFooter, Button } from 'reactstrap';
import { Translate, ICrudGetAction, ICrudDeleteAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { ICountrolAccountMySuffix } from 'app/shared/model/countrol-account-my-suffix.model';
import { IRootState } from 'app/shared/reducers';
import { getEntity, deleteEntity } from './countrol-account-my-suffix.reducer';

export interface ICountrolAccountMySuffixDeleteDialogProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const CountrolAccountMySuffixDeleteDialog = (props: ICountrolAccountMySuffixDeleteDialogProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const handleClose = () => {
    props.history.push('/countrol-account-my-suffix');
  };

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const confirmDelete = () => {
    props.deleteEntity(props.countrolAccountEntity.id);
  };

  const { countrolAccountEntity } = props;
  return (
    <Modal isOpen toggle={handleClose}>
      <ModalHeader toggle={handleClose}>
        <Translate contentKey="entity.delete.title">Confirm delete operation</Translate>
      </ModalHeader>
      <ModalBody id="apiGatewayApp.countrolAccount.delete.question">
        <Translate contentKey="apiGatewayApp.countrolAccount.delete.question" interpolate={{ id: countrolAccountEntity.id }}>
          Are you sure you want to delete this CountrolAccount?
        </Translate>
      </ModalBody>
      <ModalFooter>
        <Button color="secondary" onClick={handleClose}>
          <FontAwesomeIcon icon="ban" />
          &nbsp;
          <Translate contentKey="entity.action.cancel">Cancel</Translate>
        </Button>
        <Button id="jhi-confirm-delete-countrolAccount" color="danger" onClick={confirmDelete}>
          <FontAwesomeIcon icon="trash" />
          &nbsp;
          <Translate contentKey="entity.action.delete">Delete</Translate>
        </Button>
      </ModalFooter>
    </Modal>
  );
};

const mapStateToProps = ({ countrolAccount }: IRootState) => ({
  countrolAccountEntity: countrolAccount.entity,
  updateSuccess: countrolAccount.updateSuccess,
});

const mapDispatchToProps = { getEntity, deleteEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(CountrolAccountMySuffixDeleteDialog);
