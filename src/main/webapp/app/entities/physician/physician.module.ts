import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CwcrmSharedModule } from 'app/shared';
import {
    PhysicianComponent,
    PhysicianDetailComponent,
    PhysicianUpdateComponent,
    PhysicianDeletePopupComponent,
    PhysicianDeleteDialogComponent,
    physicianRoute,
    physicianPopupRoute
} from './';

const ENTITY_STATES = [...physicianRoute, ...physicianPopupRoute];

@NgModule({
    imports: [CwcrmSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        PhysicianComponent,
        PhysicianDetailComponent,
        PhysicianUpdateComponent,
        PhysicianDeleteDialogComponent,
        PhysicianDeletePopupComponent
    ],
    entryComponents: [PhysicianComponent, PhysicianUpdateComponent, PhysicianDeleteDialogComponent, PhysicianDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CwcrmPhysicianModule {}
