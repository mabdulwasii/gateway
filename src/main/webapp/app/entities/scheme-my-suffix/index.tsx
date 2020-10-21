import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import SchemeMySuffix from './scheme-my-suffix';
import SchemeMySuffixDetail from './scheme-my-suffix-detail';
import SchemeMySuffixUpdate from './scheme-my-suffix-update';
import SchemeMySuffixDeleteDialog from './scheme-my-suffix-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={SchemeMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={SchemeMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={SchemeMySuffixDetail} />
      <ErrorBoundaryRoute path={match.url} component={SchemeMySuffix} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={SchemeMySuffixDeleteDialog} />
  </>
);

export default Routes;
