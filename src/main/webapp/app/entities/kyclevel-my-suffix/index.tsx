import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import KyclevelMySuffix from './kyclevel-my-suffix';
import KyclevelMySuffixDetail from './kyclevel-my-suffix-detail';
import KyclevelMySuffixUpdate from './kyclevel-my-suffix-update';
import KyclevelMySuffixDeleteDialog from './kyclevel-my-suffix-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={KyclevelMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={KyclevelMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={KyclevelMySuffixDetail} />
      <ErrorBoundaryRoute path={match.url} component={KyclevelMySuffix} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={KyclevelMySuffixDeleteDialog} />
  </>
);

export default Routes;
