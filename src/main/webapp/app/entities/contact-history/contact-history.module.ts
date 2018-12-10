/*
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CwcrmSharedModule } from 'app/shared';
import { CwcrmAdminModule } from 'app/admin/admin.module';
import {
    ContactHistoryComponent,
    ContactHistoryDetailComponent,
    ContactHistoryUpdateComponent,
    ContactHistoryDeletePopupComponent,
    ContactHistoryDeleteDialogComponent,
    contactHistoryRoute,
    contactHistoryPopupRoute
} from './';
import {participantPopupRoute, participantRoute} from "app/entities/participant";

// const ENTITY_STATES = [...contactHistoryRoute, ...contactHistoryPopupRoute];

const ENTITY_STATES = [...participantRoute, ...participantPopupRoute];

@NgModule({
    imports: [CwcrmSharedModule, CwcrmAdminModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        /!*ContactHistoryComponent,
        ContactHistoryDetailComponent,
        ContactHistoryUpdateComponent,
        ContactHistoryDeleteDialogComponent,
        ContactHistoryDeletePopupComponent*!/
        // CwcrmContactHistoryModule
    ],
    entryComponents: [
        ContactHistoryComponent,
        ContactHistoryUpdateComponent,
        ContactHistoryDeleteDialogComponent,
        ContactHistoryDeletePopupComponent
    ],
    exports: [
        ContactHistoryComponent,
        ContactHistoryDetailComponent,
        ContactHistoryUpdateComponent,
        ContactHistoryDeleteDialogComponent,
        ContactHistoryDeletePopupComponent
    ],


    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CwcrmContactHistoryModule {}
*/
