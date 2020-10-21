import { element, by, ElementFinder, ElementArrayFinder } from 'protractor';

import { waitUntilAnyDisplayed, waitUntilDisplayed, click, waitUntilHidden, isVisible } from '../../util/utils';

import NavBarPage from './../../page-objects/navbar-page';

import BillerUpdatePage from './biller-my-suffix-update.page-object';

const expect = chai.expect;
export class BillerDeleteDialog {
  deleteModal = element(by.className('modal'));
  private dialogTitle: ElementFinder = element(by.id('apiGatewayApp.biller.delete.question'));
  private confirmButton = element(by.id('jhi-confirm-delete-biller'));

  getDialogTitle() {
    return this.dialogTitle;
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}

export default class BillerComponentsPage {
  createButton: ElementFinder = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('div table .btn-danger'));
  title: ElementFinder = element(by.id('biller-my-suffix-heading'));
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
    await navBarPage.getEntityPage('biller-my-suffix');
    await waitUntilAnyDisplayed([this.noRecords, this.table]);
    return this;
  }

  async goToCreateBiller() {
    await this.createButton.click();
    return new BillerUpdatePage();
  }

  async deleteBiller() {
    const deleteButton = this.getDeleteButton(this.records.last());
    await click(deleteButton);

    const billerDeleteDialog = new BillerDeleteDialog();
    await waitUntilDisplayed(billerDeleteDialog.deleteModal);
    expect(await billerDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/apiGatewayApp.biller.delete.question/);
    await billerDeleteDialog.clickOnConfirmButton();

    await waitUntilHidden(billerDeleteDialog.deleteModal);

    expect(await isVisible(billerDeleteDialog.deleteModal)).to.be.false;
  }
}
