import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IContactSubStatus } from 'app/shared/model/contact-sub-status.model';
import { AccountService } from 'app/core';
import { ContactSubStatusService } from './contact-sub-status.service';

@Component({
    selector: 'jhi-contact-sub-status',
    templateUrl: './contact-sub-status.component.html'
})
export class ContactSubStatusComponent implements OnInit, OnDestroy {
    contactSubStatuses: IContactSubStatus[];
    currentAccount: any;
    eventSubscriber: Subscription;
    currentSearch: string;

    constructor(
        protected contactSubStatusService: ContactSubStatusService,
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
            this.contactSubStatusService
                .search({
                    query: this.currentSearch
                })
                .subscribe(
                    (res: HttpResponse<IContactSubStatus[]>) => (this.contactSubStatuses = res.body),
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
            return;
        }
        this.contactSubStatusService.query().subscribe(
            (res: HttpResponse<IContactSubStatus[]>) => {
                this.contactSubStatuses = res.body;
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
        this.registerChangeInContactSubStatuses();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IContactSubStatus) {
        return item.id;
    }

    registerChangeInContactSubStatuses() {
        this.eventSubscriber = this.eventManager.subscribe('contactSubStatusListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
