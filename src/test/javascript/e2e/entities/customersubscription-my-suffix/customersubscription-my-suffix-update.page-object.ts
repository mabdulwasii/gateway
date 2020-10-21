import { element, by, ElementFinder } from 'protractor';
import { waitUntilDisplayed, waitUntilHidden, isVisible } from '../../util/utils';

const expect = chai.expect;

export default class CustomersubscriptionUpdatePage {
  pageTitle: ElementFinder = element(by.id('apiGatewayApp.customersubscription.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  uniqueIDInput: ElementFinder = element(by.css('input#customersubscription-my-suffix-uniqueID'));
  frequencySelect: ElementFinder = element(by.css('select#customersubscription-my-suffix-frequency'));
  phoneNumberSelect: ElementFinder = element(by.css('select#customersubscription-my-suffix-phoneNumber'));
  billerSelect: ElementFinder = element(by.css('select#customersubscription-my-suffix-biller'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setUniqueIDInput(uniqueID) {
    await this.uniqueIDInput.sendKeys(uniqueID);
  }

  async getUniqueIDInput() {
    return this.uniqueIDInput.getAttribute('value');
  }

  async setFrequencySelect(frequency) {
    await this.frequencySelect.sendKeys(frequency);
  }

  async getFrequencySelect() {
    return this.frequencySelect.element(by.css('option:checked')).getText();
  }

  async frequencySelectLastOption() {
    await this.frequencySelect.all(by.tagName('option')).last().click();
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

  async billerSelectLastOption() {
    await this.billerSelect.all(by.tagName('option')).last().click();
  }

  async billerSelectOption(option) {
    await this.billerSelect.sendKeys(option);
  }

  getBillerSelect() {
    return this.billerSelect;
  }

  async getBillerSelectedOption() {
    return this.billerSelect.element(by.css('option:checked')).getText();
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
    await this.setUniqueIDInput('uniqueID');
    expect(await this.getUniqueIDInput()).to.match(/uniqueID/);
    await waitUntilDisplayed(this.saveButton);
    await this.frequencySelectLastOption();
    await this.phoneNumberSelectLastOption();
    await this.billerSelectLastOption();
    await this.save();
    await waitUntilHidden(this.saveButton);
    expect(await isVisible(this.saveButton)).to.be.false;
  }
}
