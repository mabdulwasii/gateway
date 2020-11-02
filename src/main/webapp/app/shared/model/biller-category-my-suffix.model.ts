import { IBillerMySuffix } from 'app/shared/model/biller-my-suffix.model';

export interface IBillerCategoryMySuffix {
  id?: number;
  billercategoryID?: number;
  billerCategory?: string;
  billers?: IBillerMySuffix[];
}

export const defaultValue: Readonly<IBillerCategoryMySuffix> = {};
