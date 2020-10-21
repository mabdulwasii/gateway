import { combineReducers } from 'redux';
import { loadingBarReducer as loadingBar } from 'react-redux-loading-bar';

import locale, { LocaleState } from './locale';
import authentication, { AuthenticationState } from './authentication';
import applicationProfile, { ApplicationProfileState } from './application-profile';

import administration, { AdministrationState } from 'app/modules/administration/administration.reducer';
import userManagement, { UserManagementState } from 'app/modules/administration/user-management/user-management.reducer';
import register, { RegisterState } from 'app/modules/account/register/register.reducer';
import activate, { ActivateState } from 'app/modules/account/activate/activate.reducer';
import password, { PasswordState } from 'app/modules/account/password/password.reducer';
import settings, { SettingsState } from 'app/modules/account/settings/settings.reducer';
import passwordReset, { PasswordResetState } from 'app/modules/account/password-reset/password-reset.reducer';
// prettier-ignore
import profile, {
  ProfileMySuffixState
} from 'app/entities/profile-my-suffix/profile-my-suffix.reducer';
// prettier-ignore
import address, {
  AddressMySuffixState
} from 'app/entities/address-my-suffix/address-my-suffix.reducer';
// prettier-ignore
import profileType, {
  ProfileTypeMySuffixState
} from 'app/entities/profile-type-my-suffix/profile-type-my-suffix.reducer';
// prettier-ignore
import kyclevel, {
  KyclevelMySuffixState
} from 'app/entities/kyclevel-my-suffix/kyclevel-my-suffix.reducer';
// prettier-ignore
import biller, {
  BillerMySuffixState
} from 'app/entities/biller-my-suffix/biller-my-suffix.reducer';
// prettier-ignore
import walletAccount, {
  WalletAccountMySuffixState
} from 'app/entities/wallet-account-my-suffix/wallet-account-my-suffix.reducer';
// prettier-ignore
import scheme, {
  SchemeMySuffixState
} from 'app/entities/scheme-my-suffix/scheme-my-suffix.reducer';
// prettier-ignore
import schemeCategory, {
  SchemeCategoryMySuffixState
} from 'app/entities/scheme-category-my-suffix/scheme-category-my-suffix.reducer';
// prettier-ignore
import paymentTransaction, {
  PaymentTransactionMySuffixState
} from 'app/entities/payment-transaction-my-suffix/payment-transaction-my-suffix.reducer';
// prettier-ignore
import billerTransaction, {
  BillerTransactionMySuffixState
} from 'app/entities/biller-transaction-my-suffix/biller-transaction-my-suffix.reducer';
// prettier-ignore
import customersubscription, {
  CustomersubscriptionMySuffixState
} from 'app/entities/customersubscription-my-suffix/customersubscription-my-suffix.reducer';
// prettier-ignore
import billerPlatform, {
  BillerPlatformMySuffixState
} from 'app/entities/biller-platform-my-suffix/biller-platform-my-suffix.reducer';
// prettier-ignore
import billerCategory, {
  BillerCategoryMySuffixState
} from 'app/entities/biller-category-my-suffix/biller-category-my-suffix.reducer';
// prettier-ignore
import walletAccountType, {
  WalletAccountTypeMySuffixState
} from 'app/entities/wallet-account-type-my-suffix/wallet-account-type-my-suffix.reducer';
// prettier-ignore
import countrolAccount, {
  CountrolAccountMySuffixState
} from 'app/entities/countrol-account-my-suffix/countrol-account-my-suffix.reducer';
// prettier-ignore
import doubleEntryLogger, {
  DoubleEntryLoggerMySuffixState
} from 'app/entities/double-entry-logger-my-suffix/double-entry-logger-my-suffix.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

export interface IRootState {
  readonly authentication: AuthenticationState;
  readonly locale: LocaleState;
  readonly applicationProfile: ApplicationProfileState;
  readonly administration: AdministrationState;
  readonly userManagement: UserManagementState;
  readonly register: RegisterState;
  readonly activate: ActivateState;
  readonly passwordReset: PasswordResetState;
  readonly password: PasswordState;
  readonly settings: SettingsState;
  readonly profile: ProfileMySuffixState;
  readonly address: AddressMySuffixState;
  readonly profileType: ProfileTypeMySuffixState;
  readonly kyclevel: KyclevelMySuffixState;
  readonly biller: BillerMySuffixState;
  readonly walletAccount: WalletAccountMySuffixState;
  readonly scheme: SchemeMySuffixState;
  readonly schemeCategory: SchemeCategoryMySuffixState;
  readonly paymentTransaction: PaymentTransactionMySuffixState;
  readonly billerTransaction: BillerTransactionMySuffixState;
  readonly customersubscription: CustomersubscriptionMySuffixState;
  readonly billerPlatform: BillerPlatformMySuffixState;
  readonly billerCategory: BillerCategoryMySuffixState;
  readonly walletAccountType: WalletAccountTypeMySuffixState;
  readonly countrolAccount: CountrolAccountMySuffixState;
  readonly doubleEntryLogger: DoubleEntryLoggerMySuffixState;
  /* jhipster-needle-add-reducer-type - JHipster will add reducer type here */
  readonly loadingBar: any;
}

const rootReducer = combineReducers<IRootState>({
  authentication,
  locale,
  applicationProfile,
  administration,
  userManagement,
  register,
  activate,
  passwordReset,
  password,
  settings,
  profile,
  address,
  profileType,
  kyclevel,
  biller,
  walletAccount,
  scheme,
  schemeCategory,
  paymentTransaction,
  billerTransaction,
  customersubscription,
  billerPlatform,
  billerCategory,
  walletAccountType,
  countrolAccount,
  doubleEntryLogger,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
  loadingBar,
});

export default rootReducer;
