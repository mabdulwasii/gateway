import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import SchemeCategoryMySuffix from './scheme-category-my-suffix';
import SchemeCategoryMySuffixDetail from './scheme-category-my-suffix-detail';
import SchemeCategoryMySuffixUpdate from './scheme-category-my-suffix-update';
import SchemeCategoryMySuffixDeleteDialog from './scheme-category-my-suffix-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={SchemeCategoryMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={SchemeCategoryMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={SchemeCategoryMySuffixDetail} />
      <ErrorBoundaryRoute path={match.url} component={SchemeCategoryMySuffix} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={SchemeCategoryMySuffixDeleteDialog} />
  </>
);

export default Routes;
