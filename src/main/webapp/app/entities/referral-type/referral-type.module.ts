import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CwcrmSharedModule } from 'app/shared';
import {
    ReferralTypeComponent,
    ReferralTypeDetailComponent,
    ReferralTypeUpdateComponent,
    ReferralTypeDeletePopupComponent,
    ReferralTypeDeleteDialogComponent,
    referralTypeRoute,
    referralTypePopupRoute
} from './';

const ENTITY_STATES = [...referralTypeRoute, ...referralTypePopupRoute];

@NgModule({
    imports: [CwcrmSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ReferralTypeComponent,
        ReferralTypeDetailComponent,
        ReferralTypeUpdateComponent,
        ReferralTypeDeleteDialogComponent,
        ReferralTypeDeletePopupComponent
    ],
    entryComponents: [
        ReferralTypeComponent,
        ReferralTypeUpdateComponent,
        ReferralTypeDeleteDialogComponent,
        ReferralTypeDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CwcrmReferralTypeModule {}
