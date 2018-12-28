import { NgModule, CUSTOM_ELEMENTS_SCHEMA, forwardRef } from '@angular/core';

import { CwcrmSharedModule } from 'app/shared';
import { CwcrmAdminModule } from 'app/admin/admin.module';
import { ParticipantViewBaseComponent } from 'app/entities/participant/participant-view-base';
import { TextMaskModule } from 'angular2-text-mask';
import { FormsModule, NG_VALUE_ACCESSOR } from '@angular/forms';
import { CountryService } from 'app/primeng/inputs/autocomplete/service/country.service';
import { AutoCompleteModule } from 'primeng/primeng';
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
import { CwcrmContactHistoryModule } from 'app/entities/contact-history/contact-history.module';
import { ParticipantInfoService } from 'app/shared/util/participant-info.service';

const ENTITY_STATES = [...participantRoute, ...participantPopupRoute];

@NgModule({
    imports: [
        CwcrmContactHistoryModule,
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
        ParticipantDeletePopupComponent
    ],
    entryComponents: [ParticipantComponent, ParticipantUpdateComponent, ParticipantDeleteDialogComponent, ParticipantDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA],
    providers: [
        {
            provide: NG_VALUE_ACCESSOR,
            multi: true,
            useExisting: forwardRef(() => CountryService)
        },
        ParticipantInfoService
    ]
})
export class CwcrmParticipantModule {}
