import { TransactionType } from 'app/shared/model/enumerations/transaction-type.model';

export interface IPaymentTransactionMySuffix {
  id?: number;
  paymenttransID?: number;
  transactionType?: TransactionType;
  transactionRef?: string;
  amount?: number;
  channel?: string;
  currency?: string;
  sourceAccount?: string;
  sourceAccountBankCode?: string;
  sourceAccountName?: string;
  sourceNarration?: string;
  destinationAccount?: string;
  destinationAccountBankCode?: string;
  destinationAccountName?: string;
  destinationNarration?: string;
  transactionOwnerPhoneNumber?: string;
  transactionOwnerId?: number;
}

export const defaultValue: Readonly<IPaymentTransactionMySuffix> = {};
