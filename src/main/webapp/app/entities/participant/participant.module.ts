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
import {
    ContactHistoryComponent,
    ContactHistoryDetailComponent,
    ContactHistoryDeleteDialogComponent,
    ContactHistoryDeletePopupComponent,
    contactHistoryRoute,
    contactHistoryPopupRoute
} from 'app/entities/contact-history';
import {
    participantNotesPopupRoute,
    participantNotesRoute,
    ParticipantNotesComponent,
    ParticipantNotesDeleteDialogComponent,
    ParticipantNotesDeletePopupComponent,
    ParticipantNotesDetailComponent,
    ParticipantNotesResolve,
    ParticipantNotesService,
    ParticipantNotesUpdateComponent
} from 'app/entities/participant-notes';

const ENTITY_STATES = [...participantRoute, ...participantPopupRoute];

@NgModule({
    imports: [CwcrmSharedModule, CwcrmAdminModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ParticipantComponent,
        ParticipantDetailComponent,
        ParticipantUpdateComponent,
        ParticipantDeleteDialogComponent,
        ParticipantDeletePopupComponent
    ],
    entryComponents: [ParticipantComponent, ParticipantUpdateComponent, ParticipantDeleteDialogComponent, ParticipantDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CwcrmParticipantModule {}
