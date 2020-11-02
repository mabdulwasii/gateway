import { element, by, ElementFinder } from 'protractor';
import { waitUntilDisplayed, waitUntilHidden, isVisible } from '../../util/utils';

const expect = chai.expect;

export default class WalletAccountTypeUpdatePage {
  pageTitle: ElementFinder = element(by.id('apiGatewayApp.walletAccountType.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  accountypeIDInput: ElementFinder = element(by.css('input#wallet-account-type-my-suffix-accountypeID'));
  walletAccountTypeInput: ElementFinder = element(by.css('input#wallet-account-type-my-suffix-walletAccountType'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setAccountypeIDInput(accountypeID) {
    await this.accountypeIDInput.sendKeys(accountypeID);
  }

  async getAccountypeIDInput() {
    return this.accountypeIDInput.getAttribute('value');
  }

  async setWalletAccountTypeInput(walletAccountType) {
    await this.walletAccountTypeInput.sendKeys(walletAccountType);
  }

  async getWalletAccountTypeInput() {
    return this.walletAccountTypeInput.getAttribute('value');
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
    await this.setAccountypeIDInput('5');
    expect(await this.getAccountypeIDInput()).to.eq('5');
    await waitUntilDisplayed(this.saveButton);
    await this.setWalletAccountTypeInput('walletAccountType');
    expect(await this.getWalletAccountTypeInput()).to.match(/walletAccountType/);
    await this.save();
    await waitUntilHidden(this.saveButton);
    expect(await isVisible(this.saveButton)).to.be.false;
  }
}
