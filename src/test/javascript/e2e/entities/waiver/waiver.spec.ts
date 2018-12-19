/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { WaiverComponentsPage, WaiverDeleteDialog, WaiverUpdatePage } from './waiver.page-object';

const expect = chai.expect;

describe('Waiver e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let waiverUpdatePage: WaiverUpdatePage;
    let waiverComponentsPage: WaiverComponentsPage;
    let waiverDeleteDialog: WaiverDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load Waivers', async () => {
        await navBarPage.goToEntity('waiver');
        waiverComponentsPage = new WaiverComponentsPage();
        expect(await waiverComponentsPage.getTitle()).to.eq('cwcrmApp.waiver.home.title');
    });

    it('should load create Waiver page', async () => {
        await waiverComponentsPage.clickOnCreateButton();
        waiverUpdatePage = new WaiverUpdatePage();
        expect(await waiverUpdatePage.getPageTitle()).to.eq('cwcrmApp.waiver.home.createOrEditLabel');
        await waiverUpdatePage.cancel();
    });

    it('should create and save Waivers', async () => {
        const nbButtonsBeforeCreate = await waiverComponentsPage.countDeleteButtons();

        await waiverComponentsPage.clickOnCreateButton();
        await promise.all([waiverUpdatePage.setNameInput('name')]);
        expect(await waiverUpdatePage.getNameInput()).to.eq('name');
        await waiverUpdatePage.save();
        expect(await waiverUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await waiverComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last Waiver', async () => {
        const nbButtonsBeforeDelete = await waiverComponentsPage.countDeleteButtons();
        await waiverComponentsPage.clickOnLastDeleteButton();

        waiverDeleteDialog = new WaiverDeleteDialog();
        expect(await waiverDeleteDialog.getDialogTitle()).to.eq('cwcrmApp.waiver.delete.question');
        await waiverDeleteDialog.clickOnConfirmButton();

        expect(await waiverComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
