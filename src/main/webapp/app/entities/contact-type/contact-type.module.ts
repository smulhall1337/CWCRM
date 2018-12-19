import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CwcrmSharedModule } from 'app/shared';
import {
    ContactTypeComponent,
    ContactTypeDetailComponent,
    ContactTypeUpdateComponent,
    ContactTypeDeletePopupComponent,
    ContactTypeDeleteDialogComponent,
    contactTypeRoute,
    contactTypePopupRoute
} from './';

const ENTITY_STATES = [...contactTypeRoute, ...contactTypePopupRoute];

@NgModule({
    imports: [CwcrmSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ContactTypeComponent,
        ContactTypeDetailComponent,
        ContactTypeUpdateComponent,
        ContactTypeDeleteDialogComponent,
        ContactTypeDeletePopupComponent
    ],
    entryComponents: [ContactTypeComponent, ContactTypeUpdateComponent, ContactTypeDeleteDialogComponent, ContactTypeDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CwcrmContactTypeModule {}
