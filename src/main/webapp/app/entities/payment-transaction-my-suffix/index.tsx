import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import PaymentTransactionMySuffix from './payment-transaction-my-suffix';
import PaymentTransactionMySuffixDetail from './payment-transaction-my-suffix-detail';
import PaymentTransactionMySuffixUpdate from './payment-transaction-my-suffix-update';
import PaymentTransactionMySuffixDeleteDialog from './payment-transaction-my-suffix-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={PaymentTransactionMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={PaymentTransactionMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={PaymentTransactionMySuffixDetail} />
      <ErrorBoundaryRoute path={match.url} component={PaymentTransactionMySuffix} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={PaymentTransactionMySuffixDeleteDialog} />
  </>
);

export default Routes;
