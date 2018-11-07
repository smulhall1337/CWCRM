import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IMCO } from 'app/shared/model/mco.model';
import { MCOService } from './mco.service';

@Component({
    selector: 'jhi-mco-update',
    templateUrl: './mco-update.component.html'
})
export class MCOUpdateComponent implements OnInit {
    mCO: IMCO;
    isSaving: boolean;

    constructor(private mCOService: MCOService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ mCO }) => {
            this.mCO = mCO;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.mCO.id !== undefined) {
            this.subscribeToSaveResponse(this.mCOService.update(this.mCO));
        } else {
            this.subscribeToSaveResponse(this.mCOService.create(this.mCO));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IMCO>>) {
        result.subscribe((res: HttpResponse<IMCO>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
