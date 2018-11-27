import { element, by, ElementFinder } from 'protractor';

export class ExtendedUserComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-extended-user div table .btn-danger'));
    title = element.all(by.css('jhi-extended-user div h2#page-heading span')).first();

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

export class ExtendedUserUpdatePage {
    pageTitle = element(by.id('jhi-extended-user-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    departmentSelect = element(by.id('field_department'));
    employeeTypeSelect = element(by.id('field_employeeType'));
    employeeSubTypeSelect = element(by.id('field_employeeSubType'));
    userSelect = element(by.id('field_user'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
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

    async userSelectLastOption() {
        await this.userSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async userSelectOption(option) {
        await this.userSelect.sendKeys(option);
    }

    getUserSelect(): ElementFinder {
        return this.userSelect;
    }

    async getUserSelectedOption() {
        return this.userSelect.element(by.css('option:checked')).getText();
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

export class ExtendedUserDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-extendedUser-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-extendedUser'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
