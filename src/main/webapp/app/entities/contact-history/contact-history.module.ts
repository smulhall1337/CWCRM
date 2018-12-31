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
import { MatAutocompleteModule } from '@angular/material';

const ENTITY_STATES = [...contactHistoryRoute, ...contactHistoryPopupRoute];

@NgModule({
    imports: [CwcrmSharedModule, CwcrmAdminModule, RouterModule.forChild(ENTITY_STATES), MatAutocompleteModule],
    declarations: [
        ContactHistoryComponent,
        ContactHistoryDetailComponent,
        ContactHistoryUpdateComponent,
        ContactHistoryDeleteDialogComponent,
        ContactHistoryDeletePopupComponent
    ],
    entryComponents: [
        ContactHistoryComponent,
        ContactHistoryUpdateComponent,
        ContactHistoryDeleteDialogComponent,
        ContactHistoryDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CwcrmContactHistoryModule {}
