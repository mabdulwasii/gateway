import { element, by, ElementFinder } from 'protractor';
import { waitUntilDisplayed, waitUntilHidden, isVisible } from '../../util/utils';

const expect = chai.expect;

export default class DoubleEntryLoggerUpdatePage {
  pageTitle: ElementFinder = element(by.id('apiGatewayApp.doubleEntryLogger.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  dateEnteredInput: ElementFinder = element(by.css('input#double-entry-logger-my-suffix-dateEntered'));
  doubleEntryCodeInput: ElementFinder = element(by.css('input#double-entry-logger-my-suffix-doubleEntryCode'));
  amountInput: ElementFinder = element(by.css('input#double-entry-logger-my-suffix-amount'));
  narrationInput: ElementFinder = element(by.css('input#double-entry-logger-my-suffix-narration'));
  debitSelect: ElementFinder = element(by.css('select#double-entry-logger-my-suffix-debit'));
  creditSelect: ElementFinder = element(by.css('select#double-entry-logger-my-suffix-credit'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setDateEnteredInput(dateEntered) {
    await this.dateEnteredInput.sendKeys(dateEntered);
  }

  async getDateEnteredInput() {
    return this.dateEnteredInput.getAttribute('value');
  }

  async setDoubleEntryCodeInput(doubleEntryCode) {
    await this.doubleEntryCodeInput.sendKeys(doubleEntryCode);
  }

  async getDoubleEntryCodeInput() {
    return this.doubleEntryCodeInput.getAttribute('value');
  }

  async setAmountInput(amount) {
    await this.amountInput.sendKeys(amount);
  }

  async getAmountInput() {
    return this.amountInput.getAttribute('value');
  }

  async setNarrationInput(narration) {
    await this.narrationInput.sendKeys(narration);
  }

  async getNarrationInput() {
    return this.narrationInput.getAttribute('value');
  }

  async debitSelectLastOption() {
    await this.debitSelect.all(by.tagName('option')).last().click();
  }

  async debitSelectOption(option) {
    await this.debitSelect.sendKeys(option);
  }

  getDebitSelect() {
    return this.debitSelect;
  }

  async getDebitSelectedOption() {
    return this.debitSelect.element(by.css('option:checked')).getText();
  }

  async creditSelectLastOption() {
    await this.creditSelect.all(by.tagName('option')).last().click();
  }

  async creditSelectOption(option) {
    await this.creditSelect.sendKeys(option);
  }

  getCreditSelect() {
    return this.creditSelect;
  }

  async getCreditSelectedOption() {
    return this.creditSelect.element(by.css('option:checked')).getText();
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
    await this.setDateEnteredInput('01-01-2001');
    expect(await this.getDateEnteredInput()).to.eq('2001-01-01');
    await waitUntilDisplayed(this.saveButton);
    await this.setDoubleEntryCodeInput('doubleEntryCode');
    expect(await this.getDoubleEntryCodeInput()).to.match(/doubleEntryCode/);
    await waitUntilDisplayed(this.saveButton);
    await this.setAmountInput('5');
    expect(await this.getAmountInput()).to.eq('5');
    await waitUntilDisplayed(this.saveButton);
    await this.setNarrationInput('narration');
    expect(await this.getNarrationInput()).to.match(/narration/);
    await this.debitSelectLastOption();
    await this.creditSelectLastOption();
    await this.save();
    await waitUntilHidden(this.saveButton);
    expect(await isVisible(this.saveButton)).to.be.false;
  }
}
