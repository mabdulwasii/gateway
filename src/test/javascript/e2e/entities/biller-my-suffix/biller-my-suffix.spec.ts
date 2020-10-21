import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import BillerComponentsPage from './biller-my-suffix.page-object';
import BillerUpdatePage from './biller-my-suffix-update.page-object';
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

describe('Biller e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let billerComponentsPage: BillerComponentsPage;
  let billerUpdatePage: BillerUpdatePage;

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
    billerComponentsPage = new BillerComponentsPage();
    billerComponentsPage = await billerComponentsPage.goToPage(navBarPage);
  });

  it('should load Billers', async () => {
    expect(await billerComponentsPage.title.getText()).to.match(/Billers/);
    expect(await billerComponentsPage.createButton.isEnabled()).to.be.true;
  });

  it('should create and delete Billers', async () => {
    const beforeRecordsCount = (await isVisible(billerComponentsPage.noRecords)) ? 0 : await getRecordsCount(billerComponentsPage.table);
    billerUpdatePage = await billerComponentsPage.goToCreateBiller();
    await billerUpdatePage.enterData();

    expect(await billerComponentsPage.createButton.isEnabled()).to.be.true;
    await waitUntilDisplayed(billerComponentsPage.table);
    await waitUntilCount(billerComponentsPage.records, beforeRecordsCount + 1);
    expect(await billerComponentsPage.records.count()).to.eq(beforeRecordsCount + 1);

    await billerComponentsPage.deleteBiller();
    if (beforeRecordsCount !== 0) {
      await waitUntilCount(billerComponentsPage.records, beforeRecordsCount);
      expect(await billerComponentsPage.records.count()).to.eq(beforeRecordsCount);
    } else {
      await waitUntilDisplayed(billerComponentsPage.noRecords);
    }
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
