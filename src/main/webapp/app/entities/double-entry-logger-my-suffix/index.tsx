import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import DoubleEntryLoggerMySuffix from './double-entry-logger-my-suffix';
import DoubleEntryLoggerMySuffixDetail from './double-entry-logger-my-suffix-detail';
import DoubleEntryLoggerMySuffixUpdate from './double-entry-logger-my-suffix-update';
import DoubleEntryLoggerMySuffixDeleteDialog from './double-entry-logger-my-suffix-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={DoubleEntryLoggerMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={DoubleEntryLoggerMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={DoubleEntryLoggerMySuffixDetail} />
      <ErrorBoundaryRoute path={match.url} component={DoubleEntryLoggerMySuffix} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={DoubleEntryLoggerMySuffixDeleteDialog} />
  </>
);

export default Routes;
