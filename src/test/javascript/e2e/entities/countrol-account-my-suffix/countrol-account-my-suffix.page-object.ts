import { element, by, ElementFinder, ElementArrayFinder } from 'protractor';

import { waitUntilAnyDisplayed, waitUntilDisplayed, click, waitUntilHidden, isVisible } from '../../util/utils';

import NavBarPage from './../../page-objects/navbar-page';

import CountrolAccountUpdatePage from './countrol-account-my-suffix-update.page-object';

const expect = chai.expect;
export class CountrolAccountDeleteDialog {
  deleteModal = element(by.className('modal'));
  private dialogTitle: ElementFinder = element(by.id('apiGatewayApp.countrolAccount.delete.question'));
  private confirmButton = element(by.id('jhi-confirm-delete-countrolAccount'));

  getDialogTitle() {
    return this.dialogTitle;
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}

export default class CountrolAccountComponentsPage {
  createButton: ElementFinder = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('div table .btn-danger'));
  title: ElementFinder = element(by.id('countrol-account-my-suffix-heading'));
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
    await navBarPage.getEntityPage('countrol-account-my-suffix');
    await waitUntilAnyDisplayed([this.noRecords, this.table]);
    return this;
  }

  async goToCreateCountrolAccount() {
    await this.createButton.click();
    return new CountrolAccountUpdatePage();
  }

  async deleteCountrolAccount() {
    const deleteButton = this.getDeleteButton(this.records.last());
    await click(deleteButton);

    const countrolAccountDeleteDialog = new CountrolAccountDeleteDialog();
    await waitUntilDisplayed(countrolAccountDeleteDialog.deleteModal);
    expect(await countrolAccountDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/apiGatewayApp.countrolAccount.delete.question/);
    await countrolAccountDeleteDialog.clickOnConfirmButton();

    await waitUntilHidden(countrolAccountDeleteDialog.deleteModal);

    expect(await isVisible(countrolAccountDeleteDialog.deleteModal)).to.be.false;
  }
}
