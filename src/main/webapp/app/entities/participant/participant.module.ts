import { NgModule, CUSTOM_ELEMENTS_SCHEMA, forwardRef } from '@angular/core';

import { CwcrmSharedModule } from 'app/shared';
import { CwcrmAdminModule } from 'app/admin/admin.module';
import { ParticipantViewBaseComponent } from 'app/entities/participant/participant-view-base';
import { TextMaskModule } from 'angular2-text-mask';
import { FormsModule, NG_VALUE_ACCESSOR } from '@angular/forms';
import { CountryService } from 'app/primeng/inputs/autocomplete/service/country.service';
import { AutoCompleteModule } from 'primeng/primeng';
import {
    ContactHistoryComponent,
    ContactHistoryDeleteDialogComponent,
    ContactHistoryDeletePopupComponent,
    ContactHistoryDetailComponent,
    ContactHistoryUpdateComponent
} from 'app/entities/contact-history';
import {
    ParticipantComponent,
    ParticipantDetailComponent,
    ParticipantUpdateComponent,
    ParticipantDeletePopupComponent,
    ParticipantDeleteDialogComponent,
    participantRoute,
    participantPopupRoute
} from './';
import { RouterModule } from '@angular/router';

const ENTITY_STATES = [...participantRoute, ...participantPopupRoute];

@NgModule({
    imports: [
        CwcrmSharedModule,
        CwcrmAdminModule,
        RouterModule.forChild(ENTITY_STATES),
        FormsModule,
        TextMaskModule,
        AutoCompleteModule,
        FormsModule
    ],
    declarations: [
        ParticipantComponent,
        ParticipantDetailComponent,
        ParticipantUpdateComponent,
        ParticipantDeleteDialogComponent,
        ParticipantViewBaseComponent,
        ContactHistoryComponent,
        ContactHistoryDetailComponent,
        ContactHistoryUpdateComponent,
        ContactHistoryDeleteDialogComponent,
        ContactHistoryDeletePopupComponent,
        ParticipantDeletePopupComponent
    ],
    entryComponents: [ParticipantComponent, ParticipantUpdateComponent, ParticipantDeleteDialogComponent, ParticipantDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA],
    providers: [
        {
            provide: NG_VALUE_ACCESSOR,
            multi: true,
            useExisting: forwardRef(() => CountryService)
        }
    ]
})
export class CwcrmParticipantModule {}
