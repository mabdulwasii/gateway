import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ProfileTypeMySuffix from './profile-type-my-suffix';
import ProfileTypeMySuffixDetail from './profile-type-my-suffix-detail';
import ProfileTypeMySuffixUpdate from './profile-type-my-suffix-update';
import ProfileTypeMySuffixDeleteDialog from './profile-type-my-suffix-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ProfileTypeMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ProfileTypeMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ProfileTypeMySuffixDetail} />
      <ErrorBoundaryRoute path={match.url} component={ProfileTypeMySuffix} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={ProfileTypeMySuffixDeleteDialog} />
  </>
);

export default Routes;
