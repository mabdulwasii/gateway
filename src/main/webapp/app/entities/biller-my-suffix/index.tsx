import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import BillerMySuffix from './biller-my-suffix';
import BillerMySuffixDetail from './biller-my-suffix-detail';
import BillerMySuffixUpdate from './biller-my-suffix-update';
import BillerMySuffixDeleteDialog from './biller-my-suffix-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={BillerMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={BillerMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={BillerMySuffixDetail} />
      <ErrorBoundaryRoute path={match.url} component={BillerMySuffix} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={BillerMySuffixDeleteDialog} />
  </>
);

export default Routes;
