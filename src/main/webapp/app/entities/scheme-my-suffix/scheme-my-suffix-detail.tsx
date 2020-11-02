import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './scheme-my-suffix.reducer';
import { ISchemeMySuffix } from 'app/shared/model/scheme-my-suffix.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ISchemeMySuffixDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const SchemeMySuffixDetail = (props: ISchemeMySuffixDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { schemeEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="apiGatewayApp.scheme.detail.title">Scheme</Translate> [<b>{schemeEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="schemeID">
              <Translate contentKey="apiGatewayApp.scheme.schemeID">Scheme ID</Translate>
            </span>
          </dt>
          <dd>{schemeEntity.schemeID}</dd>
          <dt>
            <span id="scheme">
              <Translate contentKey="apiGatewayApp.scheme.scheme">Scheme</Translate>
            </span>
          </dt>
          <dd>{schemeEntity.scheme}</dd>
          <dt>
            <Translate contentKey="apiGatewayApp.scheme.schemeCategory">Scheme Category</Translate>
          </dt>
          <dd>{schemeEntity.schemeCategoryId ? schemeEntity.schemeCategoryId : ''}</dd>
        </dl>
        <Button tag={Link} to="/scheme-my-suffix" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/scheme-my-suffix/${schemeEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ scheme }: IRootState) => ({
  schemeEntity: scheme.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(SchemeMySuffixDetail);
