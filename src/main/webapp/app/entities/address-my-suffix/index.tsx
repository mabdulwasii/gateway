import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import AddressMySuffix from './address-my-suffix';
import AddressMySuffixDetail from './address-my-suffix-detail';
import AddressMySuffixUpdate from './address-my-suffix-update';
import AddressMySuffixDeleteDialog from './address-my-suffix-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={AddressMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={AddressMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={AddressMySuffixDetail} />
      <ErrorBoundaryRoute path={match.url} component={AddressMySuffix} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={AddressMySuffixDeleteDialog} />
  </>
);

export default Routes;
