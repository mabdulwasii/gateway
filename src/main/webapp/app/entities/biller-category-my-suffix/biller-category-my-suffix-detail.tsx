import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './biller-category-my-suffix.reducer';
import { IBillerCategoryMySuffix } from 'app/shared/model/biller-category-my-suffix.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IBillerCategoryMySuffixDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const BillerCategoryMySuffixDetail = (props: IBillerCategoryMySuffixDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { billerCategoryEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="apiGatewayApp.billerCategory.detail.title">BillerCategory</Translate> [<b>{billerCategoryEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="billercategoryID">
              <Translate contentKey="apiGatewayApp.billerCategory.billercategoryID">Billercategory ID</Translate>
            </span>
          </dt>
          <dd>{billerCategoryEntity.billercategoryID}</dd>
          <dt>
            <span id="billerCategory">
              <Translate contentKey="apiGatewayApp.billerCategory.billerCategory">Biller Category</Translate>
            </span>
          </dt>
          <dd>{billerCategoryEntity.billerCategory}</dd>
        </dl>
        <Button tag={Link} to="/biller-category-my-suffix" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/biller-category-my-suffix/${billerCategoryEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ billerCategory }: IRootState) => ({
  billerCategoryEntity: billerCategory.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(BillerCategoryMySuffixDetail);
