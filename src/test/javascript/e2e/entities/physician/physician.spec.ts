/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { PhysicianComponentsPage, PhysicianDeleteDialog, PhysicianUpdatePage } from './physician.page-object';

const expect = chai.expect;

describe('Physician e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let physicianUpdatePage: PhysicianUpdatePage;
    let physicianComponentsPage: PhysicianComponentsPage;
    let physicianDeleteDialog: PhysicianDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load Physicians', async () => {
        await navBarPage.goToEntity('physician');
        physicianComponentsPage = new PhysicianComponentsPage();
        expect(await physicianComponentsPage.getTitle()).to.eq('cwcrmApp.physician.home.title');
    });

    it('should load create Physician page', async () => {
        await physicianComponentsPage.clickOnCreateButton();
        physicianUpdatePage = new PhysicianUpdatePage();
        expect(await physicianUpdatePage.getPageTitle()).to.eq('cwcrmApp.physician.home.createOrEditLabel');
        await physicianUpdatePage.cancel();
    });

    it('should create and save Physicians', async () => {
        const nbButtonsBeforeCreate = await physicianComponentsPage.countDeleteButtons();

        await physicianComponentsPage.clickOnCreateButton();
        await promise.all([
            physicianUpdatePage.setFirstNameInput('firstName'),
            physicianUpdatePage.setLastNameInput('lastName'),
            physicianUpdatePage.setPhoneInput('phone'),
            physicianUpdatePage.setFaxInput('fax'),
            physicianUpdatePage.setAddressInput('address'),
            physicianUpdatePage.setNotesInput('notes'),
            physicianUpdatePage.setDisabilityInput('disability')
        ]);
        expect(await physicianUpdatePage.getFirstNameInput()).to.eq('firstName');
        expect(await physicianUpdatePage.getLastNameInput()).to.eq('lastName');
        expect(await physicianUpdatePage.getPhoneInput()).to.eq('phone');
        expect(await physicianUpdatePage.getFaxInput()).to.eq('fax');
        expect(await physicianUpdatePage.getAddressInput()).to.eq('address');
        expect(await physicianUpdatePage.getNotesInput()).to.eq('notes');
        expect(await physicianUpdatePage.getDisabilityInput()).to.eq('disability');
        await physicianUpdatePage.save();
        expect(await physicianUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await physicianComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last Physician', async () => {
        const nbButtonsBeforeDelete = await physicianComponentsPage.countDeleteButtons();
        await physicianComponentsPage.clickOnLastDeleteButton();

        physicianDeleteDialog = new PhysicianDeleteDialog();
        expect(await physicianDeleteDialog.getDialogTitle()).to.eq('cwcrmApp.physician.delete.question');
        await physicianDeleteDialog.clickOnConfirmButton();

        expect(await physicianComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
