import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './scheme-category-my-suffix.reducer';
import { ISchemeCategoryMySuffix } from 'app/shared/model/scheme-category-my-suffix.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ISchemeCategoryMySuffixUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const SchemeCategoryMySuffixUpdate = (props: ISchemeCategoryMySuffixUpdateProps) => {
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { schemeCategoryEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/scheme-category-my-suffix');
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
        ...schemeCategoryEntity,
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
          <h2 id="apiGatewayApp.schemeCategory.home.createOrEditLabel">
            <Translate contentKey="apiGatewayApp.schemeCategory.home.createOrEditLabel">Create or edit a SchemeCategory</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : schemeCategoryEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="scheme-category-my-suffix-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="scheme-category-my-suffix-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="schemecategoryIDLabel" for="scheme-category-my-suffix-schemecategoryID">
                  <Translate contentKey="apiGatewayApp.schemeCategory.schemecategoryID">Schemecategory ID</Translate>
                </Label>
                <AvField id="scheme-category-my-suffix-schemecategoryID" type="string" className="form-control" name="schemecategoryID" />
              </AvGroup>
              <AvGroup>
                <Label id="schemeCategoryLabel" for="scheme-category-my-suffix-schemeCategory">
                  <Translate contentKey="apiGatewayApp.schemeCategory.schemeCategory">Scheme Category</Translate>
                </Label>
                <AvField id="scheme-category-my-suffix-schemeCategory" type="text" name="schemeCategory" />
              </AvGroup>
              <AvGroup>
                <Label id="descriptionLabel" for="scheme-category-my-suffix-description">
                  <Translate contentKey="apiGatewayApp.schemeCategory.description">Description</Translate>
                </Label>
                <AvField id="scheme-category-my-suffix-description" type="text" name="description" />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/scheme-category-my-suffix" replace color="info">
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
  schemeCategoryEntity: storeState.schemeCategory.entity,
  loading: storeState.schemeCategory.loading,
  updating: storeState.schemeCategory.updating,
  updateSuccess: storeState.schemeCategory.updateSuccess,
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(SchemeCategoryMySuffixUpdate);
