export interface IBillerPlatformMySuffix {
  id?: number;
  billerplatformID?: number;
  billerPlatform?: string;
  amount?: number;
  billerId?: number;
}

export const defaultValue: Readonly<IBillerPlatformMySuffix> = {};
