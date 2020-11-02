import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import BillerTransactionMySuffix from './biller-transaction-my-suffix';
import BillerTransactionMySuffixDetail from './biller-transaction-my-suffix-detail';
import BillerTransactionMySuffixUpdate from './biller-transaction-my-suffix-update';
import BillerTransactionMySuffixDeleteDialog from './biller-transaction-my-suffix-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={BillerTransactionMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={BillerTransactionMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={BillerTransactionMySuffixDetail} />
      <ErrorBoundaryRoute path={match.url} component={BillerTransactionMySuffix} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={BillerTransactionMySuffixDeleteDialog} />
  </>
);

export default Routes;
