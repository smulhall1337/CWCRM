import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IAction } from 'app/shared/model/action.model';
import { ActionService } from './action.service';
import { IUser, UserService } from 'app/core';
import { IParticipant } from 'app/shared/model/participant.model';
import { ParticipantService } from 'app/entities/participant';

@Component({
    selector: 'jhi-action-update',
    templateUrl: './action-update.component.html'
})
export class ActionUpdateComponent implements OnInit {
    action: IAction;
    isSaving: boolean;

    users: IUser[];

    participants: IParticipant[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected actionService: ActionService,
        protected userService: UserService,
        protected participantService: ParticipantService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ action }) => {
            this.action = action;
        });
        this.userService.query().subscribe(
            (res: HttpResponse<IUser[]>) => {
                this.users = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.participantService.query({ filter: 'action-is-null' }).subscribe(
            (res: HttpResponse<IParticipant[]>) => {
                if (!this.action.participantId) {
                    this.participants = res.body;
                } else {
                    this.participantService.find(this.action.participantId).subscribe(
                        (subRes: HttpResponse<IParticipant>) => {
                            this.participants = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.action.id !== undefined) {
            this.subscribeToSaveResponse(this.actionService.update(this.action));
        } else {
            this.subscribeToSaveResponse(this.actionService.create(this.action));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IAction>>) {
        result.subscribe((res: HttpResponse<IAction>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackUserById(index: number, item: IUser) {
        return item.id;
    }

    trackParticipantById(index: number, item: IParticipant) {
        return item.id;
    }
}
