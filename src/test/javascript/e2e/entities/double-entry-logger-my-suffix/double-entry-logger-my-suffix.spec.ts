import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import DoubleEntryLoggerComponentsPage from './double-entry-logger-my-suffix.page-object';
import DoubleEntryLoggerUpdatePage from './double-entry-logger-my-suffix-update.page-object';
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

describe('DoubleEntryLogger e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let doubleEntryLoggerComponentsPage: DoubleEntryLoggerComponentsPage;
  let doubleEntryLoggerUpdatePage: DoubleEntryLoggerUpdatePage;

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
    doubleEntryLoggerComponentsPage = new DoubleEntryLoggerComponentsPage();
    doubleEntryLoggerComponentsPage = await doubleEntryLoggerComponentsPage.goToPage(navBarPage);
  });

  it('should load DoubleEntryLoggers', async () => {
    expect(await doubleEntryLoggerComponentsPage.title.getText()).to.match(/Double Entry Loggers/);
    expect(await doubleEntryLoggerComponentsPage.createButton.isEnabled()).to.be.true;
  });

  it('should create and delete DoubleEntryLoggers', async () => {
    const beforeRecordsCount = (await isVisible(doubleEntryLoggerComponentsPage.noRecords))
      ? 0
      : await getRecordsCount(doubleEntryLoggerComponentsPage.table);
    doubleEntryLoggerUpdatePage = await doubleEntryLoggerComponentsPage.goToCreateDoubleEntryLogger();
    await doubleEntryLoggerUpdatePage.enterData();

    expect(await doubleEntryLoggerComponentsPage.createButton.isEnabled()).to.be.true;
    await waitUntilDisplayed(doubleEntryLoggerComponentsPage.table);
    await waitUntilCount(doubleEntryLoggerComponentsPage.records, beforeRecordsCount + 1);
    expect(await doubleEntryLoggerComponentsPage.records.count()).to.eq(beforeRecordsCount + 1);

    await doubleEntryLoggerComponentsPage.deleteDoubleEntryLogger();
    if (beforeRecordsCount !== 0) {
      await waitUntilCount(doubleEntryLoggerComponentsPage.records, beforeRecordsCount);
      expect(await doubleEntryLoggerComponentsPage.records.count()).to.eq(beforeRecordsCount);
    } else {
      await waitUntilDisplayed(doubleEntryLoggerComponentsPage.noRecords);
    }
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
