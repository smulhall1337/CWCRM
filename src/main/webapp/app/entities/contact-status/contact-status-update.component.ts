import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IContactStatus } from 'app/shared/model/contact-status.model';
import { ContactStatusService } from './contact-status.service';
import { IParticipant } from 'app/shared/model/participant.model';
import { ParticipantService } from 'app/entities/participant';

@Component({
    selector: 'jhi-contact-status-update',
    templateUrl: './contact-status-update.component.html'
})
export class ContactStatusUpdateComponent implements OnInit {
    contactStatus: IContactStatus;
    isSaving: boolean;

    participants: IParticipant[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private contactStatusService: ContactStatusService,
        private participantService: ParticipantService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ contactStatus }) => {
            this.contactStatus = contactStatus;
        });
        this.participantService.query().subscribe(
            (res: HttpResponse<IParticipant[]>) => {
                this.participants = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.contactStatus.id !== undefined) {
            this.subscribeToSaveResponse(this.contactStatusService.update(this.contactStatus));
        } else {
            this.subscribeToSaveResponse(this.contactStatusService.create(this.contactStatus));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IContactStatus>>) {
        result.subscribe((res: HttpResponse<IContactStatus>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackParticipantById(index: number, item: IParticipant) {
        return item.id;
    }
}
