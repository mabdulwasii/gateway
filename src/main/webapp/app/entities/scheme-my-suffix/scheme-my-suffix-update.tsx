import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { ISchemeCategoryMySuffix } from 'app/shared/model/scheme-category-my-suffix.model';
import { getEntities as getSchemeCategories } from 'app/entities/scheme-category-my-suffix/scheme-category-my-suffix.reducer';
import { getEntity, updateEntity, createEntity, reset } from './scheme-my-suffix.reducer';
import { ISchemeMySuffix } from 'app/shared/model/scheme-my-suffix.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ISchemeMySuffixUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const SchemeMySuffixUpdate = (props: ISchemeMySuffixUpdateProps) => {
  const [schemeCategoryId, setSchemeCategoryId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { schemeEntity, schemeCategories, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/scheme-my-suffix');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getSchemeCategories();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...schemeEntity,
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
          <h2 id="apiGatewayApp.scheme.home.createOrEditLabel">
            <Translate contentKey="apiGatewayApp.scheme.home.createOrEditLabel">Create or edit a Scheme</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : schemeEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="scheme-my-suffix-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="scheme-my-suffix-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="schemeIDLabel" for="scheme-my-suffix-schemeID">
                  <Translate contentKey="apiGatewayApp.scheme.schemeID">Scheme ID</Translate>
                </Label>
                <AvField id="scheme-my-suffix-schemeID" type="string" className="form-control" name="schemeID" />
              </AvGroup>
              <AvGroup>
                <Label id="schemeLabel" for="scheme-my-suffix-scheme">
                  <Translate contentKey="apiGatewayApp.scheme.scheme">Scheme</Translate>
                </Label>
                <AvField id="scheme-my-suffix-scheme" type="text" name="scheme" />
              </AvGroup>
              <AvGroup>
                <Label for="scheme-my-suffix-schemeCategory">
                  <Translate contentKey="apiGatewayApp.scheme.schemeCategory">Scheme Category</Translate>
                </Label>
                <AvInput id="scheme-my-suffix-schemeCategory" type="select" className="form-control" name="schemeCategoryId">
                  <option value="" key="0" />
                  {schemeCategories
                    ? schemeCategories.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/scheme-my-suffix" replace color="info">
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
  schemeCategories: storeState.schemeCategory.entities,
  schemeEntity: storeState.scheme.entity,
  loading: storeState.scheme.loading,
  updating: storeState.scheme.updating,
  updateSuccess: storeState.scheme.updateSuccess,
});

const mapDispatchToProps = {
  getSchemeCategories,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(SchemeMySuffixUpdate);
