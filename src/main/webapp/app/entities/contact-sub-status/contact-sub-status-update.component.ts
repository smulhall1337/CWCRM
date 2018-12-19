import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IContactSubStatus } from 'app/shared/model/contact-sub-status.model';
import { ContactSubStatusService } from './contact-sub-status.service';

@Component({
    selector: 'jhi-contact-sub-status-update',
    templateUrl: './contact-sub-status-update.component.html'
})
export class ContactSubStatusUpdateComponent implements OnInit {
    contactSubStatus: IContactSubStatus;
    isSaving: boolean;

    constructor(protected contactSubStatusService: ContactSubStatusService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ contactSubStatus }) => {
            this.contactSubStatus = contactSubStatus;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.contactSubStatus.id !== undefined) {
            this.subscribeToSaveResponse(this.contactSubStatusService.update(this.contactSubStatus));
        } else {
            this.subscribeToSaveResponse(this.contactSubStatusService.create(this.contactSubStatus));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IContactSubStatus>>) {
        result.subscribe((res: HttpResponse<IContactSubStatus>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
