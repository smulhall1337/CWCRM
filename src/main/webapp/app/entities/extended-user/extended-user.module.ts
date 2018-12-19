import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CwcrmSharedModule } from 'app/shared';
import { CwcrmAdminModule } from 'app/admin/admin.module';
import {
    ExtendedUserComponent,
    ExtendedUserDetailComponent,
    ExtendedUserUpdateComponent,
    ExtendedUserDeletePopupComponent,
    ExtendedUserDeleteDialogComponent,
    extendedUserRoute,
    extendedUserPopupRoute
} from './';

const ENTITY_STATES = [...extendedUserRoute, ...extendedUserPopupRoute];

@NgModule({
    imports: [CwcrmSharedModule, CwcrmAdminModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ExtendedUserComponent,
        ExtendedUserDetailComponent,
        ExtendedUserUpdateComponent,
        ExtendedUserDeleteDialogComponent,
        ExtendedUserDeletePopupComponent
    ],
    entryComponents: [
        ExtendedUserComponent,
        ExtendedUserUpdateComponent,
        ExtendedUserDeleteDialogComponent,
        ExtendedUserDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CwcrmExtendedUserModule {}
