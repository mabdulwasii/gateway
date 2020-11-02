import { element, by, ElementFinder } from 'protractor';
import { waitUntilDisplayed, waitUntilHidden, isVisible } from '../../util/utils';

const expect = chai.expect;

export default class SchemeUpdatePage {
  pageTitle: ElementFinder = element(by.id('apiGatewayApp.scheme.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  schemeIDInput: ElementFinder = element(by.css('input#scheme-my-suffix-schemeID'));
  schemeInput: ElementFinder = element(by.css('input#scheme-my-suffix-scheme'));
  schemeCategorySelect: ElementFinder = element(by.css('select#scheme-my-suffix-schemeCategory'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setSchemeIDInput(schemeID) {
    await this.schemeIDInput.sendKeys(schemeID);
  }

  async getSchemeIDInput() {
    return this.schemeIDInput.getAttribute('value');
  }

  async setSchemeInput(scheme) {
    await this.schemeInput.sendKeys(scheme);
  }

  async getSchemeInput() {
    return this.schemeInput.getAttribute('value');
  }

  async schemeCategorySelectLastOption() {
    await this.schemeCategorySelect.all(by.tagName('option')).last().click();
  }

  async schemeCategorySelectOption(option) {
    await this.schemeCategorySelect.sendKeys(option);
  }

  getSchemeCategorySelect() {
    return this.schemeCategorySelect;
  }

  async getSchemeCategorySelectedOption() {
    return this.schemeCategorySelect.element(by.css('option:checked')).getText();
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
    await this.setSchemeIDInput('5');
    expect(await this.getSchemeIDInput()).to.eq('5');
    await waitUntilDisplayed(this.saveButton);
    await this.setSchemeInput('scheme');
    expect(await this.getSchemeInput()).to.match(/scheme/);
    await this.schemeCategorySelectLastOption();
    await this.save();
    await waitUntilHidden(this.saveButton);
    expect(await isVisible(this.saveButton)).to.be.false;
  }
}
