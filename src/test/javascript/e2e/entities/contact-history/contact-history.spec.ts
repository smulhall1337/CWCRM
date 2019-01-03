/*
/!* tslint:disable no-unused-expression *!/
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { ContactHistoryComponentsPage, ContactHistoryDeleteDialog, ContactHistoryUpdatePage } from './contact-history.page-object';

const expect = chai.expect;

describe('ContactHistory e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let contactHistoryUpdatePage: ContactHistoryUpdatePage;
    let contactHistoryComponentsPage: ContactHistoryComponentsPage;
    let contactHistoryDeleteDialog: ContactHistoryDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load ContactHistories', async () => {
        await navBarPage.goToEntity('contact-history');
        contactHistoryComponentsPage = new ContactHistoryComponentsPage();
        expect(await contactHistoryComponentsPage.getTitle()).to.eq('cwcrmApp.contactHistory.home.title');
    });

    it('should load create ContactHistory page', async () => {
        await contactHistoryComponentsPage.clickOnCreateButton();
        contactHistoryUpdatePage = new ContactHistoryUpdatePage();
        expect(await contactHistoryUpdatePage.getPageTitle()).to.eq('cwcrmApp.contactHistory.home.createOrEditLabel');
        await contactHistoryUpdatePage.cancel();
    });

    it('should create and save ContactHistories', async () => {
        const nbButtonsBeforeCreate = await contactHistoryComponentsPage.countDeleteButtons();

        await contactHistoryComponentsPage.clickOnCreateButton();
        await promise.all([
            contactHistoryUpdatePage.setDateInput('2000-12-31'),
            contactHistoryUpdatePage.setNotesInput('notes'),
            contactHistoryUpdatePage.participantSelectLastOption(),
            contactHistoryUpdatePage.participantSelectLastOption(),
            contactHistoryUpdatePage.userSelectLastOption(),
            contactHistoryUpdatePage.userSelectLastOption(),
            contactHistoryUpdatePage.contactTypeSelectLastOption()
        ]);
        expect(await contactHistoryUpdatePage.getDateInput()).to.eq('2000-12-31');
        expect(await contactHistoryUpdatePage.getNotesInput()).to.eq('notes');
        await contactHistoryUpdatePage.save();
        expect(await contactHistoryUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await contactHistoryComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last ContactHistory', async () => {
        const nbButtonsBeforeDelete = await contactHistoryComponentsPage.countDeleteButtons();
        await contactHistoryComponentsPage.clickOnLastDeleteButton();

        contactHistoryDeleteDialog = new ContactHistoryDeleteDialog();
        expect(await contactHistoryDeleteDialog.getDialogTitle()).to.eq('cwcrmApp.contactHistory.delete.question');
        await contactHistoryDeleteDialog.clickOnConfirmButton();

        expect(await contactHistoryComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
*/
