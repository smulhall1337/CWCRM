import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CwcrmSharedModule } from 'app/shared';
import {
    PriorityComponent,
    PriorityDetailComponent,
    PriorityUpdateComponent,
    PriorityDeletePopupComponent,
    PriorityDeleteDialogComponent,
    priorityRoute,
    priorityPopupRoute
} from './';

const ENTITY_STATES = [...priorityRoute, ...priorityPopupRoute];

@NgModule({
    imports: [CwcrmSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        PriorityComponent,
        PriorityDetailComponent,
        PriorityUpdateComponent,
        PriorityDeleteDialogComponent,
        PriorityDeletePopupComponent
    ],
    entryComponents: [PriorityComponent, PriorityUpdateComponent, PriorityDeleteDialogComponent, PriorityDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CwcrmPriorityModule {}
