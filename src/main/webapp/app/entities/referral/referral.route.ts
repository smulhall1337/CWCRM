import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Referral } from 'app/shared/model/referral.model';
import { ReferralService } from './referral.service';
import { ReferralComponent } from './referral.component';
import { ReferralDetailComponent } from './referral-detail.component';
import { ReferralUpdateComponent } from './referral-update.component';
import { ReferralDeletePopupComponent } from './referral-delete-dialog.component';
import { IReferral } from 'app/shared/model/referral.model';

@Injectable({ providedIn: 'root' })
export class ReferralResolve implements Resolve<IReferral> {
    constructor(private service: ReferralService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Referral> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Referral>) => response.ok),
                map((referral: HttpResponse<Referral>) => referral.body)
            );
        }
        return of(new Referral());
    }
}

export const referralRoute: Routes = [
    {
        path: 'referral',
        component: ReferralComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'cwcrmApp.referral.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'referral/:id/view',
        component: ReferralDetailComponent,
        resolve: {
            referral: ReferralResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'cwcrmApp.referral.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'referral/new',
        component: ReferralUpdateComponent,
        resolve: {
            referral: ReferralResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'cwcrmApp.referral.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'referral/:id/edit',
        component: ReferralUpdateComponent,
        resolve: {
            referral: ReferralResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'cwcrmApp.referral.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const referralPopupRoute: Routes = [
    {
        path: 'referral/:id/delete',
        component: ReferralDeletePopupComponent,
        resolve: {
            referral: ReferralResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'cwcrmApp.referral.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
