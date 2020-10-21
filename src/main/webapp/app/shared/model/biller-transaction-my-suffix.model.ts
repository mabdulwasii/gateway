export interface IBillerTransactionMySuffix {
  id?: number;
  billertransID?: number;
  transactionRef?: string;
  narration?: string;
  amount?: number;
  phoneNumberId?: number;
}

export const defaultValue: Readonly<IBillerTransactionMySuffix> = {};
