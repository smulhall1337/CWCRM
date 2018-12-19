import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IMCO } from 'app/shared/model/mco.model';
import { AccountService } from 'app/core';
import { MCOService } from './mco.service';

@Component({
    selector: 'jhi-mco',
    templateUrl: './mco.component.html'
})
export class MCOComponent implements OnInit, OnDestroy {
    mCOS: IMCO[];
    currentAccount: any;
    eventSubscriber: Subscription;
    currentSearch: string;

    constructor(
        protected mCOService: MCOService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected activatedRoute: ActivatedRoute,
        protected accountService: AccountService
    ) {
        this.currentSearch =
            this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['search']
                ? this.activatedRoute.snapshot.params['search']
                : '';
    }

    loadAll() {
        if (this.currentSearch) {
            this.mCOService
                .search({
                    query: this.currentSearch
                })
                .subscribe((res: HttpResponse<IMCO[]>) => (this.mCOS = res.body), (res: HttpErrorResponse) => this.onError(res.message));
            return;
        }
        this.mCOService.query().subscribe(
            (res: HttpResponse<IMCO[]>) => {
                this.mCOS = res.body;
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
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInMCOS();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IMCO) {
        return item.id;
    }

    registerChangeInMCOS() {
        this.eventSubscriber = this.eventManager.subscribe('mCOListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
