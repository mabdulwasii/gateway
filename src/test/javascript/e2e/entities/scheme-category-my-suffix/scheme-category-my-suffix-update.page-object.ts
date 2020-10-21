import { element, by, ElementFinder } from 'protractor';
import { waitUntilDisplayed, waitUntilHidden, isVisible } from '../../util/utils';

const expect = chai.expect;

export default class SchemeCategoryUpdatePage {
  pageTitle: ElementFinder = element(by.id('apiGatewayApp.schemeCategory.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  schemecategoryIDInput: ElementFinder = element(by.css('input#scheme-category-my-suffix-schemecategoryID'));
  schemeCategoryInput: ElementFinder = element(by.css('input#scheme-category-my-suffix-schemeCategory'));
  descriptionInput: ElementFinder = element(by.css('input#scheme-category-my-suffix-description'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setSchemecategoryIDInput(schemecategoryID) {
    await this.schemecategoryIDInput.sendKeys(schemecategoryID);
  }

  async getSchemecategoryIDInput() {
    return this.schemecategoryIDInput.getAttribute('value');
  }

  async setSchemeCategoryInput(schemeCategory) {
    await this.schemeCategoryInput.sendKeys(schemeCategory);
  }

  async getSchemeCategoryInput() {
    return this.schemeCategoryInput.getAttribute('value');
  }

  async setDescriptionInput(description) {
    await this.descriptionInput.sendKeys(description);
  }

  async getDescriptionInput() {
    return this.descriptionInput.getAttribute('value');
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
    await this.setSchemecategoryIDInput('5');
    expect(await this.getSchemecategoryIDInput()).to.eq('5');
    await waitUntilDisplayed(this.saveButton);
    await this.setSchemeCategoryInput('schemeCategory');
    expect(await this.getSchemeCategoryInput()).to.match(/schemeCategory/);
    await waitUntilDisplayed(this.saveButton);
    await this.setDescriptionInput('description');
    expect(await this.getDescriptionInput()).to.match(/description/);
    await this.save();
    await waitUntilHidden(this.saveButton);
    expect(await isVisible(this.saveButton)).to.be.false;
  }
}
