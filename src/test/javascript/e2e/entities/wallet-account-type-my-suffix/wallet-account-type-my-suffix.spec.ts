import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import WalletAccountTypeComponentsPage from './wallet-account-type-my-suffix.page-object';
import WalletAccountTypeUpdatePage from './wallet-account-type-my-suffix-update.page-object';
import {
  waitUntilDisplayed,
  waitUntilAnyDisplayed,
  click,
  getRecordsCount,
  waitUntilHidden,
  waitUntilCount,
  isVisible,
} from '../../util/utils';

const expect = chai.expect;

describe('WalletAccountType e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let walletAccountTypeComponentsPage: WalletAccountTypeComponentsPage;
  let walletAccountTypeUpdatePage: WalletAccountTypeUpdatePage;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.waitUntilDisplayed();

    await signInPage.username.sendKeys('admin');
    await signInPage.password.sendKeys('admin');
    await signInPage.loginButton.click();
    await signInPage.waitUntilHidden();
    await waitUntilDisplayed(navBarPage.entityMenu);
    await waitUntilDisplayed(navBarPage.adminMenu);
    await waitUntilDisplayed(navBarPage.accountMenu);
  });

  beforeEach(async () => {
    await browser.get('/');
    await waitUntilDisplayed(navBarPage.entityMenu);
    walletAccountTypeComponentsPage = new WalletAccountTypeComponentsPage();
    walletAccountTypeComponentsPage = await walletAccountTypeComponentsPage.goToPage(navBarPage);
  });

  it('should load WalletAccountTypes', async () => {
    expect(await walletAccountTypeComponentsPage.title.getText()).to.match(/Wallet Account Types/);
    expect(await walletAccountTypeComponentsPage.createButton.isEnabled()).to.be.true;
  });

  it('should create and delete WalletAccountTypes', async () => {
    const beforeRecordsCount = (await isVisible(walletAccountTypeComponentsPage.noRecords))
      ? 0
      : await getRecordsCount(walletAccountTypeComponentsPage.table);
    walletAccountTypeUpdatePage = await walletAccountTypeComponentsPage.goToCreateWalletAccountType();
    await walletAccountTypeUpdatePage.enterData();

    expect(await walletAccountTypeComponentsPage.createButton.isEnabled()).to.be.true;
    await waitUntilDisplayed(walletAccountTypeComponentsPage.table);
    await waitUntilCount(walletAccountTypeComponentsPage.records, beforeRecordsCount + 1);
    expect(await walletAccountTypeComponentsPage.records.count()).to.eq(beforeRecordsCount + 1);

    await walletAccountTypeComponentsPage.deleteWalletAccountType();
    if (beforeRecordsCount !== 0) {
      await waitUntilCount(walletAccountTypeComponentsPage.records, beforeRecordsCount);
      expect(await walletAccountTypeComponentsPage.records.count()).to.eq(beforeRecordsCount);
    } else {
      await waitUntilDisplayed(walletAccountTypeComponentsPage.noRecords);
    }
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
