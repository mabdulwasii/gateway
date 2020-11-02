import { element, by, ElementFinder } from 'protractor';
import { waitUntilDisplayed, waitUntilHidden, isVisible } from '../../util/utils';

import path from 'path';

const expect = chai.expect;

const fileToUpload = '../../../../../../src/main/webapp/content/images/logo-jhipster.png';
const absolutePath = path.resolve(__dirname, fileToUpload);
export default class ProfileUpdatePage {
  pageTitle: ElementFinder = element(by.id('apiGatewayApp.profile.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  profileIDInput: ElementFinder = element(by.css('input#profile-my-suffix-profileID'));
  phoneNumberInput: ElementFinder = element(by.css('input#profile-my-suffix-phoneNumber'));
  genderSelect: ElementFinder = element(by.css('select#profile-my-suffix-gender'));
  dateOfBirthInput: ElementFinder = element(by.css('input#profile-my-suffix-dateOfBirth'));
  addressInput: ElementFinder = element(by.css('input#profile-my-suffix-address'));
  photoInput: ElementFinder = element(by.css('input#file_photo'));
  bvnInput: ElementFinder = element(by.css('input#profile-my-suffix-bvn'));
  validIDInput: ElementFinder = element(by.css('input#profile-my-suffix-validID'));
  userSelect: ElementFinder = element(by.css('select#profile-my-suffix-user'));
  profileTypeSelect: ElementFinder = element(by.css('select#profile-my-suffix-profileType'));
  kycSelect: ElementFinder = element(by.css('select#profile-my-suffix-kyc'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setProfileIDInput(profileID) {
    await this.profileIDInput.sendKeys(profileID);
  }

  async getProfileIDInput() {
    return this.profileIDInput.getAttribute('value');
  }

  async setPhoneNumberInput(phoneNumber) {
    await this.phoneNumberInput.sendKeys(phoneNumber);
  }

  async getPhoneNumberInput() {
    return this.phoneNumberInput.getAttribute('value');
  }

  async setGenderSelect(gender) {
    await this.genderSelect.sendKeys(gender);
  }

  async getGenderSelect() {
    return this.genderSelect.element(by.css('option:checked')).getText();
  }

  async genderSelectLastOption() {
    await this.genderSelect.all(by.tagName('option')).last().click();
  }
  async setDateOfBirthInput(dateOfBirth) {
    await this.dateOfBirthInput.sendKeys(dateOfBirth);
  }

  async getDateOfBirthInput() {
    return this.dateOfBirthInput.getAttribute('value');
  }

  async setAddressInput(address) {
    await this.addressInput.sendKeys(address);
  }

  async getAddressInput() {
    return this.addressInput.getAttribute('value');
  }

  async setPhotoInput(photo) {
    await this.photoInput.sendKeys(photo);
  }

  async getPhotoInput() {
    return this.photoInput.getAttribute('value');
  }

  async setBvnInput(bvn) {
    await this.bvnInput.sendKeys(bvn);
  }

  async getBvnInput() {
    return this.bvnInput.getAttribute('value');
  }

  async setValidIDInput(validID) {
    await this.validIDInput.sendKeys(validID);
  }

  async getValidIDInput() {
    return this.validIDInput.getAttribute('value');
  }

  async userSelectLastOption() {
    await this.userSelect.all(by.tagName('option')).last().click();
  }

  async userSelectOption(option) {
    await this.userSelect.sendKeys(option);
  }

  getUserSelect() {
    return this.userSelect;
  }

  async getUserSelectedOption() {
    return this.userSelect.element(by.css('option:checked')).getText();
  }

  async profileTypeSelectLastOption() {
    await this.profileTypeSelect.all(by.tagName('option')).last().click();
  }

  async profileTypeSelectOption(option) {
    await this.profileTypeSelect.sendKeys(option);
  }

  getProfileTypeSelect() {
    return this.profileTypeSelect;
  }

  async getProfileTypeSelectedOption() {
    return this.profileTypeSelect.element(by.css('option:checked')).getText();
  }

  async kycSelectLastOption() {
    await this.kycSelect.all(by.tagName('option')).last().click();
  }

  async kycSelectOption(option) {
    await this.kycSelect.sendKeys(option);
  }

  getKycSelect() {
    return this.kycSelect;
  }

  async getKycSelectedOption() {
    return this.kycSelect.element(by.css('option:checked')).getText();
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
    await this.setProfileIDInput('profileID');
    expect(await this.getProfileIDInput()).to.match(/profileID/);
    await waitUntilDisplayed(this.saveButton);
    await this.setPhoneNumberInput('phoneNumber');
    expect(await this.getPhoneNumberInput()).to.match(/phoneNumber/);
    await waitUntilDisplayed(this.saveButton);
    await this.genderSelectLastOption();
    await waitUntilDisplayed(this.saveButton);
    await this.setDateOfBirthInput('01-01-2001');
    expect(await this.getDateOfBirthInput()).to.eq('2001-01-01');
    await waitUntilDisplayed(this.saveButton);
    await this.setAddressInput('address');
    expect(await this.getAddressInput()).to.match(/address/);
    await waitUntilDisplayed(this.saveButton);
    await this.setPhotoInput(absolutePath);
    await waitUntilDisplayed(this.saveButton);
    await this.setBvnInput('bvn');
    expect(await this.getBvnInput()).to.match(/bvn/);
    await waitUntilDisplayed(this.saveButton);
    await this.setValidIDInput('validID');
    expect(await this.getValidIDInput()).to.match(/validID/);
    await this.userSelectLastOption();
    await this.profileTypeSelectLastOption();
    await this.kycSelectLastOption();
    await this.save();
    await waitUntilHidden(this.saveButton);
    expect(await isVisible(this.saveButton)).to.be.false;
  }
}
