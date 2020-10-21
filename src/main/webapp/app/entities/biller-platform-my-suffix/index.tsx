import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import BillerPlatformMySuffix from './biller-platform-my-suffix';
import BillerPlatformMySuffixDetail from './biller-platform-my-suffix-detail';
import BillerPlatformMySuffixUpdate from './biller-platform-my-suffix-update';
import BillerPlatformMySuffixDeleteDialog from './biller-platform-my-suffix-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={BillerPlatformMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={BillerPlatformMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={BillerPlatformMySuffixDetail} />
      <ErrorBoundaryRoute path={match.url} component={BillerPlatformMySuffix} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={BillerPlatformMySuffixDeleteDialog} />
  </>
);

export default Routes;
