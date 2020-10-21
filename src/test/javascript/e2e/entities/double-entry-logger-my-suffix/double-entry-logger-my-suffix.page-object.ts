import { element, by, ElementFinder, ElementArrayFinder } from 'protractor';

import { waitUntilAnyDisplayed, waitUntilDisplayed, click, waitUntilHidden, isVisible } from '../../util/utils';

import NavBarPage from './../../page-objects/navbar-page';

import DoubleEntryLoggerUpdatePage from './double-entry-logger-my-suffix-update.page-object';

const expect = chai.expect;
export class DoubleEntryLoggerDeleteDialog {
  deleteModal = element(by.className('modal'));
  private dialogTitle: ElementFinder = element(by.id('apiGatewayApp.doubleEntryLogger.delete.question'));
  private confirmButton = element(by.id('jhi-confirm-delete-doubleEntryLogger'));

  getDialogTitle() {
    return this.dialogTitle;
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}

export default class DoubleEntryLoggerComponentsPage {
  createButton: ElementFinder = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('div table .btn-danger'));
  title: ElementFinder = element(by.id('double-entry-logger-my-suffix-heading'));
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
    await navBarPage.getEntityPage('double-entry-logger-my-suffix');
    await waitUntilAnyDisplayed([this.noRecords, this.table]);
    return this;
  }

  async goToCreateDoubleEntryLogger() {
    await this.createButton.click();
    return new DoubleEntryLoggerUpdatePage();
  }

  async deleteDoubleEntryLogger() {
    const deleteButton = this.getDeleteButton(this.records.last());
    await click(deleteButton);

    const doubleEntryLoggerDeleteDialog = new DoubleEntryLoggerDeleteDialog();
    await waitUntilDisplayed(doubleEntryLoggerDeleteDialog.deleteModal);
    expect(await doubleEntryLoggerDeleteDialog.getDialogTitle().getAttribute('id')).to.match(
      /apiGatewayApp.doubleEntryLogger.delete.question/
    );
    await doubleEntryLoggerDeleteDialog.clickOnConfirmButton();

    await waitUntilHidden(doubleEntryLoggerDeleteDialog.deleteModal);

    expect(await isVisible(doubleEntryLoggerDeleteDialog.deleteModal)).to.be.false;
  }
}
