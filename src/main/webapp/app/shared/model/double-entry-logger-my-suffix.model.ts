import { Moment } from 'moment';

export interface IDoubleEntryLoggerMySuffix {
  id?: number;
  dateEntered?: string;
  doubleEntryCode?: string;
  amount?: number;
  narration?: string;
  debitCountrolAccountCode?: string;
  debitId?: number;
  creditCountrolAccountCode?: string;
  creditId?: number;
}

export const defaultValue: Readonly<IDoubleEntryLoggerMySuffix> = {};
