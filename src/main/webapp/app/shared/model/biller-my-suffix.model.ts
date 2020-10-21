import { ICustomersubscriptionMySuffix } from 'app/shared/model/customersubscription-my-suffix.model';
import { IBillerPlatformMySuffix } from 'app/shared/model/biller-platform-my-suffix.model';

export interface IBillerMySuffix {
  id?: number;
  billerID?: number;
  biller?: string;
  address?: string;
  phoneNumber?: string;
  customersubscriptions?: ICustomersubscriptionMySuffix[];
  billerPlatforms?: IBillerPlatformMySuffix[];
  billerCategoryId?: number;
}

export const defaultValue: Readonly<IBillerMySuffix> = {};
