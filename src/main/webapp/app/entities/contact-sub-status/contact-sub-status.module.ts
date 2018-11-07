import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CwcrmSharedModule } from 'app/shared';
import {
    ContactSubStatusComponent,
    ContactSubStatusDetailComponent,
    ContactSubStatusUpdateComponent,
    ContactSubStatusDeletePopupComponent,
    ContactSubStatusDeleteDialogComponent,
    contactSubStatusRoute,
    contactSubStatusPopupRoute
} from './';

const ENTITY_STATES = [...contactSubStatusRoute, ...contactSubStatusPopupRoute];

@NgModule({
    imports: [CwcrmSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ContactSubStatusComponent,
        ContactSubStatusDetailComponent,
        ContactSubStatusUpdateComponent,
        ContactSubStatusDeleteDialogComponent,
        ContactSubStatusDeletePopupComponent
    ],
    entryComponents: [
        ContactSubStatusComponent,
        ContactSubStatusUpdateComponent,
        ContactSubStatusDeleteDialogComponent,
        ContactSubStatusDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CwcrmContactSubStatusModule {}
