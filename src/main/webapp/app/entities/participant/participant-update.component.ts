import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { IParticipant } from 'app/shared/model/participant.model';
import { ParticipantService } from './participant.service';
import { IContactStatus } from 'app/shared/model/contact-status.model';
import { ContactStatusService } from 'app/entities/contact-status';
import { IContactSubStatus } from 'app/shared/model/contact-sub-status.model';
import { ContactSubStatusService } from 'app/entities/contact-sub-status';
import { IWaiver } from 'app/shared/model/waiver.model';
import { WaiverService } from 'app/entities/waiver';
import { IMCO } from 'app/shared/model/mco.model';
import { MCOService } from 'app/entities/mco';
import { ISupportCoordinator } from 'app/shared/model/support-coordinator.model';
import { SupportCoordinatorService } from 'app/entities/support-coordinator';
import { IPhysician } from 'app/shared/model/physician.model';
import { PhysicianService } from 'app/entities/physician';

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

    mcos: IMCO[];

    supportcoordinators: ISupportCoordinator[];

    primaryphysicians: IPhysician[];
    registrationDateDp: any;
    created: string;
    updated: string;

    constructor(
        private dataUtils: JhiDataUtils,
        private jhiAlertService: JhiAlertService,
        private participantService: ParticipantService,
        private contactStatusService: ContactStatusService,
        private contactSubStatusService: ContactSubStatusService,
        private waiverService: WaiverService,
        private mCOService: MCOService,
        private supportCoordinatorService: SupportCoordinatorService,
        private physicianService: PhysicianService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ participant }) => {
            this.participant = participant;
            this.created = this.participant.created != null ? this.participant.created.format(DATE_TIME_FORMAT) : null;
            this.updated = this.participant.updated != null ? this.participant.updated.format(DATE_TIME_FORMAT) : null;
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
        this.supportCoordinatorService.query({ filter: 'participant-is-null' }).subscribe(
            (res: HttpResponse<ISupportCoordinator[]>) => {
                if (!this.participant.supportCoordinatorId) {
                    this.supportcoordinators = res.body;
                } else {
                    this.supportCoordinatorService.find(this.participant.supportCoordinatorId).subscribe(
                        (subRes: HttpResponse<ISupportCoordinator>) => {
                            this.supportcoordinators = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.physicianService.query({ filter: 'participant-is-null' }).subscribe(
            (res: HttpResponse<IPhysician[]>) => {
                if (!this.participant.primaryPhysicianId) {
                    this.primaryphysicians = res.body;
                } else {
                    this.physicianService.find(this.participant.primaryPhysicianId).subscribe(
                        (subRes: HttpResponse<IPhysician>) => {
                            this.primaryphysicians = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    setFileData(event, entity, field, isImage) {
        this.dataUtils.setFileData(event, entity, field, isImage);
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.participant.created = this.created != null ? moment(this.created, DATE_TIME_FORMAT) : null;
        this.participant.updated = this.updated != null ? moment(this.updated, DATE_TIME_FORMAT) : null;
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
        this.previousState();
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

    trackMCOById(index: number, item: IMCO) {
        return item.id;
    }

    trackSupportCoordinatorById(index: number, item: ISupportCoordinator) {
        return item.id;
    }

    trackPhysicianById(index: number, item: IPhysician) {
        return item.id;
    }
}
