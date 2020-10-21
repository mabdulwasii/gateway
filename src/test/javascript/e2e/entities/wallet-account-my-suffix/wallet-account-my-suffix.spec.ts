import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import WalletAccountComponentsPage from './wallet-account-my-suffix.page-object';
import WalletAccountUpdatePage from './wallet-account-my-suffix-update.page-object';
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

describe('WalletAccount e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let walletAccountComponentsPage: WalletAccountComponentsPage;
  let walletAccountUpdatePage: WalletAccountUpdatePage;

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
    walletAccountComponentsPage = new WalletAccountComponentsPage();
    walletAccountComponentsPage = await walletAccountComponentsPage.goToPage(navBarPage);
  });

  it('should load WalletAccounts', async () => {
    expect(await walletAccountComponentsPage.title.getText()).to.match(/Wallet Accounts/);
    expect(await walletAccountComponentsPage.createButton.isEnabled()).to.be.true;
  });

  it('should create and delete WalletAccounts', async () => {
    const beforeRecordsCount = (await isVisible(walletAccountComponentsPage.noRecords))
      ? 0
      : await getRecordsCount(walletAccountComponentsPage.table);
    walletAccountUpdatePage = await walletAccountComponentsPage.goToCreateWalletAccount();
    await walletAccountUpdatePage.enterData();

    expect(await walletAccountComponentsPage.createButton.isEnabled()).to.be.true;
    await waitUntilDisplayed(walletAccountComponentsPage.table);
    await waitUntilCount(walletAccountComponentsPage.records, beforeRecordsCount + 1);
    expect(await walletAccountComponentsPage.records.count()).to.eq(beforeRecordsCount + 1);

    await walletAccountComponentsPage.deleteWalletAccount();
    if (beforeRecordsCount !== 0) {
      await waitUntilCount(walletAccountComponentsPage.records, beforeRecordsCount);
      expect(await walletAccountComponentsPage.records.count()).to.eq(beforeRecordsCount);
    } else {
      await waitUntilDisplayed(walletAccountComponentsPage.noRecords);
    }
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
