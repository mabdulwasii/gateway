import { Moment } from 'moment';
import { IWalletAccountMySuffix } from 'app/shared/model/wallet-account-my-suffix.model';
import { IPaymentTransactionMySuffix } from 'app/shared/model/payment-transaction-my-suffix.model';
import { IBillerTransactionMySuffix } from 'app/shared/model/biller-transaction-my-suffix.model';
import { ICustomersubscriptionMySuffix } from 'app/shared/model/customersubscription-my-suffix.model';
import { Gender } from 'app/shared/model/enumerations/gender.model';

export interface IProfileMySuffix {
  id?: number;
  profileID?: string;
  phoneNumber?: string;
  gender?: Gender;
  dateOfBirth?: string;
  address?: string;
  photoContentType?: string;
  photo?: any;
  bvn?: string;
  validID?: string;
  walletAccounts?: IWalletAccountMySuffix[];
  paymentTransactions?: IPaymentTransactionMySuffix[];
  billerTransactions?: IBillerTransactionMySuffix[];
  customersubscriptions?: ICustomersubscriptionMySuffix[];
  userLogin?: string;
  userId?: number;
  profileTypeId?: number;
  kycId?: number;
}

export const defaultValue: Readonly<IProfileMySuffix> = {};
