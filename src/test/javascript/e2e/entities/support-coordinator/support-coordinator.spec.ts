/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
    SupportCoordinatorComponentsPage,
    SupportCoordinatorDeleteDialog,
    SupportCoordinatorUpdatePage
} from './support-coordinator.page-object';

const expect = chai.expect;

describe('SupportCoordinator e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let supportCoordinatorUpdatePage: SupportCoordinatorUpdatePage;
    let supportCoordinatorComponentsPage: SupportCoordinatorComponentsPage;
    /*let supportCoordinatorDeleteDialog: SupportCoordinatorDeleteDialog;*/

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load SupportCoordinators', async () => {
        await navBarPage.goToEntity('support-coordinator');
        supportCoordinatorComponentsPage = new SupportCoordinatorComponentsPage();
        expect(await supportCoordinatorComponentsPage.getTitle()).to.eq('cwcrmApp.supportCoordinator.home.title');
    });

    it('should load create SupportCoordinator page', async () => {
        await supportCoordinatorComponentsPage.clickOnCreateButton();
        supportCoordinatorUpdatePage = new SupportCoordinatorUpdatePage();
        expect(await supportCoordinatorUpdatePage.getPageTitle()).to.eq('cwcrmApp.supportCoordinator.home.createOrEditLabel');
        await supportCoordinatorUpdatePage.cancel();
    });

    /* it('should create and save SupportCoordinators', async () => {
        const nbButtonsBeforeCreate = await supportCoordinatorComponentsPage.countDeleteButtons();

        await supportCoordinatorComponentsPage.clickOnCreateButton();
        await promise.all([
            supportCoordinatorUpdatePage.setFirstNameInput('firstName'),
            supportCoordinatorUpdatePage.setLastNameInput('lastName'),
            supportCoordinatorUpdatePage.setPhoneInput('phone'),
            supportCoordinatorUpdatePage.setEmailIdInput('emailId'),
            supportCoordinatorUpdatePage.setDateHiredInput('2000-12-31'),
            supportCoordinatorUpdatePage.setUserNameInput('userName'),
            supportCoordinatorUpdatePage.departmentSelectLastOption(),
            supportCoordinatorUpdatePage.employeeTypeSelectLastOption(),
            supportCoordinatorUpdatePage.employeeSubTypeSelectLastOption(),
        ]);
        expect(await supportCoordinatorUpdatePage.getFirstNameInput()).to.eq('firstName');
        expect(await supportCoordinatorUpdatePage.getLastNameInput()).to.eq('lastName');
        expect(await supportCoordinatorUpdatePage.getPhoneInput()).to.eq('phone');
        expect(await supportCoordinatorUpdatePage.getEmailIdInput()).to.eq('emailId');
        expect(await supportCoordinatorUpdatePage.getDateHiredInput()).to.eq('2000-12-31');
        expect(await supportCoordinatorUpdatePage.getUserNameInput()).to.eq('userName');
        await supportCoordinatorUpdatePage.save();
        expect(await supportCoordinatorUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await supportCoordinatorComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });*/

    /* it('should delete last SupportCoordinator', async () => {
        const nbButtonsBeforeDelete = await supportCoordinatorComponentsPage.countDeleteButtons();
        await supportCoordinatorComponentsPage.clickOnLastDeleteButton();

        supportCoordinatorDeleteDialog = new SupportCoordinatorDeleteDialog();
        expect(await supportCoordinatorDeleteDialog.getDialogTitle())
            .to.eq('cwcrmApp.supportCoordinator.delete.question');
        await supportCoordinatorDeleteDialog.clickOnConfirmButton();

        expect(await supportCoordinatorComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });*/

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
