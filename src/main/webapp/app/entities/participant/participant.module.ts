import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { MatTabsModule } from '@angular/material/tabs';
import { I18nCountrySelectModule } from 'ngx-i18n-country-select';
import { TextMaskModule } from 'angular2-text-mask';
import { FormsModule } from '@angular/forms';

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
    // contactHistoryRoute,
    ContactHistoryUpdateComponent
    // contactHistoryPopupRoute
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
import { ParticipantViewBaseComponent } from 'app/entities/participant/participant-view-base.component';
// import { CwcrmContactHistoryModule } from 'app/entities/contact-history/contact-history.module';

const ENTITY_STATES = [...participantRoute, ...participantPopupRoute];

@NgModule({
    imports: [
        CwcrmSharedModule,
        CwcrmAdminModule,
        RouterModule.forChild(ENTITY_STATES),
        MatTabsModule,
        I18nCountrySelectModule.forRoot(),
        FormsModule,
        TextMaskModule
        // CwcrmContactHistoryModule
    ],

    declarations: [
        ParticipantComponent,
        ParticipantDetailComponent,
        ParticipantUpdateComponent,
        ParticipantDeleteDialogComponent,
        ParticipantDeletePopupComponent,
        ParticipantViewBaseComponent,
        ContactHistoryComponent,
        ContactHistoryDetailComponent,
        ContactHistoryDeleteDialogComponent,
        ContactHistoryDeletePopupComponent,
        ContactHistoryUpdateComponent
    ],
    entryComponents: [ParticipantComponent, ParticipantUpdateComponent, ParticipantDeleteDialogComponent, ParticipantDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CwcrmParticipantModule {}
