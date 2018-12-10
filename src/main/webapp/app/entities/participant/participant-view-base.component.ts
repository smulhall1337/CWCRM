import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { JhiAlertService } from 'ng-jhipster';

import { IParticipant } from 'app/shared/model/participant.model';
import { ParticipantService } from './participant.service';
import { IContactStatus } from 'app/shared/model/contact-status.model';
import { ContactStatusService } from 'app/entities/contact-status';
import { IContactSubStatus } from 'app/shared/model/contact-sub-status.model';
import { ContactSubStatusService } from 'app/entities/contact-sub-status';
import { IWaiver } from 'app/shared/model/waiver.model';
import { WaiverService } from 'app/entities/waiver';
import { IUser, UserService } from 'app/core';

@Component({
    selector: 'jhi-participant-view',
    templateUrl: './participant-view-base.html'
})
export class ParticipantViewBaseComponent {}
