import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IReferral } from 'app/shared/model/referral.model';
import { ReferralService } from './referral.service';
import { IParticipant } from 'app/shared/model/participant.model';
import { ParticipantService } from 'app/entities/participant';

@Component({
    selector: 'jhi-referral-update',
    templateUrl: './referral-update.component.html'
})
export class ReferralUpdateComponent implements OnInit {
    referral: IReferral;
    isSaving: boolean;

    participants: IParticipant[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private referralService: ReferralService,
        private participantService: ParticipantService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ referral }) => {
            this.referral = referral;
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
        if (this.referral.id !== undefined) {
            this.subscribeToSaveResponse(this.referralService.update(this.referral));
        } else {
            this.subscribeToSaveResponse(this.referralService.create(this.referral));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IReferral>>) {
        result.subscribe((res: HttpResponse<IReferral>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
