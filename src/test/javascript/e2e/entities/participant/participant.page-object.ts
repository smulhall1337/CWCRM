import { element, by, ElementFinder } from 'protractor';

export class ParticipantComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-participant div table .btn-danger'));
    title = element.all(by.css('jhi-participant div h2#page-heading span')).first();

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

export class ParticipantUpdatePage {
    pageTitle = element(by.id('jhi-participant-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    firstNameInput = element(by.id('field_firstName'));
    lastNameInput = element(by.id('field_lastName'));
    registrationDateInput = element(by.id('field_registrationDate'));
    address1Input = element(by.id('field_address1'));
    address2Input = element(by.id('field_address2'));
    cityInput = element(by.id('field_city'));
    stateInput = element(by.id('field_state'));
    countryInput = element(by.id('field_country'));
    dobInput = element(by.id('field_dob'));
    phoneInput = element(by.id('field_phone'));
    emailInput = element(by.id('field_email'));
    zipInput = element(by.id('field_zip'));
    manNumberInput = element(by.id('field_manNumber'));
    deceasedInput = element(by.id('field_deceased'));
    createdInput = element(by.id('field_created'));
    updatedInput = element(by.id('field_updated'));
    isActiveInput = element(by.id('field_isActive'));
    altContactInfoInput = element(by.id('field_altContactInfo'));
    contactStatusSelect = element(by.id('field_contactStatus'));
    contactSubStatusSelect = element(by.id('field_contactSubStatus'));
    waiverSelect = element(by.id('field_waiver'));
    mcoSelect = element(by.id('field_mco'));
    supportCoordinatorSelect = element(by.id('field_supportCoordinator'));
    primaryPhysicianSelect = element(by.id('field_primaryPhysician'));

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

    async setRegistrationDateInput(registrationDate) {
        await this.registrationDateInput.sendKeys(registrationDate);
    }

    async getRegistrationDateInput() {
        return this.registrationDateInput.getAttribute('value');
    }

    async setAddress1Input(address1) {
        await this.address1Input.sendKeys(address1);
    }

    async getAddress1Input() {
        return this.address1Input.getAttribute('value');
    }

    async setAddress2Input(address2) {
        await this.address2Input.sendKeys(address2);
    }

    async getAddress2Input() {
        return this.address2Input.getAttribute('value');
    }

    async setCityInput(city) {
        await this.cityInput.sendKeys(city);
    }

    async getCityInput() {
        return this.cityInput.getAttribute('value');
    }

    async setStateInput(state) {
        await this.stateInput.sendKeys(state);
    }

    async getStateInput() {
        return this.stateInput.getAttribute('value');
    }

    async setCountryInput(country) {
        await this.countryInput.sendKeys(country);
    }

    async getCountryInput() {
        return this.countryInput.getAttribute('value');
    }

    async setDobInput(dob) {
        await this.dobInput.sendKeys(dob);
    }

    async getDobInput() {
        return this.dobInput.getAttribute('value');
    }

    async setPhoneInput(phone) {
        await this.phoneInput.sendKeys(phone);
    }

    async getPhoneInput() {
        return this.phoneInput.getAttribute('value');
    }

    async setEmailInput(email) {
        await this.emailInput.sendKeys(email);
    }

    async getEmailInput() {
        return this.emailInput.getAttribute('value');
    }

    async setZipInput(zip) {
        await this.zipInput.sendKeys(zip);
    }

    async getZipInput() {
        return this.zipInput.getAttribute('value');
    }

    async setManNumberInput(manNumber) {
        await this.manNumberInput.sendKeys(manNumber);
    }

    async getManNumberInput() {
        return this.manNumberInput.getAttribute('value');
    }

    getDeceasedInput() {
        return this.deceasedInput;
    }
    async setCreatedInput(created) {
        await this.createdInput.sendKeys(created);
    }

    async getCreatedInput() {
        return this.createdInput.getAttribute('value');
    }

    async setUpdatedInput(updated) {
        await this.updatedInput.sendKeys(updated);
    }

    async getUpdatedInput() {
        return this.updatedInput.getAttribute('value');
    }

    getIsActiveInput() {
        return this.isActiveInput;
    }
    async setAltContactInfoInput(altContactInfo) {
        await this.altContactInfoInput.sendKeys(altContactInfo);
    }

    async getAltContactInfoInput() {
        return this.altContactInfoInput.getAttribute('value');
    }

    async contactStatusSelectLastOption() {
        await this.contactStatusSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async contactStatusSelectOption(option) {
        await this.contactStatusSelect.sendKeys(option);
    }

    getContactStatusSelect(): ElementFinder {
        return this.contactStatusSelect;
    }

    async getContactStatusSelectedOption() {
        return this.contactStatusSelect.element(by.css('option:checked')).getText();
    }

    async contactSubStatusSelectLastOption() {
        await this.contactSubStatusSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async contactSubStatusSelectOption(option) {
        await this.contactSubStatusSelect.sendKeys(option);
    }

    getContactSubStatusSelect(): ElementFinder {
        return this.contactSubStatusSelect;
    }

    async getContactSubStatusSelectedOption() {
        return this.contactSubStatusSelect.element(by.css('option:checked')).getText();
    }

    async waiverSelectLastOption() {
        await this.waiverSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async waiverSelectOption(option) {
        await this.waiverSelect.sendKeys(option);
    }

    getWaiverSelect(): ElementFinder {
        return this.waiverSelect;
    }

    async getWaiverSelectedOption() {
        return this.waiverSelect.element(by.css('option:checked')).getText();
    }

    async mcoSelectLastOption() {
        await this.mcoSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async mcoSelectOption(option) {
        await this.mcoSelect.sendKeys(option);
    }

    getMcoSelect(): ElementFinder {
        return this.mcoSelect;
    }

    async getMcoSelectedOption() {
        return this.mcoSelect.element(by.css('option:checked')).getText();
    }

    async supportCoordinatorSelectLastOption() {
        await this.supportCoordinatorSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async supportCoordinatorSelectOption(option) {
        await this.supportCoordinatorSelect.sendKeys(option);
    }

    getSupportCoordinatorSelect(): ElementFinder {
        return this.supportCoordinatorSelect;
    }

    async getSupportCoordinatorSelectedOption() {
        return this.supportCoordinatorSelect.element(by.css('option:checked')).getText();
    }

    async primaryPhysicianSelectLastOption() {
        await this.primaryPhysicianSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async primaryPhysicianSelectOption(option) {
        await this.primaryPhysicianSelect.sendKeys(option);
    }

    getPrimaryPhysicianSelect(): ElementFinder {
        return this.primaryPhysicianSelect;
    }

    async getPrimaryPhysicianSelectedOption() {
        return this.primaryPhysicianSelect.element(by.css('option:checked')).getText();
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

export class ParticipantDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-participant-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-participant'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
