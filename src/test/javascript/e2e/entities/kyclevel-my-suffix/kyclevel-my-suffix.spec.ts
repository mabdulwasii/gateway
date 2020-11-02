import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import KyclevelComponentsPage from './kyclevel-my-suffix.page-object';
import KyclevelUpdatePage from './kyclevel-my-suffix-update.page-object';
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

describe('Kyclevel e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let kyclevelComponentsPage: KyclevelComponentsPage;
  let kyclevelUpdatePage: KyclevelUpdatePage;

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
    kyclevelComponentsPage = new KyclevelComponentsPage();
    kyclevelComponentsPage = await kyclevelComponentsPage.goToPage(navBarPage);
  });

  it('should load Kyclevels', async () => {
    expect(await kyclevelComponentsPage.title.getText()).to.match(/Kyclevels/);
    expect(await kyclevelComponentsPage.createButton.isEnabled()).to.be.true;
  });

  it('should create and delete Kyclevels', async () => {
    const beforeRecordsCount = (await isVisible(kyclevelComponentsPage.noRecords))
      ? 0
      : await getRecordsCount(kyclevelComponentsPage.table);
    kyclevelUpdatePage = await kyclevelComponentsPage.goToCreateKyclevel();
    await kyclevelUpdatePage.enterData();

    expect(await kyclevelComponentsPage.createButton.isEnabled()).to.be.true;
    await waitUntilDisplayed(kyclevelComponentsPage.table);
    await waitUntilCount(kyclevelComponentsPage.records, beforeRecordsCount + 1);
    expect(await kyclevelComponentsPage.records.count()).to.eq(beforeRecordsCount + 1);

    await kyclevelComponentsPage.deleteKyclevel();
    if (beforeRecordsCount !== 0) {
      await waitUntilCount(kyclevelComponentsPage.records, beforeRecordsCount);
      expect(await kyclevelComponentsPage.records.count()).to.eq(beforeRecordsCount);
    } else {
      await waitUntilDisplayed(kyclevelComponentsPage.noRecords);
    }
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
