/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { PriorityComponentsPage, PriorityDeleteDialog, PriorityUpdatePage } from './priority.page-object';

const expect = chai.expect;

describe('Priority e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let priorityUpdatePage: PriorityUpdatePage;
    let priorityComponentsPage: PriorityComponentsPage;
    let priorityDeleteDialog: PriorityDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load Priorities', async () => {
        await navBarPage.goToEntity('priority');
        priorityComponentsPage = new PriorityComponentsPage();
        expect(await priorityComponentsPage.getTitle()).to.eq('cwcrmApp.priority.home.title');
    });

    it('should load create Priority page', async () => {
        await priorityComponentsPage.clickOnCreateButton();
        priorityUpdatePage = new PriorityUpdatePage();
        expect(await priorityUpdatePage.getPageTitle()).to.eq('cwcrmApp.priority.home.createOrEditLabel');
        await priorityUpdatePage.cancel();
    });

    it('should create and save Priorities', async () => {
        const nbButtonsBeforeCreate = await priorityComponentsPage.countDeleteButtons();

        await priorityComponentsPage.clickOnCreateButton();
        await promise.all([priorityUpdatePage.setNameInput('name')]);
        expect(await priorityUpdatePage.getNameInput()).to.eq('name');
        await priorityUpdatePage.save();
        expect(await priorityUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await priorityComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last Priority', async () => {
        const nbButtonsBeforeDelete = await priorityComponentsPage.countDeleteButtons();
        await priorityComponentsPage.clickOnLastDeleteButton();

        priorityDeleteDialog = new PriorityDeleteDialog();
        expect(await priorityDeleteDialog.getDialogTitle()).to.eq('cwcrmApp.priority.delete.question');
        await priorityDeleteDialog.clickOnConfirmButton();

        expect(await priorityComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
