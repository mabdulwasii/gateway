import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ProfileMySuffix from './profile-my-suffix';
import ProfileMySuffixDetail from './profile-my-suffix-detail';
import ProfileMySuffixUpdate from './profile-my-suffix-update';
import ProfileMySuffixDeleteDialog from './profile-my-suffix-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ProfileMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ProfileMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ProfileMySuffixDetail} />
      <ErrorBoundaryRoute path={match.url} component={ProfileMySuffix} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={ProfileMySuffixDeleteDialog} />
  </>
);

export default Routes;
