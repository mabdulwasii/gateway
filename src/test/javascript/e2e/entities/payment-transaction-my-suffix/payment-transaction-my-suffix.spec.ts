import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import PaymentTransactionComponentsPage from './payment-transaction-my-suffix.page-object';
import PaymentTransactionUpdatePage from './payment-transaction-my-suffix-update.page-object';
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

describe('PaymentTransaction e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let paymentTransactionComponentsPage: PaymentTransactionComponentsPage;
  let paymentTransactionUpdatePage: PaymentTransactionUpdatePage;

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
    paymentTransactionComponentsPage = new PaymentTransactionComponentsPage();
    paymentTransactionComponentsPage = await paymentTransactionComponentsPage.goToPage(navBarPage);
  });

  it('should load PaymentTransactions', async () => {
    expect(await paymentTransactionComponentsPage.title.getText()).to.match(/Payment Transactions/);
    expect(await paymentTransactionComponentsPage.createButton.isEnabled()).to.be.true;
  });

  it('should create and delete PaymentTransactions', async () => {
    const beforeRecordsCount = (await isVisible(paymentTransactionComponentsPage.noRecords))
      ? 0
      : await getRecordsCount(paymentTransactionComponentsPage.table);
    paymentTransactionUpdatePage = await paymentTransactionComponentsPage.goToCreatePaymentTransaction();
    await paymentTransactionUpdatePage.enterData();

    expect(await paymentTransactionComponentsPage.createButton.isEnabled()).to.be.true;
    await waitUntilDisplayed(paymentTransactionComponentsPage.table);
    await waitUntilCount(paymentTransactionComponentsPage.records, beforeRecordsCount + 1);
    expect(await paymentTransactionComponentsPage.records.count()).to.eq(beforeRecordsCount + 1);

    await paymentTransactionComponentsPage.deletePaymentTransaction();
    if (beforeRecordsCount !== 0) {
      await waitUntilCount(paymentTransactionComponentsPage.records, beforeRecordsCount);
      expect(await paymentTransactionComponentsPage.records.count()).to.eq(beforeRecordsCount);
    } else {
      await waitUntilDisplayed(paymentTransactionComponentsPage.noRecords);
    }
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
