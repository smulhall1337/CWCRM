import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { CwcrmParticipantModule } from './participant/participant.module';
import { CwcrmReferralModule } from './referral/referral.module';
import { CwcrmActionModule } from './action/action.module';
import { CwcrmSupportCoordinatorModule } from './support-coordinator/support-coordinator.module';
import { CwcrmWaiverModule } from './waiver/waiver.module';
import { CwcrmDepartmentModule } from './department/department.module';
import { CwcrmEmployeeTypeModule } from './employee-type/employee-type.module';
import { CwcrmEmployeeSubTypeModule } from './employee-sub-type/employee-sub-type.module';
import { CwcrmContactStatusModule } from './contact-status/contact-status.module';
import { CwcrmContactSubStatusModule } from './contact-sub-status/contact-sub-status.module';
import { CwcrmPriorityModule } from './priority/priority.module';
import { CwcrmMCOModule } from './mco/mco.module';
import { CwcrmPhysicianModule } from './physician/physician.module';
import { CwcrmEnrollmentAgencyModule } from './enrollment-agency/enrollment-agency.module';
// import { CwcrmContactHistoryModule } from './contact-history/contact-history.module';
import { CwcrmParticipantNotesModule } from './participant-notes/participant-notes.module';
import { CwcrmContactTypeModule } from './contact-type/contact-type.module';
import { CwcrmExtendedUserModule } from './extended-user/extended-user.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        CwcrmParticipantModule,
        CwcrmReferralModule,
        CwcrmActionModule,
        CwcrmSupportCoordinatorModule,
        CwcrmWaiverModule,
        CwcrmDepartmentModule,
        CwcrmEmployeeTypeModule,
        CwcrmEmployeeSubTypeModule,
        CwcrmContactStatusModule,
        CwcrmContactSubStatusModule,
        CwcrmPriorityModule,
        CwcrmMCOModule,
        CwcrmPhysicianModule,
        CwcrmEnrollmentAgencyModule,
        // CwcrmContactHistoryModule,
        CwcrmParticipantNotesModule,
        CwcrmContactTypeModule,
        CwcrmExtendedUserModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    exports: [
        // CwcrmContactHistoryModule
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CwcrmEntityModule {}
