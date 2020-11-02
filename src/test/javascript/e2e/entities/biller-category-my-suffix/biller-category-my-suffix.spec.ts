import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import BillerCategoryComponentsPage from './biller-category-my-suffix.page-object';
import BillerCategoryUpdatePage from './biller-category-my-suffix-update.page-object';
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

describe('BillerCategory e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let billerCategoryComponentsPage: BillerCategoryComponentsPage;
  let billerCategoryUpdatePage: BillerCategoryUpdatePage;

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
    billerCategoryComponentsPage = new BillerCategoryComponentsPage();
    billerCategoryComponentsPage = await billerCategoryComponentsPage.goToPage(navBarPage);
  });

  it('should load BillerCategories', async () => {
    expect(await billerCategoryComponentsPage.title.getText()).to.match(/Biller Categories/);
    expect(await billerCategoryComponentsPage.createButton.isEnabled()).to.be.true;
  });

  it('should create and delete BillerCategories', async () => {
    const beforeRecordsCount = (await isVisible(billerCategoryComponentsPage.noRecords))
      ? 0
      : await getRecordsCount(billerCategoryComponentsPage.table);
    billerCategoryUpdatePage = await billerCategoryComponentsPage.goToCreateBillerCategory();
    await billerCategoryUpdatePage.enterData();

    expect(await billerCategoryComponentsPage.createButton.isEnabled()).to.be.true;
    await waitUntilDisplayed(billerCategoryComponentsPage.table);
    await waitUntilCount(billerCategoryComponentsPage.records, beforeRecordsCount + 1);
    expect(await billerCategoryComponentsPage.records.count()).to.eq(beforeRecordsCount + 1);

    await billerCategoryComponentsPage.deleteBillerCategory();
    if (beforeRecordsCount !== 0) {
      await waitUntilCount(billerCategoryComponentsPage.records, beforeRecordsCount);
      expect(await billerCategoryComponentsPage.records.count()).to.eq(beforeRecordsCount);
    } else {
      await waitUntilDisplayed(billerCategoryComponentsPage.noRecords);
    }
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
