import { element, by, ElementFinder, ElementArrayFinder } from 'protractor';

import { waitUntilAnyDisplayed, waitUntilDisplayed, click, waitUntilHidden, isVisible } from '../../util/utils';

import NavBarPage from './../../page-objects/navbar-page';

import PaymentTransactionUpdatePage from './payment-transaction-my-suffix-update.page-object';

const expect = chai.expect;
export class PaymentTransactionDeleteDialog {
  deleteModal = element(by.className('modal'));
  private dialogTitle: ElementFinder = element(by.id('apiGatewayApp.paymentTransaction.delete.question'));
  private confirmButton = element(by.id('jhi-confirm-delete-paymentTransaction'));

  getDialogTitle() {
    return this.dialogTitle;
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}

export default class PaymentTransactionComponentsPage {
  createButton: ElementFinder = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('div table .btn-danger'));
  title: ElementFinder = element(by.id('payment-transaction-my-suffix-heading'));
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
    await navBarPage.getEntityPage('payment-transaction-my-suffix');
    await waitUntilAnyDisplayed([this.noRecords, this.table]);
    return this;
  }

  async goToCreatePaymentTransaction() {
    await this.createButton.click();
    return new PaymentTransactionUpdatePage();
  }

  async deletePaymentTransaction() {
    const deleteButton = this.getDeleteButton(this.records.last());
    await click(deleteButton);

    const paymentTransactionDeleteDialog = new PaymentTransactionDeleteDialog();
    await waitUntilDisplayed(paymentTransactionDeleteDialog.deleteModal);
    expect(await paymentTransactionDeleteDialog.getDialogTitle().getAttribute('id')).to.match(
      /apiGatewayApp.paymentTransaction.delete.question/
    );
    await paymentTransactionDeleteDialog.clickOnConfirmButton();

    await waitUntilHidden(paymentTransactionDeleteDialog.deleteModal);

    expect(await isVisible(paymentTransactionDeleteDialog.deleteModal)).to.be.false;
  }
}
