import { element, by, ElementFinder, ElementArrayFinder } from 'protractor';

import { waitUntilAnyDisplayed, waitUntilDisplayed, click, waitUntilHidden, isVisible } from '../../util/utils';

import NavBarPage from './../../page-objects/navbar-page';

import BillerTransactionUpdatePage from './biller-transaction-my-suffix-update.page-object';

const expect = chai.expect;
export class BillerTransactionDeleteDialog {
  deleteModal = element(by.className('modal'));
  private dialogTitle: ElementFinder = element(by.id('apiGatewayApp.billerTransaction.delete.question'));
  private confirmButton = element(by.id('jhi-confirm-delete-billerTransaction'));

  getDialogTitle() {
    return this.dialogTitle;
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}

export default class BillerTransactionComponentsPage {
  createButton: ElementFinder = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('div table .btn-danger'));
  title: ElementFinder = element(by.id('biller-transaction-my-suffix-heading'));
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
    await navBarPage.getEntityPage('biller-transaction-my-suffix');
    await waitUntilAnyDisplayed([this.noRecords, this.table]);
    return this;
  }

  async goToCreateBillerTransaction() {
    await this.createButton.click();
    return new BillerTransactionUpdatePage();
  }

  async deleteBillerTransaction() {
    const deleteButton = this.getDeleteButton(this.records.last());
    await click(deleteButton);

    const billerTransactionDeleteDialog = new BillerTransactionDeleteDialog();
    await waitUntilDisplayed(billerTransactionDeleteDialog.deleteModal);
    expect(await billerTransactionDeleteDialog.getDialogTitle().getAttribute('id')).to.match(
      /apiGatewayApp.billerTransaction.delete.question/
    );
    await billerTransactionDeleteDialog.clickOnConfirmButton();

    await waitUntilHidden(billerTransactionDeleteDialog.deleteModal);

    expect(await isVisible(billerTransactionDeleteDialog.deleteModal)).to.be.false;
  }
}
