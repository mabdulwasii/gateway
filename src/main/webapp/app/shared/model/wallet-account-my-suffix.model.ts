import { Moment } from 'moment';

export interface IWalletAccountMySuffix {
  id?: number;
  accountNumber?: number;
  currentBalance?: number;
  dateOpened?: string;
  schemeId?: number;
  walletAccountTypeId?: number;
  accountOwnerPhoneNumber?: string;
  accountOwnerId?: number;
}

export const defaultValue: Readonly<IWalletAccountMySuffix> = {};
