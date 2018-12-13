import { Component, OnInit } from '@angular/core';
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
    selector: 'jhi-participant-update',
    templateUrl: './participant-update.component.html'
})
export class ParticipantUpdateComponent implements OnInit {
    participant: IParticipant;
    isSaving: boolean;

    contactstatuses: IContactStatus[];

    contactsubstatuses: IContactSubStatus[];

    waivers: IWaiver[];

    users: IUser[];
    registrationDateDp: any;

    constructor(
        private jhiAlertService: JhiAlertService,
        private participantService: ParticipantService,
        private contactStatusService: ContactStatusService,
        private contactSubStatusService: ContactSubStatusService,
        private waiverService: WaiverService,
        private userService: UserService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.parent.data.subscribe(({ participant }) => {
            this.participant = participant;
        });
        this.contactStatusService.query({ filter: 'participant-is-null' }).subscribe(
            (res: HttpResponse<IContactStatus[]>) => {
                if (!this.participant.contactStatusId) {
                    this.contactstatuses = res.body;
                } else {
                    this.contactStatusService.find(this.participant.contactStatusId).subscribe(
                        (subRes: HttpResponse<IContactStatus>) => {
                            this.contactstatuses = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.contactSubStatusService.query({ filter: 'participant-is-null' }).subscribe(
            (res: HttpResponse<IContactSubStatus[]>) => {
                if (!this.participant.contactSubStatusId) {
                    this.contactsubstatuses = res.body;
                } else {
                    this.contactSubStatusService.find(this.participant.contactSubStatusId).subscribe(
                        (subRes: HttpResponse<IContactSubStatus>) => {
                            this.contactsubstatuses = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.waiverService.query({ filter: 'participant-is-null' }).subscribe(
            (res: HttpResponse<IWaiver[]>) => {
                if (!this.participant.waiverId) {
                    this.waivers = res.body;
                } else {
                    this.waiverService.find(this.participant.waiverId).subscribe(
                        (subRes: HttpResponse<IWaiver>) => {
                            this.waivers = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.userService.query().subscribe(
            (res: HttpResponse<IUser[]>) => {
                this.users = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        console.log(this.participant.id);
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.participant.id !== undefined) {
            this.subscribeToSaveResponse(this.participantService.update(this.participant));
        } else {
            this.subscribeToSaveResponse(this.participantService.create(this.participant));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IParticipant>>) {
        result.subscribe((res: HttpResponse<IParticipant>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        //this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackContactStatusById(index: number, item: IContactStatus) {
        return item.id;
    }

    trackContactSubStatusById(index: number, item: IContactSubStatus) {
        return item.id;
    }

    trackWaiverById(index: number, item: IWaiver) {
        return item.id;
    }

    trackUserById(index: number, item: IUser) {
        return item.id;
    }
}
