/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { EnrollmentAgencyComponentsPage, EnrollmentAgencyDeleteDialog, EnrollmentAgencyUpdatePage } from './enrollment-agency.page-object';

const expect = chai.expect;

describe('EnrollmentAgency e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let enrollmentAgencyUpdatePage: EnrollmentAgencyUpdatePage;
    let enrollmentAgencyComponentsPage: EnrollmentAgencyComponentsPage;
    let enrollmentAgencyDeleteDialog: EnrollmentAgencyDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load EnrollmentAgencies', async () => {
        await navBarPage.goToEntity('enrollment-agency');
        enrollmentAgencyComponentsPage = new EnrollmentAgencyComponentsPage();
        expect(await enrollmentAgencyComponentsPage.getTitle()).to.eq('cwcrmApp.enrollmentAgency.home.title');
    });

    it('should load create EnrollmentAgency page', async () => {
        await enrollmentAgencyComponentsPage.clickOnCreateButton();
        enrollmentAgencyUpdatePage = new EnrollmentAgencyUpdatePage();
        expect(await enrollmentAgencyUpdatePage.getPageTitle()).to.eq('cwcrmApp.enrollmentAgency.home.createOrEditLabel');
        await enrollmentAgencyUpdatePage.cancel();
    });

    it('should create and save EnrollmentAgencies', async () => {
        const nbButtonsBeforeCreate = await enrollmentAgencyComponentsPage.countDeleteButtons();

        await enrollmentAgencyComponentsPage.clickOnCreateButton();
        await promise.all([
            enrollmentAgencyUpdatePage.setNameInput('name'),
            enrollmentAgencyUpdatePage.setOfficeInput('office'),
            enrollmentAgencyUpdatePage.setRecordNumberInput('recordNumber'),
            enrollmentAgencyUpdatePage.setPhoneInput('phone')
        ]);
        expect(await enrollmentAgencyUpdatePage.getNameInput()).to.eq('name');
        expect(await enrollmentAgencyUpdatePage.getOfficeInput()).to.eq('office');
        expect(await enrollmentAgencyUpdatePage.getRecordNumberInput()).to.eq('recordNumber');
        expect(await enrollmentAgencyUpdatePage.getPhoneInput()).to.eq('phone');
        await enrollmentAgencyUpdatePage.save();
        expect(await enrollmentAgencyUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await enrollmentAgencyComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last EnrollmentAgency', async () => {
        const nbButtonsBeforeDelete = await enrollmentAgencyComponentsPage.countDeleteButtons();
        await enrollmentAgencyComponentsPage.clickOnLastDeleteButton();

        enrollmentAgencyDeleteDialog = new EnrollmentAgencyDeleteDialog();
        expect(await enrollmentAgencyDeleteDialog.getDialogTitle()).to.eq('cwcrmApp.enrollmentAgency.delete.question');
        await enrollmentAgencyDeleteDialog.clickOnConfirmButton();

        expect(await enrollmentAgencyComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
