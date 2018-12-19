/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { ParticipantComponentsPage, ParticipantDeleteDialog, ParticipantUpdatePage } from './participant.page-object';

const expect = chai.expect;

describe('Participant e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let participantUpdatePage: ParticipantUpdatePage;
    let participantComponentsPage: ParticipantComponentsPage;
    let participantDeleteDialog: ParticipantDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load Participants', async () => {
        await navBarPage.goToEntity('participant');
        participantComponentsPage = new ParticipantComponentsPage();
        expect(await participantComponentsPage.getTitle()).to.eq('cwcrmApp.participant.home.title');
    });

    it('should load create Participant page', async () => {
        await participantComponentsPage.clickOnCreateButton();
        participantUpdatePage = new ParticipantUpdatePage();
        expect(await participantUpdatePage.getPageTitle()).to.eq('cwcrmApp.participant.home.createOrEditLabel');
        await participantUpdatePage.cancel();
    });

    it('should create and save Participants', async () => {
        const nbButtonsBeforeCreate = await participantComponentsPage.countDeleteButtons();

        await participantComponentsPage.clickOnCreateButton();
        await promise.all([
            participantUpdatePage.setFirstNameInput('firstName'),
            participantUpdatePage.setMiddleInitialInput('middleInitial'),
            participantUpdatePage.setLastNameInput('lastName'),
            participantUpdatePage.setTitleInput('title'),
            participantUpdatePage.setRegistrationDateInput('2000-12-31'),
            participantUpdatePage.setAddressInput('address'),
            participantUpdatePage.setCityInput('city'),
            participantUpdatePage.setStateInput('state'),
            participantUpdatePage.setCountryInput('country'),
            participantUpdatePage.setDobInput('2000-12-31'),
            participantUpdatePage.setPrimaryPhoneInput('primaryPhone'),
            participantUpdatePage.setPrimaryPhoneTypeInput('primaryPhoneType'),
            participantUpdatePage.setSecondaryPhoneInput('secondaryPhone'),
            participantUpdatePage.setSecondaryPhoneTypeInput('secondaryPhoneType'),
            participantUpdatePage.setEmailInput('email'),
            participantUpdatePage.setZipInput('zip'),
            participantUpdatePage.setMedicareIdNumberInput('medicareIdNumber'),
            participantUpdatePage.setMedicaidIdNumberInput('medicaidIdNumber'),
            participantUpdatePage.setGenderInput('gender'),
            participantUpdatePage.contactStatusSelectLastOption(),
            participantUpdatePage.contactSubStatusSelectLastOption(),
            participantUpdatePage.mcoSelectLastOption(),
            participantUpdatePage.referralTypeSelectLastOption(),
            participantUpdatePage.referralSourceSelectLastOption(),
            participantUpdatePage.assignedToSelectLastOption()
        ]);
        expect(await participantUpdatePage.getFirstNameInput()).to.eq('firstName');
        expect(await participantUpdatePage.getMiddleInitialInput()).to.eq('middleInitial');
        expect(await participantUpdatePage.getLastNameInput()).to.eq('lastName');
        expect(await participantUpdatePage.getTitleInput()).to.eq('title');
        expect(await participantUpdatePage.getRegistrationDateInput()).to.eq('2000-12-31');
        expect(await participantUpdatePage.getAddressInput()).to.eq('address');
        expect(await participantUpdatePage.getCityInput()).to.eq('city');
        expect(await participantUpdatePage.getStateInput()).to.eq('state');
        expect(await participantUpdatePage.getCountryInput()).to.eq('country');
        expect(await participantUpdatePage.getDobInput()).to.eq('2000-12-31');
        expect(await participantUpdatePage.getPrimaryPhoneInput()).to.eq('primaryPhone');
        expect(await participantUpdatePage.getPrimaryPhoneTypeInput()).to.eq('primaryPhoneType');
        expect(await participantUpdatePage.getSecondaryPhoneInput()).to.eq('secondaryPhone');
        expect(await participantUpdatePage.getSecondaryPhoneTypeInput()).to.eq('secondaryPhoneType');
        expect(await participantUpdatePage.getEmailInput()).to.eq('email');
        expect(await participantUpdatePage.getZipInput()).to.eq('zip');
        expect(await participantUpdatePage.getMedicareIdNumberInput()).to.eq('medicareIdNumber');
        expect(await participantUpdatePage.getMedicaidIdNumberInput()).to.eq('medicaidIdNumber');
        expect(await participantUpdatePage.getGenderInput()).to.eq('gender');
        await participantUpdatePage.save();
        expect(await participantUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await participantComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last Participant', async () => {
        const nbButtonsBeforeDelete = await participantComponentsPage.countDeleteButtons();
        await participantComponentsPage.clickOnLastDeleteButton();

        participantDeleteDialog = new ParticipantDeleteDialog();
        expect(await participantDeleteDialog.getDialogTitle()).to.eq('cwcrmApp.participant.delete.question');
        await participantDeleteDialog.clickOnConfirmButton();

        expect(await participantComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
