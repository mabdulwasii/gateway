import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './biller-category-my-suffix.reducer';
import { IBillerCategoryMySuffix } from 'app/shared/model/biller-category-my-suffix.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IBillerCategoryMySuffixUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const BillerCategoryMySuffixUpdate = (props: IBillerCategoryMySuffixUpdateProps) => {
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { billerCategoryEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/biller-category-my-suffix');
  };

  useEffect(() => {
    if (!isNew) {
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
        ...billerCategoryEntity,
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
          <h2 id="apiGatewayApp.billerCategory.home.createOrEditLabel">
            <Translate contentKey="apiGatewayApp.billerCategory.home.createOrEditLabel">Create or edit a BillerCategory</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : billerCategoryEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="biller-category-my-suffix-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="biller-category-my-suffix-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="billercategoryIDLabel" for="biller-category-my-suffix-billercategoryID">
                  <Translate contentKey="apiGatewayApp.billerCategory.billercategoryID">Billercategory ID</Translate>
                </Label>
                <AvField id="biller-category-my-suffix-billercategoryID" type="string" className="form-control" name="billercategoryID" />
              </AvGroup>
              <AvGroup>
                <Label id="billerCategoryLabel" for="biller-category-my-suffix-billerCategory">
                  <Translate contentKey="apiGatewayApp.billerCategory.billerCategory">Biller Category</Translate>
                </Label>
                <AvField id="biller-category-my-suffix-billerCategory" type="text" name="billerCategory" />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/biller-category-my-suffix" replace color="info">
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
  billerCategoryEntity: storeState.billerCategory.entity,
  loading: storeState.billerCategory.loading,
  updating: storeState.billerCategory.updating,
  updateSuccess: storeState.billerCategory.updateSuccess,
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(BillerCategoryMySuffixUpdate);
