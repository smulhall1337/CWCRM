import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IContactStatus } from 'app/shared/model/contact-status.model';
import { Principal } from 'app/core';
import { ContactStatusService } from './contact-status.service';

@Component({
    selector: 'jhi-contact-status',
    templateUrl: './contact-status.component.html'
})
export class ContactStatusComponent implements OnInit, OnDestroy {
    contactStatuses: IContactStatus[];
    currentAccount: any;
    eventSubscriber: Subscription;
    currentSearch: string;

    constructor(
        private contactStatusService: ContactStatusService,
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
            this.contactStatusService
                .search({
                    query: this.currentSearch
                })
                .subscribe(
                    (res: HttpResponse<IContactStatus[]>) => (this.contactStatuses = res.body),
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
            return;
        }
        this.contactStatusService.query().subscribe(
            (res: HttpResponse<IContactStatus[]>) => {
                this.contactStatuses = res.body;
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
        this.registerChangeInContactStatuses();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IContactStatus) {
        return item.id;
    }

    registerChangeInContactStatuses() {
        this.eventSubscriber = this.eventManager.subscribe('contactStatusListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
