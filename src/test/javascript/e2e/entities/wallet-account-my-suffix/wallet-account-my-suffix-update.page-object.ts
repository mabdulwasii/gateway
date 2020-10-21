import { element, by, ElementFinder } from 'protractor';
import { waitUntilDisplayed, waitUntilHidden, isVisible } from '../../util/utils';

const expect = chai.expect;

export default class WalletAccountUpdatePage {
  pageTitle: ElementFinder = element(by.id('apiGatewayApp.walletAccount.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  accountNumberInput: ElementFinder = element(by.css('input#wallet-account-my-suffix-accountNumber'));
  currentBalanceInput: ElementFinder = element(by.css('input#wallet-account-my-suffix-currentBalance'));
  dateOpenedInput: ElementFinder = element(by.css('input#wallet-account-my-suffix-dateOpened'));
  schemeSelect: ElementFinder = element(by.css('select#wallet-account-my-suffix-scheme'));
  walletAccountTypeSelect: ElementFinder = element(by.css('select#wallet-account-my-suffix-walletAccountType'));
  accountOwnerSelect: ElementFinder = element(by.css('select#wallet-account-my-suffix-accountOwner'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setAccountNumberInput(accountNumber) {
    await this.accountNumberInput.sendKeys(accountNumber);
  }

  async getAccountNumberInput() {
    return this.accountNumberInput.getAttribute('value');
  }

  async setCurrentBalanceInput(currentBalance) {
    await this.currentBalanceInput.sendKeys(currentBalance);
  }

  async getCurrentBalanceInput() {
    return this.currentBalanceInput.getAttribute('value');
  }

  async setDateOpenedInput(dateOpened) {
    await this.dateOpenedInput.sendKeys(dateOpened);
  }

  async getDateOpenedInput() {
    return this.dateOpenedInput.getAttribute('value');
  }

  async schemeSelectLastOption() {
    await this.schemeSelect.all(by.tagName('option')).last().click();
  }

  async schemeSelectOption(option) {
    await this.schemeSelect.sendKeys(option);
  }

  getSchemeSelect() {
    return this.schemeSelect;
  }

  async getSchemeSelectedOption() {
    return this.schemeSelect.element(by.css('option:checked')).getText();
  }

  async walletAccountTypeSelectLastOption() {
    await this.walletAccountTypeSelect.all(by.tagName('option')).last().click();
  }

  async walletAccountTypeSelectOption(option) {
    await this.walletAccountTypeSelect.sendKeys(option);
  }

  getWalletAccountTypeSelect() {
    return this.walletAccountTypeSelect;
  }

  async getWalletAccountTypeSelectedOption() {
    return this.walletAccountTypeSelect.element(by.css('option:checked')).getText();
  }

  async accountOwnerSelectLastOption() {
    await this.accountOwnerSelect.all(by.tagName('option')).last().click();
  }

  async accountOwnerSelectOption(option) {
    await this.accountOwnerSelect.sendKeys(option);
  }

  getAccountOwnerSelect() {
    return this.accountOwnerSelect;
  }

  async getAccountOwnerSelectedOption() {
    return this.accountOwnerSelect.element(by.css('option:checked')).getText();
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
    await this.setAccountNumberInput('5');
    expect(await this.getAccountNumberInput()).to.eq('5');
    await waitUntilDisplayed(this.saveButton);
    await this.setCurrentBalanceInput('5');
    expect(await this.getCurrentBalanceInput()).to.eq('5');
    await waitUntilDisplayed(this.saveButton);
    await this.setDateOpenedInput('01-01-2001');
    expect(await this.getDateOpenedInput()).to.eq('2001-01-01');
    await this.schemeSelectLastOption();
    await this.walletAccountTypeSelectLastOption();
    await this.accountOwnerSelectLastOption();
    await this.save();
    await waitUntilHidden(this.saveButton);
    expect(await isVisible(this.saveButton)).to.be.false;
  }
}
