import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import BillerCategoryMySuffix from './biller-category-my-suffix';
import BillerCategoryMySuffixDetail from './biller-category-my-suffix-detail';
import BillerCategoryMySuffixUpdate from './biller-category-my-suffix-update';
import BillerCategoryMySuffixDeleteDialog from './biller-category-my-suffix-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={BillerCategoryMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={BillerCategoryMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={BillerCategoryMySuffixDetail} />
      <ErrorBoundaryRoute path={match.url} component={BillerCategoryMySuffix} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={BillerCategoryMySuffixDeleteDialog} />
  </>
);

export default Routes;
