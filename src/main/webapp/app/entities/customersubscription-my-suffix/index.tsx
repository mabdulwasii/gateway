import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import CustomersubscriptionMySuffix from './customersubscription-my-suffix';
import CustomersubscriptionMySuffixDetail from './customersubscription-my-suffix-detail';
import CustomersubscriptionMySuffixUpdate from './customersubscription-my-suffix-update';
import CustomersubscriptionMySuffixDeleteDialog from './customersubscription-my-suffix-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={CustomersubscriptionMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={CustomersubscriptionMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={CustomersubscriptionMySuffixDetail} />
      <ErrorBoundaryRoute path={match.url} component={CustomersubscriptionMySuffix} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={CustomersubscriptionMySuffixDeleteDialog} />
  </>
);

export default Routes;
