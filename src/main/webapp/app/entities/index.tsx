import React from 'react';
import { Switch } from 'react-router-dom';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ProfileMySuffix from './profile-my-suffix';
import AddressMySuffix from './address-my-suffix';
import ProfileTypeMySuffix from './profile-type-my-suffix';
import KyclevelMySuffix from './kyclevel-my-suffix';
import BillerMySuffix from './biller-my-suffix';
import WalletAccountMySuffix from './wallet-account-my-suffix';
import SchemeMySuffix from './scheme-my-suffix';
import SchemeCategoryMySuffix from './scheme-category-my-suffix';
import PaymentTransactionMySuffix from './payment-transaction-my-suffix';
import BillerTransactionMySuffix from './biller-transaction-my-suffix';
import CustomersubscriptionMySuffix from './customersubscription-my-suffix';
import BillerPlatformMySuffix from './biller-platform-my-suffix';
import BillerCategoryMySuffix from './biller-category-my-suffix';
import WalletAccountTypeMySuffix from './wallet-account-type-my-suffix';
import CountrolAccountMySuffix from './countrol-account-my-suffix';
import DoubleEntryLoggerMySuffix from './double-entry-logger-my-suffix';
/* jhipster-needle-add-route-import - JHipster will add routes here */

const Routes = ({ match }) => (
  <div>
    <Switch>
      {/* prettier-ignore */}
      <ErrorBoundaryRoute path={`${match.url}profile-my-suffix`} component={ProfileMySuffix} />
      <ErrorBoundaryRoute path={`${match.url}address-my-suffix`} component={AddressMySuffix} />
      <ErrorBoundaryRoute path={`${match.url}profile-type-my-suffix`} component={ProfileTypeMySuffix} />
      <ErrorBoundaryRoute path={`${match.url}kyclevel-my-suffix`} component={KyclevelMySuffix} />
      <ErrorBoundaryRoute path={`${match.url}biller-my-suffix`} component={BillerMySuffix} />
      <ErrorBoundaryRoute path={`${match.url}wallet-account-my-suffix`} component={WalletAccountMySuffix} />
      <ErrorBoundaryRoute path={`${match.url}scheme-my-suffix`} component={SchemeMySuffix} />
      <ErrorBoundaryRoute path={`${match.url}scheme-category-my-suffix`} component={SchemeCategoryMySuffix} />
      <ErrorBoundaryRoute path={`${match.url}payment-transaction-my-suffix`} component={PaymentTransactionMySuffix} />
      <ErrorBoundaryRoute path={`${match.url}biller-transaction-my-suffix`} component={BillerTransactionMySuffix} />
      <ErrorBoundaryRoute path={`${match.url}customersubscription-my-suffix`} component={CustomersubscriptionMySuffix} />
      <ErrorBoundaryRoute path={`${match.url}biller-platform-my-suffix`} component={BillerPlatformMySuffix} />
      <ErrorBoundaryRoute path={`${match.url}biller-category-my-suffix`} component={BillerCategoryMySuffix} />
      <ErrorBoundaryRoute path={`${match.url}wallet-account-type-my-suffix`} component={WalletAccountTypeMySuffix} />
      <ErrorBoundaryRoute path={`${match.url}countrol-account-my-suffix`} component={CountrolAccountMySuffix} />
      <ErrorBoundaryRoute path={`${match.url}double-entry-logger-my-suffix`} component={DoubleEntryLoggerMySuffix} />
      {/* jhipster-needle-add-route-path - JHipster will add routes here */}
    </Switch>
  </div>
);

export default Routes;
