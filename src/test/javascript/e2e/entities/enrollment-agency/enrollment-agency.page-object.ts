import { element, by, ElementFinder } from 'protractor';

export class EnrollmentAgencyComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-enrollment-agency div table .btn-danger'));
    title = element.all(by.css('jhi-enrollment-agency div h2#page-heading span')).first();

    async clickOnCreateButton() {
        await this.createButton.click();
    }

    async clickOnLastDeleteButton() {
        await this.deleteButtons.last().click();
    }

    async countDeleteButtons() {
        return this.deleteButtons.count();
    }

    async getTitle() {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class EnrollmentAgencyUpdatePage {
    pageTitle = element(by.id('jhi-enrollment-agency-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    nameInput = element(by.id('field_name'));
    officeInput = element(by.id('field_office'));
    recordNumberInput = element(by.id('field_recordNumber'));
    phoneInput = element(by.id('field_phone'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setNameInput(name) {
        await this.nameInput.sendKeys(name);
    }

    async getNameInput() {
        return this.nameInput.getAttribute('value');
    }

    async setOfficeInput(office) {
        await this.officeInput.sendKeys(office);
    }

    async getOfficeInput() {
        return this.officeInput.getAttribute('value');
    }

    async setRecordNumberInput(recordNumber) {
        await this.recordNumberInput.sendKeys(recordNumber);
    }

    async getRecordNumberInput() {
        return this.recordNumberInput.getAttribute('value');
    }

    async setPhoneInput(phone) {
        await this.phoneInput.sendKeys(phone);
    }

    async getPhoneInput() {
        return this.phoneInput.getAttribute('value');
    }

    async save() {
        await this.saveButton.click();
    }

    async cancel() {
        await this.cancelButton.click();
    }

    getSaveButton(): ElementFinder {
        return this.saveButton;
    }
}

export class EnrollmentAgencyDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-enrollmentAgency-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-enrollmentAgency'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
