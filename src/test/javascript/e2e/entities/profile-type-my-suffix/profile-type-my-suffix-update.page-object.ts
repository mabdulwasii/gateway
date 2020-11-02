import { element, by, ElementFinder } from 'protractor';
import { waitUntilDisplayed, waitUntilHidden, isVisible } from '../../util/utils';

const expect = chai.expect;

export default class ProfileTypeUpdatePage {
  pageTitle: ElementFinder = element(by.id('apiGatewayApp.profileType.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  profiletypeIDInput: ElementFinder = element(by.css('input#profile-type-my-suffix-profiletypeID'));
  profiletypeInput: ElementFinder = element(by.css('input#profile-type-my-suffix-profiletype'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setProfiletypeIDInput(profiletypeID) {
    await this.profiletypeIDInput.sendKeys(profiletypeID);
  }

  async getProfiletypeIDInput() {
    return this.profiletypeIDInput.getAttribute('value');
  }

  async setProfiletypeInput(profiletype) {
    await this.profiletypeInput.sendKeys(profiletype);
  }

  async getProfiletypeInput() {
    return this.profiletypeInput.getAttribute('value');
  }

  async save() {
    await this.saveButton.click();
  }

  async cancel() {
    await this.cancelButton.click();
  }

  getSaveButton() {
    return this.saveButton;
  }

  async enterData() {
    await waitUntilDisplayed(this.saveButton);
    await this.setProfiletypeIDInput('5');
    expect(await this.getProfiletypeIDInput()).to.eq('5');
    await waitUntilDisplayed(this.saveButton);
    await this.setProfiletypeInput('profiletype');
    expect(await this.getProfiletypeInput()).to.match(/profiletype/);
    await this.save();
    await waitUntilHidden(this.saveButton);
    expect(await isVisible(this.saveButton)).to.be.false;
  }
}
