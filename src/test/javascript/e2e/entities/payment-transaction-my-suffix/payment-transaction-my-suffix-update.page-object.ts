import { element, by, ElementFinder } from 'protractor';
import { waitUntilDisplayed, waitUntilHidden, isVisible } from '../../util/utils';

const expect = chai.expect;

export default class PaymentTransactionUpdatePage {
  pageTitle: ElementFinder = element(by.id('apiGatewayApp.paymentTransaction.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  paymenttransIDInput: ElementFinder = element(by.css('input#payment-transaction-my-suffix-paymenttransID'));
  transactionTypeSelect: ElementFinder = element(by.css('select#payment-transaction-my-suffix-transactionType'));
  transactionRefInput: ElementFinder = element(by.css('input#payment-transaction-my-suffix-transactionRef'));
  amountInput: ElementFinder = element(by.css('input#payment-transaction-my-suffix-amount'));
  channelInput: ElementFinder = element(by.css('input#payment-transaction-my-suffix-channel'));
  currencyInput: ElementFinder = element(by.css('input#payment-transaction-my-suffix-currency'));
  sourceAccountInput: ElementFinder = element(by.css('input#payment-transaction-my-suffix-sourceAccount'));
  sourceAccountBankCodeInput: ElementFinder = element(by.css('input#payment-transaction-my-suffix-sourceAccountBankCode'));
  sourceAccountNameInput: ElementFinder = element(by.css('input#payment-transaction-my-suffix-sourceAccountName'));
  sourceNarrationInput: ElementFinder = element(by.css('input#payment-transaction-my-suffix-sourceNarration'));
  destinationAccountInput: ElementFinder = element(by.css('input#payment-transaction-my-suffix-destinationAccount'));
  destinationAccountBankCodeInput: ElementFinder = element(by.css('input#payment-transaction-my-suffix-destinationAccountBankCode'));
  destinationAccountNameInput: ElementFinder = element(by.css('input#payment-transaction-my-suffix-destinationAccountName'));
  destinationNarrationInput: ElementFinder = element(by.css('input#payment-transaction-my-suffix-destinationNarration'));
  transactionOwnerSelect: ElementFinder = element(by.css('select#payment-transaction-my-suffix-transactionOwner'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setPaymenttransIDInput(paymenttransID) {
    await this.paymenttransIDInput.sendKeys(paymenttransID);
  }

  async getPaymenttransIDInput() {
    return this.paymenttransIDInput.getAttribute('value');
  }

  async setTransactionTypeSelect(transactionType) {
    await this.transactionTypeSelect.sendKeys(transactionType);
  }

  async getTransactionTypeSelect() {
    return this.transactionTypeSelect.element(by.css('option:checked')).getText();
  }

  async transactionTypeSelectLastOption() {
    await this.transactionTypeSelect.all(by.tagName('option')).last().click();
  }
  async setTransactionRefInput(transactionRef) {
    await this.transactionRefInput.sendKeys(transactionRef);
  }

  async getTransactionRefInput() {
    return this.transactionRefInput.getAttribute('value');
  }

  async setAmountInput(amount) {
    await this.amountInput.sendKeys(amount);
  }

  async getAmountInput() {
    return this.amountInput.getAttribute('value');
  }

  async setChannelInput(channel) {
    await this.channelInput.sendKeys(channel);
  }

  async getChannelInput() {
    return this.channelInput.getAttribute('value');
  }

  async setCurrencyInput(currency) {
    await this.currencyInput.sendKeys(currency);
  }

  async getCurrencyInput() {
    return this.currencyInput.getAttribute('value');
  }

  async setSourceAccountInput(sourceAccount) {
    await this.sourceAccountInput.sendKeys(sourceAccount);
  }

  async getSourceAccountInput() {
    return this.sourceAccountInput.getAttribute('value');
  }

  async setSourceAccountBankCodeInput(sourceAccountBankCode) {
    await this.sourceAccountBankCodeInput.sendKeys(sourceAccountBankCode);
  }

  async getSourceAccountBankCodeInput() {
    return this.sourceAccountBankCodeInput.getAttribute('value');
  }

  async setSourceAccountNameInput(sourceAccountName) {
    await this.sourceAccountNameInput.sendKeys(sourceAccountName);
  }

  async getSourceAccountNameInput() {
    return this.sourceAccountNameInput.getAttribute('value');
  }

  async setSourceNarrationInput(sourceNarration) {
    await this.sourceNarrationInput.sendKeys(sourceNarration);
  }

  async getSourceNarrationInput() {
    return this.sourceNarrationInput.getAttribute('value');
  }

  async setDestinationAccountInput(destinationAccount) {
    await this.destinationAccountInput.sendKeys(destinationAccount);
  }

  async getDestinationAccountInput() {
    return this.destinationAccountInput.getAttribute('value');
  }

  async setDestinationAccountBankCodeInput(destinationAccountBankCode) {
    await this.destinationAccountBankCodeInput.sendKeys(destinationAccountBankCode);
  }

  async getDestinationAccountBankCodeInput() {
    return this.destinationAccountBankCodeInput.getAttribute('value');
  }

  async setDestinationAccountNameInput(destinationAccountName) {
    await this.destinationAccountNameInput.sendKeys(destinationAccountName);
  }

  async getDestinationAccountNameInput() {
    return this.destinationAccountNameInput.getAttribute('value');
  }

  async setDestinationNarrationInput(destinationNarration) {
    await this.destinationNarrationInput.sendKeys(destinationNarration);
  }

  async getDestinationNarrationInput() {
    return this.destinationNarrationInput.getAttribute('value');
  }

  async transactionOwnerSelectLastOption() {
    await this.transactionOwnerSelect.all(by.tagName('option')).last().click();
  }

  async transactionOwnerSelectOption(option) {
    await this.transactionOwnerSelect.sendKeys(option);
  }

  getTransactionOwnerSelect() {
    return this.transactionOwnerSelect;
  }

  async getTransactionOwnerSelectedOption() {
    return this.transactionOwnerSelect.element(by.css('option:checked')).getText();
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
    await this.setPaymenttransIDInput('5');
    expect(await this.getPaymenttransIDInput()).to.eq('5');
    await waitUntilDisplayed(this.saveButton);
    await this.transactionTypeSelectLastOption();
    await waitUntilDisplayed(this.saveButton);
    await this.setTransactionRefInput('transactionRef');
    expect(await this.getTransactionRefInput()).to.match(/transactionRef/);
    await waitUntilDisplayed(this.saveButton);
    await this.setAmountInput('5');
    expect(await this.getAmountInput()).to.eq('5');
    await waitUntilDisplayed(this.saveButton);
    await this.setChannelInput('channel');
    expect(await this.getChannelInput()).to.match(/channel/);
    await waitUntilDisplayed(this.saveButton);
    await this.setCurrencyInput('currency');
    expect(await this.getCurrencyInput()).to.match(/currency/);
    await waitUntilDisplayed(this.saveButton);
    await this.setSourceAccountInput('sourceAccount');
    expect(await this.getSourceAccountInput()).to.match(/sourceAccount/);
    await waitUntilDisplayed(this.saveButton);
    await this.setSourceAccountBankCodeInput('sourceAccountBankCode');
    expect(await this.getSourceAccountBankCodeInput()).to.match(/sourceAccountBankCode/);
    await waitUntilDisplayed(this.saveButton);
    await this.setSourceAccountNameInput('sourceAccountName');
    expect(await this.getSourceAccountNameInput()).to.match(/sourceAccountName/);
    await waitUntilDisplayed(this.saveButton);
    await this.setSourceNarrationInput('sourceNarration');
    expect(await this.getSourceNarrationInput()).to.match(/sourceNarration/);
    await waitUntilDisplayed(this.saveButton);
    await this.setDestinationAccountInput('destinationAccount');
    expect(await this.getDestinationAccountInput()).to.match(/destinationAccount/);
    await waitUntilDisplayed(this.saveButton);
    await this.setDestinationAccountBankCodeInput('destinationAccountBankCode');
    expect(await this.getDestinationAccountBankCodeInput()).to.match(/destinationAccountBankCode/);
    await waitUntilDisplayed(this.saveButton);
    await this.setDestinationAccountNameInput('destinationAccountName');
    expect(await this.getDestinationAccountNameInput()).to.match(/destinationAccountName/);
    await waitUntilDisplayed(this.saveButton);
    await this.setDestinationNarrationInput('destinationNarration');
    expect(await this.getDestinationNarrationInput()).to.match(/destinationNarration/);
    await this.transactionOwnerSelectLastOption();
    await this.save();
    await waitUntilHidden(this.saveButton);
    expect(await isVisible(this.saveButton)).to.be.false;
  }
}
