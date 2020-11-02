import { element, by, ElementFinder } from 'protractor';
import { waitUntilDisplayed, waitUntilHidden, isVisible } from '../../util/utils';

const expect = chai.expect;

export default class AddressUpdatePage {
  pageTitle: ElementFinder = element(by.id('apiGatewayApp.address.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  stateInput: ElementFinder = element(by.css('input#address-my-suffix-state'));
  localGovtInput: ElementFinder = element(by.css('input#address-my-suffix-localGovt'));
  latitudeInput: ElementFinder = element(by.css('input#address-my-suffix-latitude'));
  longitudeInput: ElementFinder = element(by.css('input#address-my-suffix-longitude'));
  addressInput: ElementFinder = element(by.css('input#address-my-suffix-address'));
  addressOwnerSelect: ElementFinder = element(by.css('select#address-my-suffix-addressOwner'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setStateInput(state) {
    await this.stateInput.sendKeys(state);
  }

  async getStateInput() {
    return this.stateInput.getAttribute('value');
  }

  async setLocalGovtInput(localGovt) {
    await this.localGovtInput.sendKeys(localGovt);
  }

  async getLocalGovtInput() {
    return this.localGovtInput.getAttribute('value');
  }

  async setLatitudeInput(latitude) {
    await this.latitudeInput.sendKeys(latitude);
  }

  async getLatitudeInput() {
    return this.latitudeInput.getAttribute('value');
  }

  async setLongitudeInput(longitude) {
    await this.longitudeInput.sendKeys(longitude);
  }

  async getLongitudeInput() {
    return this.longitudeInput.getAttribute('value');
  }

  async setAddressInput(address) {
    await this.addressInput.sendKeys(address);
  }

  async getAddressInput() {
    return this.addressInput.getAttribute('value');
  }

  async addressOwnerSelectLastOption() {
    await this.addressOwnerSelect.all(by.tagName('option')).last().click();
  }

  async addressOwnerSelectOption(option) {
    await this.addressOwnerSelect.sendKeys(option);
  }

  getAddressOwnerSelect() {
    return this.addressOwnerSelect;
  }

  async getAddressOwnerSelectedOption() {
    return this.addressOwnerSelect.element(by.css('option:checked')).getText();
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
    await this.setStateInput('state');
    expect(await this.getStateInput()).to.match(/state/);
    await waitUntilDisplayed(this.saveButton);
    await this.setLocalGovtInput('localGovt');
    expect(await this.getLocalGovtInput()).to.match(/localGovt/);
    await waitUntilDisplayed(this.saveButton);
    await this.setLatitudeInput('5');
    expect(await this.getLatitudeInput()).to.eq('5');
    await waitUntilDisplayed(this.saveButton);
    await this.setLongitudeInput('5');
    expect(await this.getLongitudeInput()).to.eq('5');
    await waitUntilDisplayed(this.saveButton);
    await this.setAddressInput('address');
    expect(await this.getAddressInput()).to.match(/address/);
    await this.addressOwnerSelectLastOption();
    await this.save();
    await waitUntilHidden(this.saveButton);
    expect(await isVisible(this.saveButton)).to.be.false;
  }
}
