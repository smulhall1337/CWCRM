import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IEnrollmentAgency } from 'app/shared/model/enrollment-agency.model';
import { Principal } from 'app/core';
import { EnrollmentAgencyService } from './enrollment-agency.service';

@Component({
    selector: 'jhi-enrollment-agency',
    templateUrl: './enrollment-agency.component.html'
})
export class EnrollmentAgencyComponent implements OnInit, OnDestroy {
    enrollmentAgencies: IEnrollmentAgency[];
    currentAccount: any;
    eventSubscriber: Subscription;
    currentSearch: string;

    constructor(
        private enrollmentAgencyService: EnrollmentAgencyService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private activatedRoute: ActivatedRoute,
        private principal: Principal
    ) {
        this.currentSearch =
            this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['search']
                ? this.activatedRoute.snapshot.params['search']
                : '';
    }

    loadAll() {
        if (this.currentSearch) {
            this.enrollmentAgencyService
                .search({
                    query: this.currentSearch
                })
                .subscribe(
                    (res: HttpResponse<IEnrollmentAgency[]>) => (this.enrollmentAgencies = res.body),
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
            return;
        }
        this.enrollmentAgencyService.query().subscribe(
            (res: HttpResponse<IEnrollmentAgency[]>) => {
                this.enrollmentAgencies = res.body;
                this.currentSearch = '';
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    search(query) {
        if (!query) {
            return this.clear();
        }
        this.currentSearch = query;
        this.loadAll();
    }

    clear() {
        this.currentSearch = '';
        this.loadAll();
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInEnrollmentAgencies();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IEnrollmentAgency) {
        return item.id;
    }

    registerChangeInEnrollmentAgencies() {
        this.eventSubscriber = this.eventManager.subscribe('enrollmentAgencyListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
