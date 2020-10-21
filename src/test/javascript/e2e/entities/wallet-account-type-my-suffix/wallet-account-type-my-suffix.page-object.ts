import { element, by, ElementFinder, ElementArrayFinder } from 'protractor';

import { waitUntilAnyDisplayed, waitUntilDisplayed, click, waitUntilHidden, isVisible } from '../../util/utils';

import NavBarPage from './../../page-objects/navbar-page';

import WalletAccountTypeUpdatePage from './wallet-account-type-my-suffix-update.page-object';

const expect = chai.expect;
export class WalletAccountTypeDeleteDialog {
  deleteModal = element(by.className('modal'));
  private dialogTitle: ElementFinder = element(by.id('apiGatewayApp.walletAccountType.delete.question'));
  private confirmButton = element(by.id('jhi-confirm-delete-walletAccountType'));

  getDialogTitle() {
    return this.dialogTitle;
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}

export default class WalletAccountTypeComponentsPage {
  createButton: ElementFinder = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('div table .btn-danger'));
  title: ElementFinder = element(by.id('wallet-account-type-my-suffix-heading'));
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
    await navBarPage.getEntityPage('wallet-account-type-my-suffix');
    await waitUntilAnyDisplayed([this.noRecords, this.table]);
    return this;
  }

  async goToCreateWalletAccountType() {
    await this.createButton.click();
    return new WalletAccountTypeUpdatePage();
  }

  async deleteWalletAccountType() {
    const deleteButton = this.getDeleteButton(this.records.last());
    await click(deleteButton);

    const walletAccountTypeDeleteDialog = new WalletAccountTypeDeleteDialog();
    await waitUntilDisplayed(walletAccountTypeDeleteDialog.deleteModal);
    expect(await walletAccountTypeDeleteDialog.getDialogTitle().getAttribute('id')).to.match(
      /apiGatewayApp.walletAccountType.delete.question/
    );
    await walletAccountTypeDeleteDialog.clickOnConfirmButton();

    await waitUntilHidden(walletAccountTypeDeleteDialog.deleteModal);

    expect(await isVisible(walletAccountTypeDeleteDialog.deleteModal)).to.be.false;
  }
}
