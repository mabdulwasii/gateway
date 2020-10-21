import { element, by, ElementFinder } from 'protractor';
import { waitUntilDisplayed, waitUntilHidden, isVisible } from '../../util/utils';

const expect = chai.expect;

export default class BillerCategoryUpdatePage {
  pageTitle: ElementFinder = element(by.id('apiGatewayApp.billerCategory.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  billercategoryIDInput: ElementFinder = element(by.css('input#biller-category-my-suffix-billercategoryID'));
  billerCategoryInput: ElementFinder = element(by.css('input#biller-category-my-suffix-billerCategory'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setBillercategoryIDInput(billercategoryID) {
    await this.billercategoryIDInput.sendKeys(billercategoryID);
  }

  async getBillercategoryIDInput() {
    return this.billercategoryIDInput.getAttribute('value');
  }

  async setBillerCategoryInput(billerCategory) {
    await this.billerCategoryInput.sendKeys(billerCategory);
  }

  async getBillerCategoryInput() {
    return this.billerCategoryInput.getAttribute('value');
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
    await this.setBillercategoryIDInput('5');
    expect(await this.getBillercategoryIDInput()).to.eq('5');
    await waitUntilDisplayed(this.saveButton);
    await this.setBillerCategoryInput('billerCategory');
    expect(await this.getBillerCategoryInput()).to.match(/billerCategory/);
    await this.save();
    await waitUntilHidden(this.saveButton);
    expect(await isVisible(this.saveButton)).to.be.false;
  }
}
