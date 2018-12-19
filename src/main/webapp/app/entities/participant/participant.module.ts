import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CwcrmSharedModule } from 'app/shared';
import { CwcrmAdminModule } from 'app/admin/admin.module';
import {
    ParticipantComponent,
    ParticipantDetailComponent,
    ParticipantUpdateComponent,
    ParticipantDeletePopupComponent,
    ParticipantDeleteDialogComponent,
    participantRoute,
    participantPopupRoute
} from './';
import { ParticipantViewBaseComponent } from 'app/entities/participant/participant-view-base';
import { TextMaskModule } from 'angular2-text-mask';
import { FormsModule } from '@angular/forms';

const ENTITY_STATES = [...participantRoute, ...participantPopupRoute];

@NgModule({
    imports: [CwcrmSharedModule, CwcrmAdminModule, RouterModule.forChild(ENTITY_STATES), FormsModule, TextMaskModule],
    declarations: [
        ParticipantComponent,
        ParticipantDetailComponent,
        ParticipantUpdateComponent,
        ParticipantDeleteDialogComponent,
        ParticipantViewBaseComponent,
        ParticipantDeletePopupComponent
    ],
    entryComponents: [ParticipantComponent, ParticipantUpdateComponent, ParticipantDeleteDialogComponent, ParticipantDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CwcrmParticipantModule {}
