import { element, by, ElementFinder } from 'protractor';

export class SupportCoordinatorComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-support-coordinator div table .btn-danger'));
    title = element.all(by.css('jhi-support-coordinator div h2#page-heading span')).first();

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

export class SupportCoordinatorUpdatePage {
    pageTitle = element(by.id('jhi-support-coordinator-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    firstNameInput = element(by.id('field_firstName'));
    lastNameInput = element(by.id('field_lastName'));
    phoneInput = element(by.id('field_phone'));
    emailIdInput = element(by.id('field_emailId'));
    dateHiredInput = element(by.id('field_dateHired'));
    userNameInput = element(by.id('field_userName'));
    departmentSelect = element(by.id('field_department'));
    employeeTypeSelect = element(by.id('field_employeeType'));
    employeeSubTypeSelect = element(by.id('field_employeeSubType'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setFirstNameInput(firstName) {
        await this.firstNameInput.sendKeys(firstName);
    }

    async getFirstNameInput() {
        return this.firstNameInput.getAttribute('value');
    }

    async setLastNameInput(lastName) {
        await this.lastNameInput.sendKeys(lastName);
    }

    async getLastNameInput() {
        return this.lastNameInput.getAttribute('value');
    }

    async setPhoneInput(phone) {
        await this.phoneInput.sendKeys(phone);
    }

    async getPhoneInput() {
        return this.phoneInput.getAttribute('value');
    }

    async setEmailIdInput(emailId) {
        await this.emailIdInput.sendKeys(emailId);
    }

    async getEmailIdInput() {
        return this.emailIdInput.getAttribute('value');
    }

    async setDateHiredInput(dateHired) {
        await this.dateHiredInput.sendKeys(dateHired);
    }

    async getDateHiredInput() {
        return this.dateHiredInput.getAttribute('value');
    }

    async setUserNameInput(userName) {
        await this.userNameInput.sendKeys(userName);
    }

    async getUserNameInput() {
        return this.userNameInput.getAttribute('value');
    }

    async departmentSelectLastOption() {
        await this.departmentSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async departmentSelectOption(option) {
        await this.departmentSelect.sendKeys(option);
    }

    getDepartmentSelect(): ElementFinder {
        return this.departmentSelect;
    }

    async getDepartmentSelectedOption() {
        return this.departmentSelect.element(by.css('option:checked')).getText();
    }

    async employeeTypeSelectLastOption() {
        await this.employeeTypeSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async employeeTypeSelectOption(option) {
        await this.employeeTypeSelect.sendKeys(option);
    }

    getEmployeeTypeSelect(): ElementFinder {
        return this.employeeTypeSelect;
    }

    async getEmployeeTypeSelectedOption() {
        return this.employeeTypeSelect.element(by.css('option:checked')).getText();
    }

    async employeeSubTypeSelectLastOption() {
        await this.employeeSubTypeSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async employeeSubTypeSelectOption(option) {
        await this.employeeSubTypeSelect.sendKeys(option);
    }

    getEmployeeSubTypeSelect(): ElementFinder {
        return this.employeeSubTypeSelect;
    }

    async getEmployeeSubTypeSelectedOption() {
        return this.employeeSubTypeSelect.element(by.css('option:checked')).getText();
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

export class SupportCoordinatorDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-supportCoordinator-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-supportCoordinator'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
