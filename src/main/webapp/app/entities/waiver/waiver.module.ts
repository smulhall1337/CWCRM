import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CwcrmSharedModule } from 'app/shared';
import {
    WaiverComponent,
    WaiverDetailComponent,
    WaiverUpdateComponent,
    WaiverDeletePopupComponent,
    WaiverDeleteDialogComponent,
    waiverRoute,
    waiverPopupRoute
} from './';

const ENTITY_STATES = [...waiverRoute, ...waiverPopupRoute];

@NgModule({
    imports: [CwcrmSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [WaiverComponent, WaiverDetailComponent, WaiverUpdateComponent, WaiverDeleteDialogComponent, WaiverDeletePopupComponent],
    entryComponents: [WaiverComponent, WaiverUpdateComponent, WaiverDeleteDialogComponent, WaiverDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CwcrmWaiverModule {}
