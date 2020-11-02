import { element, by, ElementFinder, ElementArrayFinder } from 'protractor';

import { waitUntilAnyDisplayed, waitUntilDisplayed, click, waitUntilHidden, isVisible } from '../../util/utils';

import NavBarPage from './../../page-objects/navbar-page';

import BillerPlatformUpdatePage from './biller-platform-my-suffix-update.page-object';

const expect = chai.expect;
export class BillerPlatformDeleteDialog {
  deleteModal = element(by.className('modal'));
  private dialogTitle: ElementFinder = element(by.id('apiGatewayApp.billerPlatform.delete.question'));
  private confirmButton = element(by.id('jhi-confirm-delete-billerPlatform'));

  getDialogTitle() {
    return this.dialogTitle;
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}

export default class BillerPlatformComponentsPage {
  createButton: ElementFinder = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('div table .btn-danger'));
  title: ElementFinder = element(by.id('biller-platform-my-suffix-heading'));
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
    await navBarPage.getEntityPage('biller-platform-my-suffix');
    await waitUntilAnyDisplayed([this.noRecords, this.table]);
    return this;
  }

  async goToCreateBillerPlatform() {
    await this.createButton.click();
    return new BillerPlatformUpdatePage();
  }

  async deleteBillerPlatform() {
    const deleteButton = this.getDeleteButton(this.records.last());
    await click(deleteButton);

    const billerPlatformDeleteDialog = new BillerPlatformDeleteDialog();
    await waitUntilDisplayed(billerPlatformDeleteDialog.deleteModal);
    expect(await billerPlatformDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/apiGatewayApp.billerPlatform.delete.question/);
    await billerPlatformDeleteDialog.clickOnConfirmButton();

    await waitUntilHidden(billerPlatformDeleteDialog.deleteModal);

    expect(await isVisible(billerPlatformDeleteDialog.deleteModal)).to.be.false;
  }
}
