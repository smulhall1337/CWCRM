/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { EmployeeSubTypeComponentsPage, EmployeeSubTypeDeleteDialog, EmployeeSubTypeUpdatePage } from './employee-sub-type.page-object';

const expect = chai.expect;

describe('EmployeeSubType e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let employeeSubTypeUpdatePage: EmployeeSubTypeUpdatePage;
    let employeeSubTypeComponentsPage: EmployeeSubTypeComponentsPage;
    let employeeSubTypeDeleteDialog: EmployeeSubTypeDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load EmployeeSubTypes', async () => {
        await navBarPage.goToEntity('employee-sub-type');
        employeeSubTypeComponentsPage = new EmployeeSubTypeComponentsPage();
        expect(await employeeSubTypeComponentsPage.getTitle()).to.eq('cwcrmApp.employeeSubType.home.title');
    });

    it('should load create EmployeeSubType page', async () => {
        await employeeSubTypeComponentsPage.clickOnCreateButton();
        employeeSubTypeUpdatePage = new EmployeeSubTypeUpdatePage();
        expect(await employeeSubTypeUpdatePage.getPageTitle()).to.eq('cwcrmApp.employeeSubType.home.createOrEditLabel');
        await employeeSubTypeUpdatePage.cancel();
    });

    it('should create and save EmployeeSubTypes', async () => {
        const nbButtonsBeforeCreate = await employeeSubTypeComponentsPage.countDeleteButtons();

        await employeeSubTypeComponentsPage.clickOnCreateButton();
        await promise.all([employeeSubTypeUpdatePage.setNameInput('name')]);
        expect(await employeeSubTypeUpdatePage.getNameInput()).to.eq('name');
        await employeeSubTypeUpdatePage.save();
        expect(await employeeSubTypeUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await employeeSubTypeComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last EmployeeSubType', async () => {
        const nbButtonsBeforeDelete = await employeeSubTypeComponentsPage.countDeleteButtons();
        await employeeSubTypeComponentsPage.clickOnLastDeleteButton();

        employeeSubTypeDeleteDialog = new EmployeeSubTypeDeleteDialog();
        expect(await employeeSubTypeDeleteDialog.getDialogTitle()).to.eq('cwcrmApp.employeeSubType.delete.question');
        await employeeSubTypeDeleteDialog.clickOnConfirmButton();

        expect(await employeeSubTypeComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
