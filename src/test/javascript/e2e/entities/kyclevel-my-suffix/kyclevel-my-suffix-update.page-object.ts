import { element, by, ElementFinder } from 'protractor';
import { waitUntilDisplayed, waitUntilHidden, isVisible } from '../../util/utils';

const expect = chai.expect;

export default class KyclevelUpdatePage {
  pageTitle: ElementFinder = element(by.id('apiGatewayApp.kyclevel.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  kycIDInput: ElementFinder = element(by.css('input#kyclevel-my-suffix-kycID'));
  kycInput: ElementFinder = element(by.css('input#kyclevel-my-suffix-kyc'));
  descriptionInput: ElementFinder = element(by.css('input#kyclevel-my-suffix-description'));
  kycLevelInput: ElementFinder = element(by.css('input#kyclevel-my-suffix-kycLevel'));
  phoneNumberInput: ElementFinder = element(by.css('input#kyclevel-my-suffix-phoneNumber'));
  emailAddressInput: ElementFinder = element(by.css('input#kyclevel-my-suffix-emailAddress'));
  firstNameInput: ElementFinder = element(by.css('input#kyclevel-my-suffix-firstName'));
  lastNameInput: ElementFinder = element(by.css('input#kyclevel-my-suffix-lastName'));
  genderInput: ElementFinder = element(by.css('input#kyclevel-my-suffix-gender'));
  dateofBirthInput: ElementFinder = element(by.css('input#kyclevel-my-suffix-dateofBirth'));
  addressInput: ElementFinder = element(by.css('input#kyclevel-my-suffix-address'));
  photoUploadInput: ElementFinder = element(by.css('input#kyclevel-my-suffix-photoUpload'));
  verifiedBVNInput: ElementFinder = element(by.css('input#kyclevel-my-suffix-verifiedBVN'));
  verifiedValidIDInput: ElementFinder = element(by.css('input#kyclevel-my-suffix-verifiedValidID'));
  evidenceofAddressInput: ElementFinder = element(by.css('input#kyclevel-my-suffix-evidenceofAddress'));
  verificationofAddressInput: ElementFinder = element(by.css('input#kyclevel-my-suffix-verificationofAddress'));
  employmentDetailsInput: ElementFinder = element(by.css('input#kyclevel-my-suffix-employmentDetails'));
  dailyTransactionLimitInput: ElementFinder = element(by.css('input#kyclevel-my-suffix-dailyTransactionLimit'));
  cumulativeBalanceLimitInput: ElementFinder = element(by.css('input#kyclevel-my-suffix-cumulativeBalanceLimit'));
  paymentTransactionInput: ElementFinder = element(by.css('input#kyclevel-my-suffix-paymentTransaction'));
  billerTransactionInput: ElementFinder = element(by.css('input#kyclevel-my-suffix-billerTransaction'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setKycIDInput(kycID) {
    await this.kycIDInput.sendKeys(kycID);
  }

  async getKycIDInput() {
    return this.kycIDInput.getAttribute('value');
  }

  async setKycInput(kyc) {
    await this.kycInput.sendKeys(kyc);
  }

  async getKycInput() {
    return this.kycInput.getAttribute('value');
  }

  async setDescriptionInput(description) {
    await this.descriptionInput.sendKeys(description);
  }

  async getDescriptionInput() {
    return this.descriptionInput.getAttribute('value');
  }

  async setKycLevelInput(kycLevel) {
    await this.kycLevelInput.sendKeys(kycLevel);
  }

  async getKycLevelInput() {
    return this.kycLevelInput.getAttribute('value');
  }

  getPhoneNumberInput() {
    return this.phoneNumberInput;
  }
  getEmailAddressInput() {
    return this.emailAddressInput;
  }
  getFirstNameInput() {
    return this.firstNameInput;
  }
  getLastNameInput() {
    return this.lastNameInput;
  }
  getGenderInput() {
    return this.genderInput;
  }
  getDateofBirthInput() {
    return this.dateofBirthInput;
  }
  getAddressInput() {
    return this.addressInput;
  }
  getPhotoUploadInput() {
    return this.photoUploadInput;
  }
  getVerifiedBVNInput() {
    return this.verifiedBVNInput;
  }
  getVerifiedValidIDInput() {
    return this.verifiedValidIDInput;
  }
  getEvidenceofAddressInput() {
    return this.evidenceofAddressInput;
  }
  getVerificationofAddressInput() {
    return this.verificationofAddressInput;
  }
  getEmploymentDetailsInput() {
    return this.employmentDetailsInput;
  }
  async setDailyTransactionLimitInput(dailyTransactionLimit) {
    await this.dailyTransactionLimitInput.sendKeys(dailyTransactionLimit);
  }

  async getDailyTransactionLimitInput() {
    return this.dailyTransactionLimitInput.getAttribute('value');
  }

  async setCumulativeBalanceLimitInput(cumulativeBalanceLimit) {
    await this.cumulativeBalanceLimitInput.sendKeys(cumulativeBalanceLimit);
  }

  async getCumulativeBalanceLimitInput() {
    return this.cumulativeBalanceLimitInput.getAttribute('value');
  }

  getPaymentTransactionInput() {
    return this.paymentTransactionInput;
  }
  getBillerTransactionInput() {
    return this.billerTransactionInput;
  }
  async save() {
    await this.saveButton.click();
  }

  async cancel() {
    await this.cancelButton.click();
  }

  getSaveButton() {
    return this.saveButton;
  }

  async enterData() {
    await waitUntilDisplayed(this.saveButton);
    await this.setKycIDInput('5');
    expect(await this.getKycIDInput()).to.eq('5');
    await waitUntilDisplayed(this.saveButton);
    await this.setKycInput('kyc');
    expect(await this.getKycInput()).to.match(/kyc/);
    await waitUntilDisplayed(this.saveButton);
    await this.setDescriptionInput('description');
    expect(await this.getDescriptionInput()).to.match(/description/);
    await waitUntilDisplayed(this.saveButton);
    await this.setKycLevelInput('5');
    expect(await this.getKycLevelInput()).to.eq('5');
    await waitUntilDisplayed(this.saveButton);
    const selectedPhoneNumber = await this.getPhoneNumberInput().isSelected();
    if (selectedPhoneNumber) {
      await this.getPhoneNumberInput().click();
      expect(await this.getPhoneNumberInput().isSelected()).to.be.false;
    } else {
      await this.getPhoneNumberInput().click();
      expect(await this.getPhoneNumberInput().isSelected()).to.be.true;
    }
    await waitUntilDisplayed(this.saveButton);
    const selectedEmailAddress = await this.getEmailAddressInput().isSelected();
    if (selectedEmailAddress) {
      await this.getEmailAddressInput().click();
      expect(await this.getEmailAddressInput().isSelected()).to.be.false;
    } else {
      await this.getEmailAddressInput().click();
      expect(await this.getEmailAddressInput().isSelected()).to.be.true;
    }
    await waitUntilDisplayed(this.saveButton);
    const selectedFirstName = await this.getFirstNameInput().isSelected();
    if (selectedFirstName) {
      await this.getFirstNameInput().click();
      expect(await this.getFirstNameInput().isSelected()).to.be.false;
    } else {
      await this.getFirstNameInput().click();
      expect(await this.getFirstNameInput().isSelected()).to.be.true;
    }
    await waitUntilDisplayed(this.saveButton);
    const selectedLastName = await this.getLastNameInput().isSelected();
    if (selectedLastName) {
      await this.getLastNameInput().click();
      expect(await this.getLastNameInput().isSelected()).to.be.false;
    } else {
      await this.getLastNameInput().click();
      expect(await this.getLastNameInput().isSelected()).to.be.true;
    }
    await waitUntilDisplayed(this.saveButton);
    const selectedGender = await this.getGenderInput().isSelected();
    if (selectedGender) {
      await this.getGenderInput().click();
      expect(await this.getGenderInput().isSelected()).to.be.false;
    } else {
      await this.getGenderInput().click();
      expect(await this.getGenderInput().isSelected()).to.be.true;
    }
    await waitUntilDisplayed(this.saveButton);
    const selectedDateofBirth = await this.getDateofBirthInput().isSelected();
    if (selectedDateofBirth) {
      await this.getDateofBirthInput().click();
      expect(await this.getDateofBirthInput().isSelected()).to.be.false;
    } else {
      await this.getDateofBirthInput().click();
      expect(await this.getDateofBirthInput().isSelected()).to.be.true;
    }
    await waitUntilDisplayed(this.saveButton);
    const selectedAddress = await this.getAddressInput().isSelected();
    if (selectedAddress) {
      await this.getAddressInput().click();
      expect(await this.getAddressInput().isSelected()).to.be.false;
    } else {
      await this.getAddressInput().click();
      expect(await this.getAddressInput().isSelected()).to.be.true;
    }
    await waitUntilDisplayed(this.saveButton);
    const selectedPhotoUpload = await this.getPhotoUploadInput().isSelected();
    if (selectedPhotoUpload) {
      await this.getPhotoUploadInput().click();
      expect(await this.getPhotoUploadInput().isSelected()).to.be.false;
    } else {
      await this.getPhotoUploadInput().click();
      expect(await this.getPhotoUploadInput().isSelected()).to.be.true;
    }
    await waitUntilDisplayed(this.saveButton);
    const selectedVerifiedBVN = await this.getVerifiedBVNInput().isSelected();
    if (selectedVerifiedBVN) {
      await this.getVerifiedBVNInput().click();
      expect(await this.getVerifiedBVNInput().isSelected()).to.be.false;
    } else {
      await this.getVerifiedBVNInput().click();
      expect(await this.getVerifiedBVNInput().isSelected()).to.be.true;
    }
    await waitUntilDisplayed(this.saveButton);
    const selectedVerifiedValidID = await this.getVerifiedValidIDInput().isSelected();
    if (selectedVerifiedValidID) {
      await this.getVerifiedValidIDInput().click();
      expect(await this.getVerifiedValidIDInput().isSelected()).to.be.false;
    } else {
      await this.getVerifiedValidIDInput().click();
      expect(await this.getVerifiedValidIDInput().isSelected()).to.be.true;
    }
    await waitUntilDisplayed(this.saveButton);
    const selectedEvidenceofAddress = await this.getEvidenceofAddressInput().isSelected();
    if (selectedEvidenceofAddress) {
      await this.getEvidenceofAddressInput().click();
      expect(await this.getEvidenceofAddressInput().isSelected()).to.be.false;
    } else {
      await this.getEvidenceofAddressInput().click();
      expect(await this.getEvidenceofAddressInput().isSelected()).to.be.true;
    }
    await waitUntilDisplayed(this.saveButton);
    const selectedVerificationofAddress = await this.getVerificationofAddressInput().isSelected();
    if (selectedVerificationofAddress) {
      await this.getVerificationofAddressInput().click();
      expect(await this.getVerificationofAddressInput().isSelected()).to.be.false;
    } else {
      await this.getVerificationofAddressInput().click();
      expect(await this.getVerificationofAddressInput().isSelected()).to.be.true;
    }
    await waitUntilDisplayed(this.saveButton);
    const selectedEmploymentDetails = await this.getEmploymentDetailsInput().isSelected();
    if (selectedEmploymentDetails) {
      await this.getEmploymentDetailsInput().click();
      expect(await this.getEmploymentDetailsInput().isSelected()).to.be.false;
    } else {
      await this.getEmploymentDetailsInput().click();
      expect(await this.getEmploymentDetailsInput().isSelected()).to.be.true;
    }
    await waitUntilDisplayed(this.saveButton);
    await this.setDailyTransactionLimitInput('5');
    expect(await this.getDailyTransactionLimitInput()).to.eq('5');
    await waitUntilDisplayed(this.saveButton);
    await this.setCumulativeBalanceLimitInput('5');
    expect(await this.getCumulativeBalanceLimitInput()).to.eq('5');
    await waitUntilDisplayed(this.saveButton);
    const selectedPaymentTransaction = await this.getPaymentTransactionInput().isSelected();
    if (selectedPaymentTransaction) {
      await this.getPaymentTransactionInput().click();
      expect(await this.getPaymentTransactionInput().isSelected()).to.be.false;
    } else {
      await this.getPaymentTransactionInput().click();
      expect(await this.getPaymentTransactionInput().isSelected()).to.be.true;
    }
    await waitUntilDisplayed(this.saveButton);
    const selectedBillerTransaction = await this.getBillerTransactionInput().isSelected();
    if (selectedBillerTransaction) {
      await this.getBillerTransactionInput().click();
      expect(await this.getBillerTransactionInput().isSelected()).to.be.false;
    } else {
      await this.getBillerTransactionInput().click();
      expect(await this.getBillerTransactionInput().isSelected()).to.be.true;
    }
    await this.save();
    await waitUntilHidden(this.saveButton);
    expect(await isVisible(this.saveButton)).to.be.false;
  }
}
