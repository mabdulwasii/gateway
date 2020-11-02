import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import SchemeCategoryComponentsPage from './scheme-category-my-suffix.page-object';
import SchemeCategoryUpdatePage from './scheme-category-my-suffix-update.page-object';
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

describe('SchemeCategory e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let schemeCategoryComponentsPage: SchemeCategoryComponentsPage;
  let schemeCategoryUpdatePage: SchemeCategoryUpdatePage;

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
    schemeCategoryComponentsPage = new SchemeCategoryComponentsPage();
    schemeCategoryComponentsPage = await schemeCategoryComponentsPage.goToPage(navBarPage);
  });

  it('should load SchemeCategories', async () => {
    expect(await schemeCategoryComponentsPage.title.getText()).to.match(/Scheme Categories/);
    expect(await schemeCategoryComponentsPage.createButton.isEnabled()).to.be.true;
  });

  it('should create and delete SchemeCategories', async () => {
    const beforeRecordsCount = (await isVisible(schemeCategoryComponentsPage.noRecords))
      ? 0
      : await getRecordsCount(schemeCategoryComponentsPage.table);
    schemeCategoryUpdatePage = await schemeCategoryComponentsPage.goToCreateSchemeCategory();
    await schemeCategoryUpdatePage.enterData();

    expect(await schemeCategoryComponentsPage.createButton.isEnabled()).to.be.true;
    await waitUntilDisplayed(schemeCategoryComponentsPage.table);
    await waitUntilCount(schemeCategoryComponentsPage.records, beforeRecordsCount + 1);
    expect(await schemeCategoryComponentsPage.records.count()).to.eq(beforeRecordsCount + 1);

    await schemeCategoryComponentsPage.deleteSchemeCategory();
    if (beforeRecordsCount !== 0) {
      await waitUntilCount(schemeCategoryComponentsPage.records, beforeRecordsCount);
      expect(await schemeCategoryComponentsPage.records.count()).to.eq(beforeRecordsCount);
    } else {
      await waitUntilDisplayed(schemeCategoryComponentsPage.noRecords);
    }
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
