/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { ContactTypeComponentsPage, ContactTypeDeleteDialog, ContactTypeUpdatePage } from './contact-type.page-object';

const expect = chai.expect;

describe('ContactType e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let contactTypeUpdatePage: ContactTypeUpdatePage;
    let contactTypeComponentsPage: ContactTypeComponentsPage;
    let contactTypeDeleteDialog: ContactTypeDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load ContactTypes', async () => {
        await navBarPage.goToEntity('contact-type');
        contactTypeComponentsPage = new ContactTypeComponentsPage();
        expect(await contactTypeComponentsPage.getTitle()).to.eq('cwcrmApp.contactType.home.title');
    });

    it('should load create ContactType page', async () => {
        await contactTypeComponentsPage.clickOnCreateButton();
        contactTypeUpdatePage = new ContactTypeUpdatePage();
        expect(await contactTypeUpdatePage.getPageTitle()).to.eq('cwcrmApp.contactType.home.createOrEditLabel');
        await contactTypeUpdatePage.cancel();
    });

    it('should create and save ContactTypes', async () => {
        const nbButtonsBeforeCreate = await contactTypeComponentsPage.countDeleteButtons();

        await contactTypeComponentsPage.clickOnCreateButton();
        await promise.all([contactTypeUpdatePage.setNameInput('name')]);
        expect(await contactTypeUpdatePage.getNameInput()).to.eq('name');
        await contactTypeUpdatePage.save();
        expect(await contactTypeUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await contactTypeComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last ContactType', async () => {
        const nbButtonsBeforeDelete = await contactTypeComponentsPage.countDeleteButtons();
        await contactTypeComponentsPage.clickOnLastDeleteButton();

        contactTypeDeleteDialog = new ContactTypeDeleteDialog();
        expect(await contactTypeDeleteDialog.getDialogTitle()).to.eq('cwcrmApp.contactType.delete.question');
        await contactTypeDeleteDialog.clickOnConfirmButton();

        expect(await contactTypeComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
