import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CwcrmSharedModule } from 'app/shared';
import {
    ReferralComponent,
    ReferralDetailComponent,
    ReferralUpdateComponent,
    ReferralDeletePopupComponent,
    ReferralDeleteDialogComponent,
    referralRoute,
    referralPopupRoute
} from './';

const ENTITY_STATES = [...referralRoute, ...referralPopupRoute];

@NgModule({
    imports: [CwcrmSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ReferralComponent,
        ReferralDetailComponent,
        ReferralUpdateComponent,
        ReferralDeleteDialogComponent,
        ReferralDeletePopupComponent
    ],
    entryComponents: [ReferralComponent, ReferralUpdateComponent, ReferralDeleteDialogComponent, ReferralDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CwcrmReferralModule {}
