import { Frequency } from 'app/shared/model/enumerations/frequency.model';

export interface ICustomersubscriptionMySuffix {
  id?: number;
  uniqueID?: string;
  frequency?: Frequency;
  phoneNumberId?: number;
  billerId?: number;
}

export const defaultValue: Readonly<ICustomersubscriptionMySuffix> = {};
