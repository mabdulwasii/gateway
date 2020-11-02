export interface IAddressMySuffix {
  id?: number;
  state?: string;
  localGovt?: string;
  latitude?: number;
  longitude?: number;
  address?: string;
  addressOwnerPhoneNumber?: string;
  addressOwnerId?: number;
}

export const defaultValue: Readonly<IAddressMySuffix> = {};
