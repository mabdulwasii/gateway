import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import CountrolAccountComponentsPage from './countrol-account-my-suffix.page-object';
import CountrolAccountUpdatePage from './countrol-account-my-suffix-update.page-object';
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

describe('CountrolAccount e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let countrolAccountComponentsPage: CountrolAccountComponentsPage;
  let countrolAccountUpdatePage: CountrolAccountUpdatePage;

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
    countrolAccountComponentsPage = new CountrolAccountComponentsPage();
    countrolAccountComponentsPage = await countrolAccountComponentsPage.goToPage(navBarPage);
  });

  it('should load CountrolAccounts', async () => {
    expect(await countrolAccountComponentsPage.title.getText()).to.match(/Countrol Accounts/);
    expect(await countrolAccountComponentsPage.createButton.isEnabled()).to.be.true;
  });

  it('should create and delete CountrolAccounts', async () => {
    const beforeRecordsCount = (await isVisible(countrolAccountComponentsPage.noRecords))
      ? 0
      : await getRecordsCount(countrolAccountComponentsPage.table);
    countrolAccountUpdatePage = await countrolAccountComponentsPage.goToCreateCountrolAccount();
    await countrolAccountUpdatePage.enterData();

    expect(await countrolAccountComponentsPage.createButton.isEnabled()).to.be.true;
    await waitUntilDisplayed(countrolAccountComponentsPage.table);
    await waitUntilCount(countrolAccountComponentsPage.records, beforeRecordsCount + 1);
    expect(await countrolAccountComponentsPage.records.count()).to.eq(beforeRecordsCount + 1);

    await countrolAccountComponentsPage.deleteCountrolAccount();
    if (beforeRecordsCount !== 0) {
      await waitUntilCount(countrolAccountComponentsPage.records, beforeRecordsCount);
      expect(await countrolAccountComponentsPage.records.count()).to.eq(beforeRecordsCount);
    } else {
      await waitUntilDisplayed(countrolAccountComponentsPage.noRecords);
    }
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
