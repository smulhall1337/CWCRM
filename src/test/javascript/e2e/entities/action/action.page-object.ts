import { element, by, ElementFinder } from 'protractor';

export class ActionComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-action div table .btn-danger'));
    title = element.all(by.css('jhi-action div h2#page-heading span')).first();

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

export class ActionUpdatePage {
    pageTitle = element(by.id('jhi-action-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    dueDateInput = element(by.id('field_dueDate'));
    assignedToSelect = element(by.id('field_assignedTo'));
    participantSelect = element(by.id('field_participant'));
    prioritySelect = element(by.id('field_priority'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setDueDateInput(dueDate) {
        await this.dueDateInput.sendKeys(dueDate);
    }

    async getDueDateInput() {
        return this.dueDateInput.getAttribute('value');
    }

    async assignedToSelectLastOption() {
        await this.assignedToSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async assignedToSelectOption(option) {
        await this.assignedToSelect.sendKeys(option);
    }

    getAssignedToSelect(): ElementFinder {
        return this.assignedToSelect;
    }

    async getAssignedToSelectedOption() {
        return this.assignedToSelect.element(by.css('option:checked')).getText();
    }

    async participantSelectLastOption() {
        await this.participantSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async participantSelectOption(option) {
        await this.participantSelect.sendKeys(option);
    }

    getParticipantSelect(): ElementFinder {
        return this.participantSelect;
    }

    async getParticipantSelectedOption() {
        return this.participantSelect.element(by.css('option:checked')).getText();
    }

    async prioritySelectLastOption() {
        await this.prioritySelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async prioritySelectOption(option) {
        await this.prioritySelect.sendKeys(option);
    }

    getPrioritySelect(): ElementFinder {
        return this.prioritySelect;
    }

    async getPrioritySelectedOption() {
        return this.prioritySelect.element(by.css('option:checked')).getText();
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

export class ActionDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-action-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-action'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
