import { element, by, ElementFinder } from 'protractor';
import { waitUntilDisplayed, waitUntilHidden, isVisible } from '../../util/utils';

const expect = chai.expect;

export default class CountrolAccountUpdatePage {
  pageTitle: ElementFinder = element(by.id('apiGatewayApp.countrolAccount.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  countrolAccountCodeInput: ElementFinder = element(by.css('input#countrol-account-my-suffix-countrolAccountCode'));
  countrolAccountNameInput: ElementFinder = element(by.css('input#countrol-account-my-suffix-countrolAccountName'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setCountrolAccountCodeInput(countrolAccountCode) {
    await this.countrolAccountCodeInput.sendKeys(countrolAccountCode);
  }

  async getCountrolAccountCodeInput() {
    return this.countrolAccountCodeInput.getAttribute('value');
  }

  async setCountrolAccountNameInput(countrolAccountName) {
    await this.countrolAccountNameInput.sendKeys(countrolAccountName);
  }

  async getCountrolAccountNameInput() {
    return this.countrolAccountNameInput.getAttribute('value');
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
    await this.setCountrolAccountCodeInput('countrolAccountCode');
    expect(await this.getCountrolAccountCodeInput()).to.match(/countrolAccountCode/);
    await waitUntilDisplayed(this.saveButton);
    await this.setCountrolAccountNameInput('countrolAccountName');
    expect(await this.getCountrolAccountNameInput()).to.match(/countrolAccountName/);
    await this.save();
    await waitUntilHidden(this.saveButton);
    expect(await isVisible(this.saveButton)).to.be.false;
  }
}
