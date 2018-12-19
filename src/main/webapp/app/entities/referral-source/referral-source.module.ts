import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CwcrmSharedModule } from 'app/shared';
import {
    ReferralSourceComponent,
    ReferralSourceDetailComponent,
    ReferralSourceUpdateComponent,
    ReferralSourceDeletePopupComponent,
    ReferralSourceDeleteDialogComponent,
    referralSourceRoute,
    referralSourcePopupRoute
} from './';

const ENTITY_STATES = [...referralSourceRoute, ...referralSourcePopupRoute];

@NgModule({
    imports: [CwcrmSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ReferralSourceComponent,
        ReferralSourceDetailComponent,
        ReferralSourceUpdateComponent,
        ReferralSourceDeleteDialogComponent,
        ReferralSourceDeletePopupComponent
    ],
    entryComponents: [
        ReferralSourceComponent,
        ReferralSourceUpdateComponent,
        ReferralSourceDeleteDialogComponent,
        ReferralSourceDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CwcrmReferralSourceModule {}
