import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { JhiAlertService } from 'ng-jhipster';

import { IAction } from 'app/shared/model/action.model';
import { ActionService } from './action.service';
import { ISupportCoordinator } from 'app/shared/model/support-coordinator.model';
import { SupportCoordinatorService } from 'app/entities/support-coordinator';
import { IParticipant } from 'app/shared/model/participant.model';
import { ParticipantService } from 'app/entities/participant';
import { IPriority } from 'app/shared/model/priority.model';
import { PriorityService } from 'app/entities/priority';

@Component({
    selector: 'jhi-action-update',
    templateUrl: './action-update.component.html'
})
export class ActionUpdateComponent implements OnInit {
    action: IAction;
    isSaving: boolean;

    assignedtos: ISupportCoordinator[];

    participants: IParticipant[];

    priorities: IPriority[];
    dueDateDp: any;

    constructor(
        private jhiAlertService: JhiAlertService,
        private actionService: ActionService,
        private supportCoordinatorService: SupportCoordinatorService,
        private participantService: ParticipantService,
        private priorityService: PriorityService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ action }) => {
            this.action = action;
        });
        this.supportCoordinatorService.query({ filter: 'action-is-null' }).subscribe(
            (res: HttpResponse<ISupportCoordinator[]>) => {
                if (!this.action.assignedToId) {
                    this.assignedtos = res.body;
                } else {
                    this.supportCoordinatorService.find(this.action.assignedToId).subscribe(
                        (subRes: HttpResponse<ISupportCoordinator>) => {
                            this.assignedtos = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
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
        this.priorityService.query({ filter: 'action-is-null' }).subscribe(
            (res: HttpResponse<IPriority[]>) => {
                if (!this.action.priorityId) {
                    this.priorities = res.body;
                } else {
                    this.priorityService.find(this.action.priorityId).subscribe(
                        (subRes: HttpResponse<IPriority>) => {
                            this.priorities = [subRes.body].concat(res.body);
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

    private subscribeToSaveResponse(result: Observable<HttpResponse<IAction>>) {
        result.subscribe((res: HttpResponse<IAction>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackSupportCoordinatorById(index: number, item: ISupportCoordinator) {
        return item.id;
    }

    trackParticipantById(index: number, item: IParticipant) {
        return item.id;
    }

    trackPriorityById(index: number, item: IPriority) {
        return item.id;
    }
}
