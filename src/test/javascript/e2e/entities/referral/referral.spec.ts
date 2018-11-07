/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { ReferralComponentsPage, ReferralDeleteDialog, ReferralUpdatePage } from './referral.page-object';

const expect = chai.expect;

describe('Referral e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let referralUpdatePage: ReferralUpdatePage;
    let referralComponentsPage: ReferralComponentsPage;
    let referralDeleteDialog: ReferralDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load Referrals', async () => {
        await navBarPage.goToEntity('referral');
        referralComponentsPage = new ReferralComponentsPage();
        expect(await referralComponentsPage.getTitle()).to.eq('cwcrmApp.referral.home.title');
    });

    it('should load create Referral page', async () => {
        await referralComponentsPage.clickOnCreateButton();
        referralUpdatePage = new ReferralUpdatePage();
        expect(await referralUpdatePage.getPageTitle()).to.eq('cwcrmApp.referral.home.createOrEditLabel');
        await referralUpdatePage.cancel();
    });

    it('should create and save Referrals', async () => {
        const nbButtonsBeforeCreate = await referralComponentsPage.countDeleteButtons();

        await referralComponentsPage.clickOnCreateButton();
        await promise.all([referralUpdatePage.setNameInput('name'), referralUpdatePage.participantSelectLastOption()]);
        expect(await referralUpdatePage.getNameInput()).to.eq('name');
        await referralUpdatePage.save();
        expect(await referralUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await referralComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last Referral', async () => {
        const nbButtonsBeforeDelete = await referralComponentsPage.countDeleteButtons();
        await referralComponentsPage.clickOnLastDeleteButton();

        referralDeleteDialog = new ReferralDeleteDialog();
        expect(await referralDeleteDialog.getDialogTitle()).to.eq('cwcrmApp.referral.delete.question');
        await referralDeleteDialog.clickOnConfirmButton();

        expect(await referralComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
