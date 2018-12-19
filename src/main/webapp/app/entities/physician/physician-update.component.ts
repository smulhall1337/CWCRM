import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiDataUtils } from 'ng-jhipster';

import { IPhysician } from 'app/shared/model/physician.model';
import { PhysicianService } from './physician.service';

@Component({
    selector: 'jhi-physician-update',
    templateUrl: './physician-update.component.html'
})
export class PhysicianUpdateComponent implements OnInit {
    physician: IPhysician;
    isSaving: boolean;

    constructor(private dataUtils: JhiDataUtils, private physicianService: PhysicianService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ physician }) => {
            this.physician = physician;
        });
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
        if (this.physician.id !== undefined) {
            this.subscribeToSaveResponse(this.physicianService.update(this.physician));
        } else {
            this.subscribeToSaveResponse(this.physicianService.create(this.physician));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IPhysician>>) {
        result.subscribe((res: HttpResponse<IPhysician>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
