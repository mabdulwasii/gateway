import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import WalletAccountMySuffix from './wallet-account-my-suffix';
import WalletAccountMySuffixDetail from './wallet-account-my-suffix-detail';
import WalletAccountMySuffixUpdate from './wallet-account-my-suffix-update';
import WalletAccountMySuffixDeleteDialog from './wallet-account-my-suffix-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={WalletAccountMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={WalletAccountMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={WalletAccountMySuffixDetail} />
      <ErrorBoundaryRoute path={match.url} component={WalletAccountMySuffix} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={WalletAccountMySuffixDeleteDialog} />
  </>
);

export default Routes;
