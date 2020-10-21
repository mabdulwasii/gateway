export interface IKyclevelMySuffix {
  id?: number;
  kycID?: number;
  kyc?: string;
  description?: string;
  kycLevel?: number;
  phoneNumber?: boolean;
  emailAddress?: boolean;
  firstName?: boolean;
  lastName?: boolean;
  gender?: boolean;
  dateofBirth?: boolean;
  address?: boolean;
  photoUpload?: boolean;
  verifiedBVN?: boolean;
  verifiedValidID?: boolean;
  evidenceofAddress?: boolean;
  verificationofAddress?: boolean;
  employmentDetails?: boolean;
  dailyTransactionLimit?: number;
  cumulativeBalanceLimit?: number;
  paymentTransaction?: boolean;
  billerTransaction?: boolean;
}

export const defaultValue: Readonly<IKyclevelMySuffix> = {
  phoneNumber: false,
  emailAddress: false,
  firstName: false,
  lastName: false,
  gender: false,
  dateofBirth: false,
  address: false,
  photoUpload: false,
  verifiedBVN: false,
  verifiedValidID: false,
  evidenceofAddress: false,
  verificationofAddress: false,
  employmentDetails: false,
  paymentTransaction: false,
  billerTransaction: false,
};
