import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Participant } from 'app/shared/model/participant.model';
import { ParticipantService } from './participant.service';
import { ParticipantComponent } from './participant.component';
import { ParticipantDetailComponent } from './participant-detail.component';
import { ParticipantUpdateComponent } from './participant-update.component';
import { ParticipantDeletePopupComponent } from './participant-delete-dialog.component';
import { IParticipant } from 'app/shared/model/participant.model';
import { ParticipantViewBaseComponent } from 'app/entities/participant/participant-view-base';

@Injectable({ providedIn: 'root' })
export class ParticipantResolve implements Resolve<IParticipant> {
    constructor(private service: ParticipantService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Participant> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Participant>) => response.ok),
                map((participant: HttpResponse<Participant>) => participant.body)
            );
        }
        return of(new Participant());
    }
}

export const participantRoute: Routes = [
    {
        path: 'participant',
        component: ParticipantComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'cwcrmApp.participant.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'participant/:id/view',
        component: ParticipantDetailComponent,
        resolve: {
            participant: ParticipantResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'cwcrmApp.participant.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'participant/new',
        component: ParticipantUpdateComponent,
        resolve: {
            participant: ParticipantResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'cwcrmApp.participant.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'participant/:id/edit',
        component: ParticipantViewBaseComponent,
        children: [
            {
                path: '',
                redirectTo: 'demographics',
                pathMatch: 'full'
            },
            {
                path: 'demographics',
                component: ParticipantUpdateComponent,
                resolve: {
                    participant: ParticipantResolve
                },
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'cwcrmApp.participant.home.title'
                },
                canActivate: [UserRouteAccessService]
            }
            /*{
                path: 'contact-history',
                component: ContactHistoryComponent,
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'cwcrmApp.contactHistory.home.title'
                },
                canActivate: [UserRouteAccessService],
                children: [
                    {
                        path: ':id/view',
                        component: ContactHistoryDetailComponent,
                        resolve: {
                            contactHistory: ContactHistoryResolve
                        },
                        data: {
                            authorities: ['ROLE_USER'],
                            pageTitle: 'cwcrmApp.contactHistory.home.title'
                        },
                        canActivate: [UserRouteAccessService],
                        outlet: 'view'
                    },
                    {
                        path: 'new',
                        component: ContactHistoryUpdateComponent,
                        resolve: {
                            contactHistory: ContactHistoryResolve
                        },
                        data: {
                            authorities: ['ROLE_USER'],
                            pageTitle: 'cwcrmApp.contactHistory.home.title'
                        },
                        canActivate: [UserRouteAccessService],
                        outlet: 'new-outlet'
                    },
                    {
                        path: ':id/edit',
                        component: ContactHistoryUpdateComponent,
                        resolve: {
                            contactHistory: ContactHistoryResolve
                        },
                        data: {
                            authorities: ['ROLE_USER'],
                            pageTitle: 'cwcrmApp.contactHistory.home.title'
                        },
                        canActivate: [UserRouteAccessService]
                    }
                ]
            }*/
        ],
        resolve: {
            participant: ParticipantResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'cwcrmApp.participant.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const participantPopupRoute: Routes = [
    {
        path: 'participant/:id/delete',
        component: ParticipantDeletePopupComponent,
        resolve: {
            participant: ParticipantResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'cwcrmApp.participant.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
