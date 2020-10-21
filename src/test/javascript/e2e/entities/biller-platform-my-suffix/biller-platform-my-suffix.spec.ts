import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import BillerPlatformComponentsPage from './biller-platform-my-suffix.page-object';
import BillerPlatformUpdatePage from './biller-platform-my-suffix-update.page-object';
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

describe('BillerPlatform e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let billerPlatformComponentsPage: BillerPlatformComponentsPage;
  let billerPlatformUpdatePage: BillerPlatformUpdatePage;

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
    billerPlatformComponentsPage = new BillerPlatformComponentsPage();
    billerPlatformComponentsPage = await billerPlatformComponentsPage.goToPage(navBarPage);
  });

  it('should load BillerPlatforms', async () => {
    expect(await billerPlatformComponentsPage.title.getText()).to.match(/Biller Platforms/);
    expect(await billerPlatformComponentsPage.createButton.isEnabled()).to.be.true;
  });

  it('should create and delete BillerPlatforms', async () => {
    const beforeRecordsCount = (await isVisible(billerPlatformComponentsPage.noRecords))
      ? 0
      : await getRecordsCount(billerPlatformComponentsPage.table);
    billerPlatformUpdatePage = await billerPlatformComponentsPage.goToCreateBillerPlatform();
    await billerPlatformUpdatePage.enterData();

    expect(await billerPlatformComponentsPage.createButton.isEnabled()).to.be.true;
    await waitUntilDisplayed(billerPlatformComponentsPage.table);
    await waitUntilCount(billerPlatformComponentsPage.records, beforeRecordsCount + 1);
    expect(await billerPlatformComponentsPage.records.count()).to.eq(beforeRecordsCount + 1);

    await billerPlatformComponentsPage.deleteBillerPlatform();
    if (beforeRecordsCount !== 0) {
      await waitUntilCount(billerPlatformComponentsPage.records, beforeRecordsCount);
      expect(await billerPlatformComponentsPage.records.count()).to.eq(beforeRecordsCount);
    } else {
      await waitUntilDisplayed(billerPlatformComponentsPage.noRecords);
    }
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
