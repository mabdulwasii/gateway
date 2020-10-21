import { element, by, ElementFinder, ElementArrayFinder } from 'protractor';

import { waitUntilAnyDisplayed, waitUntilDisplayed, click, waitUntilHidden, isVisible } from '../../util/utils';

import NavBarPage from './../../page-objects/navbar-page';

import SchemeUpdatePage from './scheme-my-suffix-update.page-object';

const expect = chai.expect;
export class SchemeDeleteDialog {
  deleteModal = element(by.className('modal'));
  private dialogTitle: ElementFinder = element(by.id('apiGatewayApp.scheme.delete.question'));
  private confirmButton = element(by.id('jhi-confirm-delete-scheme'));

  getDialogTitle() {
    return this.dialogTitle;
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}

export default class SchemeComponentsPage {
  createButton: ElementFinder = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('div table .btn-danger'));
  title: ElementFinder = element(by.id('scheme-my-suffix-heading'));
  noRecords: ElementFinder = element(by.css('#app-view-container .table-responsive div.alert.alert-warning'));
  table: ElementFinder = element(by.css('#app-view-container div.table-responsive > table'));

  records: ElementArrayFinder = this.table.all(by.css('tbody tr'));

  getDetailsButton(record: ElementFinder) {
    return record.element(by.css('a.btn.btn-info.btn-sm'));
  }

  getEditButton(record: ElementFinder) {
    return record.element(by.css('a.btn.btn-primary.btn-sm'));
  }

  getDeleteButton(record: ElementFinder) {
    return record.element(by.css('a.btn.btn-danger.btn-sm'));
  }

  async goToPage(navBarPage: NavBarPage) {
    await navBarPage.getEntityPage('scheme-my-suffix');
    await waitUntilAnyDisplayed([this.noRecords, this.table]);
    return this;
  }

  async goToCreateScheme() {
    await this.createButton.click();
    return new SchemeUpdatePage();
  }

  async deleteScheme() {
    const deleteButton = this.getDeleteButton(this.records.last());
    await click(deleteButton);

    const schemeDeleteDialog = new SchemeDeleteDialog();
    await waitUntilDisplayed(schemeDeleteDialog.deleteModal);
    expect(await schemeDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/apiGatewayApp.scheme.delete.question/);
    await schemeDeleteDialog.clickOnConfirmButton();

    await waitUntilHidden(schemeDeleteDialog.deleteModal);

    expect(await isVisible(schemeDeleteDialog.deleteModal)).to.be.false;
  }
}
