import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import CustomersubscriptionComponentsPage from './customersubscription-my-suffix.page-object';
import CustomersubscriptionUpdatePage from './customersubscription-my-suffix-update.page-object';
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

describe('Customersubscription e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let customersubscriptionComponentsPage: CustomersubscriptionComponentsPage;
  let customersubscriptionUpdatePage: CustomersubscriptionUpdatePage;

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
    customersubscriptionComponentsPage = new CustomersubscriptionComponentsPage();
    customersubscriptionComponentsPage = await customersubscriptionComponentsPage.goToPage(navBarPage);
  });

  it('should load Customersubscriptions', async () => {
    expect(await customersubscriptionComponentsPage.title.getText()).to.match(/Customersubscriptions/);
    expect(await customersubscriptionComponentsPage.createButton.isEnabled()).to.be.true;
  });

  it('should create and delete Customersubscriptions', async () => {
    const beforeRecordsCount = (await isVisible(customersubscriptionComponentsPage.noRecords))
      ? 0
      : await getRecordsCount(customersubscriptionComponentsPage.table);
    customersubscriptionUpdatePage = await customersubscriptionComponentsPage.goToCreateCustomersubscription();
    await customersubscriptionUpdatePage.enterData();

    expect(await customersubscriptionComponentsPage.createButton.isEnabled()).to.be.true;
    await waitUntilDisplayed(customersubscriptionComponentsPage.table);
    await waitUntilCount(customersubscriptionComponentsPage.records, beforeRecordsCount + 1);
    expect(await customersubscriptionComponentsPage.records.count()).to.eq(beforeRecordsCount + 1);

    await customersubscriptionComponentsPage.deleteCustomersubscription();
    if (beforeRecordsCount !== 0) {
      await waitUntilCount(customersubscriptionComponentsPage.records, beforeRecordsCount);
      expect(await customersubscriptionComponentsPage.records.count()).to.eq(beforeRecordsCount);
    } else {
      await waitUntilDisplayed(customersubscriptionComponentsPage.noRecords);
    }
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
