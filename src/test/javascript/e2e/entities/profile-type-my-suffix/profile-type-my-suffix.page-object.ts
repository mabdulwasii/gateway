import { element, by, ElementFinder, ElementArrayFinder } from 'protractor';

import { waitUntilAnyDisplayed, waitUntilDisplayed, click, waitUntilHidden, isVisible } from '../../util/utils';

import NavBarPage from './../../page-objects/navbar-page';

import ProfileTypeUpdatePage from './profile-type-my-suffix-update.page-object';

const expect = chai.expect;
export class ProfileTypeDeleteDialog {
  deleteModal = element(by.className('modal'));
  private dialogTitle: ElementFinder = element(by.id('apiGatewayApp.profileType.delete.question'));
  private confirmButton = element(by.id('jhi-confirm-delete-profileType'));

  getDialogTitle() {
    return this.dialogTitle;
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}

export default class ProfileTypeComponentsPage {
  createButton: ElementFinder = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('div table .btn-danger'));
  title: ElementFinder = element(by.id('profile-type-my-suffix-heading'));
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
    await navBarPage.getEntityPage('profile-type-my-suffix');
    await waitUntilAnyDisplayed([this.noRecords, this.table]);
    return this;
  }

  async goToCreateProfileType() {
    await this.createButton.click();
    return new ProfileTypeUpdatePage();
  }

  async deleteProfileType() {
    const deleteButton = this.getDeleteButton(this.records.last());
    await click(deleteButton);

    const profileTypeDeleteDialog = new ProfileTypeDeleteDialog();
    await waitUntilDisplayed(profileTypeDeleteDialog.deleteModal);
    expect(await profileTypeDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/apiGatewayApp.profileType.delete.question/);
    await profileTypeDeleteDialog.clickOnConfirmButton();

    await waitUntilHidden(profileTypeDeleteDialog.deleteModal);

    expect(await isVisible(profileTypeDeleteDialog.deleteModal)).to.be.false;
  }
}
