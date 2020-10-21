import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import ProfileTypeComponentsPage from './profile-type-my-suffix.page-object';
import ProfileTypeUpdatePage from './profile-type-my-suffix-update.page-object';
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

describe('ProfileType e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let profileTypeComponentsPage: ProfileTypeComponentsPage;
  let profileTypeUpdatePage: ProfileTypeUpdatePage;

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
    profileTypeComponentsPage = new ProfileTypeComponentsPage();
    profileTypeComponentsPage = await profileTypeComponentsPage.goToPage(navBarPage);
  });

  it('should load ProfileTypes', async () => {
    expect(await profileTypeComponentsPage.title.getText()).to.match(/Profile Types/);
    expect(await profileTypeComponentsPage.createButton.isEnabled()).to.be.true;
  });

  it('should create and delete ProfileTypes', async () => {
    const beforeRecordsCount = (await isVisible(profileTypeComponentsPage.noRecords))
      ? 0
      : await getRecordsCount(profileTypeComponentsPage.table);
    profileTypeUpdatePage = await profileTypeComponentsPage.goToCreateProfileType();
    await profileTypeUpdatePage.enterData();

    expect(await profileTypeComponentsPage.createButton.isEnabled()).to.be.true;
    await waitUntilDisplayed(profileTypeComponentsPage.table);
    await waitUntilCount(profileTypeComponentsPage.records, beforeRecordsCount + 1);
    expect(await profileTypeComponentsPage.records.count()).to.eq(beforeRecordsCount + 1);

    await profileTypeComponentsPage.deleteProfileType();
    if (beforeRecordsCount !== 0) {
      await waitUntilCount(profileTypeComponentsPage.records, beforeRecordsCount);
      expect(await profileTypeComponentsPage.records.count()).to.eq(beforeRecordsCount);
    } else {
      await waitUntilDisplayed(profileTypeComponentsPage.noRecords);
    }
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
