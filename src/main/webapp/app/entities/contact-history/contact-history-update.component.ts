import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { IContactHistory } from 'app/shared/model/contact-history.model';
import { ContactHistoryService } from './contact-history.service';
import { IContactType } from 'app/shared/model/contact-type.model';
import { ContactTypeService } from 'app/entities/contact-type';
import { IParticipant } from 'app/shared/model/participant.model';
import { ParticipantService } from 'app/entities/participant';
import { IUser, UserService } from 'app/core';

@Component({
    selector: 'jhi-contact-history-update',
    templateUrl: './contact-history-update.component.html'
})
export class ContactHistoryUpdateComponent implements OnInit {
    contactHistory: IContactHistory;
    isSaving: boolean;

    contacttypes: IContactType[];

    participants: IParticipant[];

    users: IUser[];
    dateDp: any;

    constructor(
        private dataUtils: JhiDataUtils,
        private jhiAlertService: JhiAlertService,
        private contactHistoryService: ContactHistoryService,
        private contactTypeService: ContactTypeService,
        private participantService: ParticipantService,
        private userService: UserService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ contactHistory }) => {
            this.contactHistory = contactHistory;
        });
        this.contactTypeService.query({ filter: 'contacthistory-is-null' }).subscribe(
            (res: HttpResponse<IContactType[]>) => {
                if (!this.contactHistory.contactTypeId) {
                    this.contacttypes = res.body;
                } else {
                    this.contactTypeService.find(this.contactHistory.contactTypeId).subscribe(
                        (subRes: HttpResponse<IContactType>) => {
                            this.contacttypes = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.participantService.query().subscribe(
            (res: HttpResponse<IParticipant[]>) => {
                this.participants = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.userService.query().subscribe(
            (res: HttpResponse<IUser[]>) => {
                this.users = res.body;
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
        if (this.contactHistory.id !== undefined) {
            this.subscribeToSaveResponse(this.contactHistoryService.update(this.contactHistory));
        } else {
            this.subscribeToSaveResponse(this.contactHistoryService.create(this.contactHistory));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IContactHistory>>) {
        result.subscribe((res: HttpResponse<IContactHistory>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackContactTypeById(index: number, item: IContactType) {
        return item.id;
    }

    trackParticipantById(index: number, item: IParticipant) {
        return item.id;
    }

    trackUserById(index: number, item: IUser) {
        return item.id;
    }
}
