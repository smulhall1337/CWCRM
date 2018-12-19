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
import { IMCO } from 'app/shared/model/mco.model';
import { MCOService } from 'app/entities/mco';
import { IReferralType } from 'app/shared/model/referral-type.model';
import { ReferralTypeService } from 'app/entities/referral-type';
import { IReferralSource } from 'app/shared/model/referral-source.model';
import { ReferralSourceService } from 'app/entities/referral-source';
import { IUser, UserService } from 'app/core';
import { IAction } from 'app/shared/model/action.model';
import { ActionService } from 'app/entities/action';
import { IContactHistory } from 'app/shared/model/contact-history.model';
import { ContactHistoryService } from 'app/entities/contact-history';

@Component({
    selector: 'jhi-participant-update',
    templateUrl: './participant-update.component.html'
})
export class ParticipantUpdateComponent implements OnInit {
    participant: IParticipant;
    isSaving: boolean;

    contactstatuses: IContactStatus[];

    contactsubstatuses: IContactSubStatus[];

    mcos: IMCO[];

    referraltypes: IReferralType[];

    referralsources: IReferralSource[];

    users: IUser[];

    actions: IAction[];

    contacthistories: IContactHistory[];
    registrationDateDp: any;
    dobDp: any;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected participantService: ParticipantService,
        protected contactStatusService: ContactStatusService,
        protected contactSubStatusService: ContactSubStatusService,
        protected mCOService: MCOService,
        protected referralTypeService: ReferralTypeService,
        protected referralSourceService: ReferralSourceService,
        protected userService: UserService,
        protected actionService: ActionService,
        protected contactHistoryService: ContactHistoryService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ participant }) => {
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
        this.mCOService.query({ filter: 'participant-is-null' }).subscribe(
            (res: HttpResponse<IMCO[]>) => {
                if (!this.participant.mcoId) {
                    this.mcos = res.body;
                } else {
                    this.mCOService.find(this.participant.mcoId).subscribe(
                        (subRes: HttpResponse<IMCO>) => {
                            this.mcos = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.referralTypeService.query({ filter: 'participant-is-null' }).subscribe(
            (res: HttpResponse<IReferralType[]>) => {
                if (!this.participant.referralTypeId) {
                    this.referraltypes = res.body;
                } else {
                    this.referralTypeService.find(this.participant.referralTypeId).subscribe(
                        (subRes: HttpResponse<IReferralType>) => {
                            this.referraltypes = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.referralSourceService.query({ filter: 'participant-is-null' }).subscribe(
            (res: HttpResponse<IReferralSource[]>) => {
                if (!this.participant.referralSourceId) {
                    this.referralsources = res.body;
                } else {
                    this.referralSourceService.find(this.participant.referralSourceId).subscribe(
                        (subRes: HttpResponse<IReferralSource>) => {
                            this.referralsources = [subRes.body].concat(res.body);
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
        this.actionService.query().subscribe(
            (res: HttpResponse<IAction[]>) => {
                this.actions = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.contactHistoryService.query().subscribe(
            (res: HttpResponse<IContactHistory[]>) => {
                this.contacthistories = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
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

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IParticipant>>) {
        result.subscribe((res: HttpResponse<IParticipant>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackContactStatusById(index: number, item: IContactStatus) {
        return item.id;
    }

    trackContactSubStatusById(index: number, item: IContactSubStatus) {
        return item.id;
    }

    trackMCOById(index: number, item: IMCO) {
        return item.id;
    }

    trackReferralTypeById(index: number, item: IReferralType) {
        return item.id;
    }

    trackReferralSourceById(index: number, item: IReferralSource) {
        return item.id;
    }

    trackUserById(index: number, item: IUser) {
        return item.id;
    }

    trackActionById(index: number, item: IAction) {
        return item.id;
    }

    trackContactHistoryById(index: number, item: IContactHistory) {
        return item.id;
    }
}
