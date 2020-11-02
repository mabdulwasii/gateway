import { element, by, ElementFinder } from 'protractor';
import { waitUntilDisplayed, waitUntilHidden, isVisible } from '../../util/utils';

const expect = chai.expect;

export default class BillerTransactionUpdatePage {
  pageTitle: ElementFinder = element(by.id('apiGatewayApp.billerTransaction.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  billertransIDInput: ElementFinder = element(by.css('input#biller-transaction-my-suffix-billertransID'));
  transactionRefInput: ElementFinder = element(by.css('input#biller-transaction-my-suffix-transactionRef'));
  narrationInput: ElementFinder = element(by.css('input#biller-transaction-my-suffix-narration'));
  amountInput: ElementFinder = element(by.css('input#biller-transaction-my-suffix-amount'));
  phoneNumberSelect: ElementFinder = element(by.css('select#biller-transaction-my-suffix-phoneNumber'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setBillertransIDInput(billertransID) {
    await this.billertransIDInput.sendKeys(billertransID);
  }

  async getBillertransIDInput() {
    return this.billertransIDInput.getAttribute('value');
  }

  async setTransactionRefInput(transactionRef) {
    await this.transactionRefInput.sendKeys(transactionRef);
  }

  async getTransactionRefInput() {
    return this.transactionRefInput.getAttribute('value');
  }

  async setNarrationInput(narration) {
    await this.narrationInput.sendKeys(narration);
  }

  async getNarrationInput() {
    return this.narrationInput.getAttribute('value');
  }

  async setAmountInput(amount) {
    await this.amountInput.sendKeys(amount);
  }

  async getAmountInput() {
    return this.amountInput.getAttribute('value');
  }

  async phoneNumberSelectLastOption() {
    await this.phoneNumberSelect.all(by.tagName('option')).last().click();
  }

  async phoneNumberSelectOption(option) {
    await this.phoneNumberSelect.sendKeys(option);
  }

  getPhoneNumberSelect() {
    return this.phoneNumberSelect;
  }

  async getPhoneNumberSelectedOption() {
    return this.phoneNumberSelect.element(by.css('option:checked')).getText();
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
    await this.setBillertransIDInput('5');
    expect(await this.getBillertransIDInput()).to.eq('5');
    await waitUntilDisplayed(this.saveButton);
    await this.setTransactionRefInput('transactionRef');
    expect(await this.getTransactionRefInput()).to.match(/transactionRef/);
    await waitUntilDisplayed(this.saveButton);
    await this.setNarrationInput('narration');
    expect(await this.getNarrationInput()).to.match(/narration/);
    await waitUntilDisplayed(this.saveButton);
    await this.setAmountInput('5');
    expect(await this.getAmountInput()).to.eq('5');
    await this.phoneNumberSelectLastOption();
    await this.save();
    await waitUntilHidden(this.saveButton);
    expect(await isVisible(this.saveButton)).to.be.false;
  }
}
