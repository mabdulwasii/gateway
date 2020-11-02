import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IBillerCategoryMySuffix } from 'app/shared/model/biller-category-my-suffix.model';
import { getEntities as getBillerCategories } from 'app/entities/biller-category-my-suffix/biller-category-my-suffix.reducer';
import { getEntity, updateEntity, createEntity, reset } from './biller-my-suffix.reducer';
import { IBillerMySuffix } from 'app/shared/model/biller-my-suffix.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IBillerMySuffixUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const BillerMySuffixUpdate = (props: IBillerMySuffixUpdateProps) => {
  const [billerCategoryId, setBillerCategoryId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { billerEntity, billerCategories, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/biller-my-suffix' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getBillerCategories();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...billerEntity,
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
          <h2 id="apiGatewayApp.biller.home.createOrEditLabel">
            <Translate contentKey="apiGatewayApp.biller.home.createOrEditLabel">Create or edit a Biller</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : billerEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="biller-my-suffix-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="biller-my-suffix-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="billerIDLabel" for="biller-my-suffix-billerID">
                  <Translate contentKey="apiGatewayApp.biller.billerID">Biller ID</Translate>
                </Label>
                <AvField id="biller-my-suffix-billerID" type="string" className="form-control" name="billerID" />
              </AvGroup>
              <AvGroup>
                <Label id="billerLabel" for="biller-my-suffix-biller">
                  <Translate contentKey="apiGatewayApp.biller.biller">Biller</Translate>
                </Label>
                <AvField id="biller-my-suffix-biller" type="text" name="biller" />
              </AvGroup>
              <AvGroup>
                <Label id="addressLabel" for="biller-my-suffix-address">
                  <Translate contentKey="apiGatewayApp.biller.address">Address</Translate>
                </Label>
                <AvField id="biller-my-suffix-address" type="text" name="address" />
              </AvGroup>
              <AvGroup>
                <Label id="phoneNumberLabel" for="biller-my-suffix-phoneNumber">
                  <Translate contentKey="apiGatewayApp.biller.phoneNumber">Phone Number</Translate>
                </Label>
                <AvField id="biller-my-suffix-phoneNumber" type="text" name="phoneNumber" />
              </AvGroup>
              <AvGroup>
                <Label for="biller-my-suffix-billerCategory">
                  <Translate contentKey="apiGatewayApp.biller.billerCategory">Biller Category</Translate>
                </Label>
                <AvInput id="biller-my-suffix-billerCategory" type="select" className="form-control" name="billerCategoryId">
                  <option value="" key="0" />
                  {billerCategories
                    ? billerCategories.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/biller-my-suffix" replace color="info">
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
  billerCategories: storeState.billerCategory.entities,
  billerEntity: storeState.biller.entity,
  loading: storeState.biller.loading,
  updating: storeState.biller.updating,
  updateSuccess: storeState.biller.updateSuccess,
});

const mapDispatchToProps = {
  getBillerCategories,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(BillerMySuffixUpdate);
