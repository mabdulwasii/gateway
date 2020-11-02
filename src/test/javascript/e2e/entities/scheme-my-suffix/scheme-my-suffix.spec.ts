import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import SchemeComponentsPage from './scheme-my-suffix.page-object';
import SchemeUpdatePage from './scheme-my-suffix-update.page-object';
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

describe('Scheme e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let schemeComponentsPage: SchemeComponentsPage;
  let schemeUpdatePage: SchemeUpdatePage;

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
    schemeComponentsPage = new SchemeComponentsPage();
    schemeComponentsPage = await schemeComponentsPage.goToPage(navBarPage);
  });

  it('should load Schemes', async () => {
    expect(await schemeComponentsPage.title.getText()).to.match(/Schemes/);
    expect(await schemeComponentsPage.createButton.isEnabled()).to.be.true;
  });

  it('should create and delete Schemes', async () => {
    const beforeRecordsCount = (await isVisible(schemeComponentsPage.noRecords)) ? 0 : await getRecordsCount(schemeComponentsPage.table);
    schemeUpdatePage = await schemeComponentsPage.goToCreateScheme();
    await schemeUpdatePage.enterData();

    expect(await schemeComponentsPage.createButton.isEnabled()).to.be.true;
    await waitUntilDisplayed(schemeComponentsPage.table);
    await waitUntilCount(schemeComponentsPage.records, beforeRecordsCount + 1);
    expect(await schemeComponentsPage.records.count()).to.eq(beforeRecordsCount + 1);

    await schemeComponentsPage.deleteScheme();
    if (beforeRecordsCount !== 0) {
      await waitUntilCount(schemeComponentsPage.records, beforeRecordsCount);
      expect(await schemeComponentsPage.records.count()).to.eq(beforeRecordsCount);
    } else {
      await waitUntilDisplayed(schemeComponentsPage.noRecords);
    }
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
