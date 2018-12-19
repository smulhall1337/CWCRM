import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CwcrmSharedModule } from 'app/shared';
import {
    SupportCoordinatorComponent,
    SupportCoordinatorDetailComponent,
    SupportCoordinatorUpdateComponent,
    SupportCoordinatorDeletePopupComponent,
    SupportCoordinatorDeleteDialogComponent,
    supportCoordinatorRoute,
    supportCoordinatorPopupRoute
} from './';

const ENTITY_STATES = [...supportCoordinatorRoute, ...supportCoordinatorPopupRoute];

@NgModule({
    imports: [CwcrmSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        SupportCoordinatorComponent,
        SupportCoordinatorDetailComponent,
        SupportCoordinatorUpdateComponent,
        SupportCoordinatorDeleteDialogComponent,
        SupportCoordinatorDeletePopupComponent
    ],
    entryComponents: [
        SupportCoordinatorComponent,
        SupportCoordinatorUpdateComponent,
        SupportCoordinatorDeleteDialogComponent,
        SupportCoordinatorDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CwcrmSupportCoordinatorModule {}
