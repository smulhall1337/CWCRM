/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { EmployeeTypeComponentsPage, EmployeeTypeDeleteDialog, EmployeeTypeUpdatePage } from './employee-type.page-object';

const expect = chai.expect;

describe('EmployeeType e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let employeeTypeUpdatePage: EmployeeTypeUpdatePage;
    let employeeTypeComponentsPage: EmployeeTypeComponentsPage;
    let employeeTypeDeleteDialog: EmployeeTypeDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load EmployeeTypes', async () => {
        await navBarPage.goToEntity('employee-type');
        employeeTypeComponentsPage = new EmployeeTypeComponentsPage();
        expect(await employeeTypeComponentsPage.getTitle()).to.eq('cwcrmApp.employeeType.home.title');
    });

    it('should load create EmployeeType page', async () => {
        await employeeTypeComponentsPage.clickOnCreateButton();
        employeeTypeUpdatePage = new EmployeeTypeUpdatePage();
        expect(await employeeTypeUpdatePage.getPageTitle()).to.eq('cwcrmApp.employeeType.home.createOrEditLabel');
        await employeeTypeUpdatePage.cancel();
    });

    it('should create and save EmployeeTypes', async () => {
        const nbButtonsBeforeCreate = await employeeTypeComponentsPage.countDeleteButtons();

        await employeeTypeComponentsPage.clickOnCreateButton();
        await promise.all([employeeTypeUpdatePage.setNameInput('name')]);
        expect(await employeeTypeUpdatePage.getNameInput()).to.eq('name');
        await employeeTypeUpdatePage.save();
        expect(await employeeTypeUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await employeeTypeComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last EmployeeType', async () => {
        const nbButtonsBeforeDelete = await employeeTypeComponentsPage.countDeleteButtons();
        await employeeTypeComponentsPage.clickOnLastDeleteButton();

        employeeTypeDeleteDialog = new EmployeeTypeDeleteDialog();
        expect(await employeeTypeDeleteDialog.getDialogTitle()).to.eq('cwcrmApp.employeeType.delete.question');
        await employeeTypeDeleteDialog.clickOnConfirmButton();

        expect(await employeeTypeComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
