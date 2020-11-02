import { element, by, ElementFinder } from 'protractor';
import { waitUntilDisplayed, waitUntilHidden, isVisible } from '../../util/utils';

const expect = chai.expect;

export default class BillerPlatformUpdatePage {
  pageTitle: ElementFinder = element(by.id('apiGatewayApp.billerPlatform.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  billerplatformIDInput: ElementFinder = element(by.css('input#biller-platform-my-suffix-billerplatformID'));
  billerPlatformInput: ElementFinder = element(by.css('input#biller-platform-my-suffix-billerPlatform'));
  amountInput: ElementFinder = element(by.css('input#biller-platform-my-suffix-amount'));
  billerSelect: ElementFinder = element(by.css('select#biller-platform-my-suffix-biller'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setBillerplatformIDInput(billerplatformID) {
    await this.billerplatformIDInput.sendKeys(billerplatformID);
  }

  async getBillerplatformIDInput() {
    return this.billerplatformIDInput.getAttribute('value');
  }

  async setBillerPlatformInput(billerPlatform) {
    await this.billerPlatformInput.sendKeys(billerPlatform);
  }

  async getBillerPlatformInput() {
    return this.billerPlatformInput.getAttribute('value');
  }

  async setAmountInput(amount) {
    await this.amountInput.sendKeys(amount);
  }

  async getAmountInput() {
    return this.amountInput.getAttribute('value');
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
    await this.setBillerplatformIDInput('5');
    expect(await this.getBillerplatformIDInput()).to.eq('5');
    await waitUntilDisplayed(this.saveButton);
    await this.setBillerPlatformInput('billerPlatform');
    expect(await this.getBillerPlatformInput()).to.match(/billerPlatform/);
    await waitUntilDisplayed(this.saveButton);
    await this.setAmountInput('5');
    expect(await this.getAmountInput()).to.eq('5');
    await this.billerSelectLastOption();
    await this.save();
    await waitUntilHidden(this.saveButton);
    expect(await isVisible(this.saveButton)).to.be.false;
  }
}
