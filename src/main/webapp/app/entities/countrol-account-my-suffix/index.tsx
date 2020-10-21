import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import CountrolAccountMySuffix from './countrol-account-my-suffix';
import CountrolAccountMySuffixDetail from './countrol-account-my-suffix-detail';
import CountrolAccountMySuffixUpdate from './countrol-account-my-suffix-update';
import CountrolAccountMySuffixDeleteDialog from './countrol-account-my-suffix-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={CountrolAccountMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={CountrolAccountMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={CountrolAccountMySuffixDetail} />
      <ErrorBoundaryRoute path={match.url} component={CountrolAccountMySuffix} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={CountrolAccountMySuffixDeleteDialog} />
  </>
);

export default Routes;
