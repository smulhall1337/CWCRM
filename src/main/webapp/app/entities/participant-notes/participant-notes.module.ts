import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CwcrmSharedModule } from 'app/shared';
import { CwcrmAdminModule } from 'app/admin/admin.module';
import {
    ParticipantNotesComponent,
    ParticipantNotesDetailComponent,
    ParticipantNotesUpdateComponent,
    ParticipantNotesDeletePopupComponent,
    ParticipantNotesDeleteDialogComponent,
    participantNotesRoute,
    participantNotesPopupRoute
} from './';

const ENTITY_STATES = [...participantNotesRoute, ...participantNotesPopupRoute];

@NgModule({
    imports: [CwcrmSharedModule, CwcrmAdminModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ParticipantNotesComponent,
        ParticipantNotesDetailComponent,
        ParticipantNotesUpdateComponent,
        ParticipantNotesDeleteDialogComponent,
        ParticipantNotesDeletePopupComponent
    ],
    entryComponents: [
        ParticipantNotesComponent,
        ParticipantNotesUpdateComponent,
        ParticipantNotesDeleteDialogComponent,
        ParticipantNotesDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CwcrmParticipantNotesModule {}
