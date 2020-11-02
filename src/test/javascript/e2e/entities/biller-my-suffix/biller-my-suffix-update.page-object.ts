import { element, by, ElementFinder } from 'protractor';
import { waitUntilDisplayed, waitUntilHidden, isVisible } from '../../util/utils';

const expect = chai.expect;

export default class BillerUpdatePage {
  pageTitle: ElementFinder = element(by.id('apiGatewayApp.biller.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  billerIDInput: ElementFinder = element(by.css('input#biller-my-suffix-billerID'));
  billerInput: ElementFinder = element(by.css('input#biller-my-suffix-biller'));
  addressInput: ElementFinder = element(by.css('input#biller-my-suffix-address'));
  phoneNumberInput: ElementFinder = element(by.css('input#biller-my-suffix-phoneNumber'));
  billerCategorySelect: ElementFinder = element(by.css('select#biller-my-suffix-billerCategory'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setBillerIDInput(billerID) {
    await this.billerIDInput.sendKeys(billerID);
  }

  async getBillerIDInput() {
    return this.billerIDInput.getAttribute('value');
  }

  async setBillerInput(biller) {
    await this.billerInput.sendKeys(biller);
  }

  async getBillerInput() {
    return this.billerInput.getAttribute('value');
  }

  async setAddressInput(address) {
    await this.addressInput.sendKeys(address);
  }

  async getAddressInput() {
    return this.addressInput.getAttribute('value');
  }

  async setPhoneNumberInput(phoneNumber) {
    await this.phoneNumberInput.sendKeys(phoneNumber);
  }

  async getPhoneNumberInput() {
    return this.phoneNumberInput.getAttribute('value');
  }

  async billerCategorySelectLastOption() {
    await this.billerCategorySelect.all(by.tagName('option')).last().click();
  }

  async billerCategorySelectOption(option) {
    await this.billerCategorySelect.sendKeys(option);
  }

  getBillerCategorySelect() {
    return this.billerCategorySelect;
  }

  async getBillerCategorySelectedOption() {
    return this.billerCategorySelect.element(by.css('option:checked')).getText();
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
    await this.setBillerIDInput('5');
    expect(await this.getBillerIDInput()).to.eq('5');
    await waitUntilDisplayed(this.saveButton);
    await this.setBillerInput('biller');
    expect(await this.getBillerInput()).to.match(/biller/);
    await waitUntilDisplayed(this.saveButton);
    await this.setAddressInput('address');
    expect(await this.getAddressInput()).to.match(/address/);
    await waitUntilDisplayed(this.saveButton);
    await this.setPhoneNumberInput('phoneNumber');
    expect(await this.getPhoneNumberInput()).to.match(/phoneNumber/);
    await this.billerCategorySelectLastOption();
    await this.save();
    await waitUntilHidden(this.saveButton);
    expect(await isVisible(this.saveButton)).to.be.false;
  }
}
