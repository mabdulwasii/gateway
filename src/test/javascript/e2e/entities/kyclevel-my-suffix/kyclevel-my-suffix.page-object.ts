import { element, by, ElementFinder, ElementArrayFinder } from 'protractor';

import { waitUntilAnyDisplayed, waitUntilDisplayed, click, waitUntilHidden, isVisible } from '../../util/utils';

import NavBarPage from './../../page-objects/navbar-page';

import KyclevelUpdatePage from './kyclevel-my-suffix-update.page-object';

const expect = chai.expect;
export class KyclevelDeleteDialog {
  deleteModal = element(by.className('modal'));
  private dialogTitle: ElementFinder = element(by.id('apiGatewayApp.kyclevel.delete.question'));
  private confirmButton = element(by.id('jhi-confirm-delete-kyclevel'));

  getDialogTitle() {
    return this.dialogTitle;
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}

export default class KyclevelComponentsPage {
  createButton: ElementFinder = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('div table .btn-danger'));
  title: ElementFinder = element(by.id('kyclevel-my-suffix-heading'));
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
    await navBarPage.getEntityPage('kyclevel-my-suffix');
    await waitUntilAnyDisplayed([this.noRecords, this.table]);
    return this;
  }

  async goToCreateKyclevel() {
    await this.createButton.click();
    return new KyclevelUpdatePage();
  }

  async deleteKyclevel() {
    const deleteButton = this.getDeleteButton(this.records.last());
    await click(deleteButton);

    const kyclevelDeleteDialog = new KyclevelDeleteDialog();
    await waitUntilDisplayed(kyclevelDeleteDialog.deleteModal);
    expect(await kyclevelDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/apiGatewayApp.kyclevel.delete.question/);
    await kyclevelDeleteDialog.clickOnConfirmButton();

    await waitUntilHidden(kyclevelDeleteDialog.deleteModal);

    expect(await isVisible(kyclevelDeleteDialog.deleteModal)).to.be.false;
  }
}
