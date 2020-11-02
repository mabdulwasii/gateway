import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import BillerTransactionComponentsPage from './biller-transaction-my-suffix.page-object';
import BillerTransactionUpdatePage from './biller-transaction-my-suffix-update.page-object';
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

describe('BillerTransaction e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let billerTransactionComponentsPage: BillerTransactionComponentsPage;
  let billerTransactionUpdatePage: BillerTransactionUpdatePage;

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
    billerTransactionComponentsPage = new BillerTransactionComponentsPage();
    billerTransactionComponentsPage = await billerTransactionComponentsPage.goToPage(navBarPage);
  });

  it('should load BillerTransactions', async () => {
    expect(await billerTransactionComponentsPage.title.getText()).to.match(/Biller Transactions/);
    expect(await billerTransactionComponentsPage.createButton.isEnabled()).to.be.true;
  });

  it('should create and delete BillerTransactions', async () => {
    const beforeRecordsCount = (await isVisible(billerTransactionComponentsPage.noRecords))
      ? 0
      : await getRecordsCount(billerTransactionComponentsPage.table);
    billerTransactionUpdatePage = await billerTransactionComponentsPage.goToCreateBillerTransaction();
    await billerTransactionUpdatePage.enterData();

    expect(await billerTransactionComponentsPage.createButton.isEnabled()).to.be.true;
    await waitUntilDisplayed(billerTransactionComponentsPage.table);
    await waitUntilCount(billerTransactionComponentsPage.records, beforeRecordsCount + 1);
    expect(await billerTransactionComponentsPage.records.count()).to.eq(beforeRecordsCount + 1);

    await billerTransactionComponentsPage.deleteBillerTransaction();
    if (beforeRecordsCount !== 0) {
      await waitUntilCount(billerTransactionComponentsPage.records, beforeRecordsCount);
      expect(await billerTransactionComponentsPage.records.count()).to.eq(beforeRecordsCount);
    } else {
      await waitUntilDisplayed(billerTransactionComponentsPage.noRecords);
    }
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
