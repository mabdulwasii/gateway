import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import WalletAccountTypeMySuffix from './wallet-account-type-my-suffix';
import WalletAccountTypeMySuffixDetail from './wallet-account-type-my-suffix-detail';
import WalletAccountTypeMySuffixUpdate from './wallet-account-type-my-suffix-update';
import WalletAccountTypeMySuffixDeleteDialog from './wallet-account-type-my-suffix-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={WalletAccountTypeMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={WalletAccountTypeMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={WalletAccountTypeMySuffixDetail} />
      <ErrorBoundaryRoute path={match.url} component={WalletAccountTypeMySuffix} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={WalletAccountTypeMySuffixDeleteDialog} />
  </>
);

export default Routes;
