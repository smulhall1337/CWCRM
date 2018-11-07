import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CwcrmSharedModule } from 'app/shared';
import {
    EnrollmentAgencyComponent,
    EnrollmentAgencyDetailComponent,
    EnrollmentAgencyUpdateComponent,
    EnrollmentAgencyDeletePopupComponent,
    EnrollmentAgencyDeleteDialogComponent,
    enrollmentAgencyRoute,
    enrollmentAgencyPopupRoute
} from './';

const ENTITY_STATES = [...enrollmentAgencyRoute, ...enrollmentAgencyPopupRoute];

@NgModule({
    imports: [CwcrmSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        EnrollmentAgencyComponent,
        EnrollmentAgencyDetailComponent,
        EnrollmentAgencyUpdateComponent,
        EnrollmentAgencyDeleteDialogComponent,
        EnrollmentAgencyDeletePopupComponent
    ],
    entryComponents: [
        EnrollmentAgencyComponent,
        EnrollmentAgencyUpdateComponent,
        EnrollmentAgencyDeleteDialogComponent,
        EnrollmentAgencyDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CwcrmEnrollmentAgencyModule {}
