import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './scheme-category-my-suffix.reducer';
import { ISchemeCategoryMySuffix } from 'app/shared/model/scheme-category-my-suffix.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ISchemeCategoryMySuffixDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const SchemeCategoryMySuffixDetail = (props: ISchemeCategoryMySuffixDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { schemeCategoryEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="apiGatewayApp.schemeCategory.detail.title">SchemeCategory</Translate> [<b>{schemeCategoryEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="schemecategoryID">
              <Translate contentKey="apiGatewayApp.schemeCategory.schemecategoryID">Schemecategory ID</Translate>
            </span>
          </dt>
          <dd>{schemeCategoryEntity.schemecategoryID}</dd>
          <dt>
            <span id="schemeCategory">
              <Translate contentKey="apiGatewayApp.schemeCategory.schemeCategory">Scheme Category</Translate>
            </span>
          </dt>
          <dd>{schemeCategoryEntity.schemeCategory}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="apiGatewayApp.schemeCategory.description">Description</Translate>
            </span>
          </dt>
          <dd>{schemeCategoryEntity.description}</dd>
        </dl>
        <Button tag={Link} to="/scheme-category-my-suffix" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/scheme-category-my-suffix/${schemeCategoryEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ schemeCategory }: IRootState) => ({
  schemeCategoryEntity: schemeCategory.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(SchemeCategoryMySuffixDetail);
