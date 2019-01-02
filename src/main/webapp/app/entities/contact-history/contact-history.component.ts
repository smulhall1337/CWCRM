import { Component, OnInit, OnDestroy, Input } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router, RoutesRecognized } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { IContactHistory } from 'app/shared/model/contact-history.model';
import { AccountService } from 'app/core';
import { ContactHistoryService } from './contact-history.service';
import { IParticipant } from 'app/shared/model/participant.model';
import { ParticipantInfoService } from 'app/shared/util/participant-info.service';

@Component({
    selector: 'jhi-contact-history',
    templateUrl: './contact-history.component.html'
})
export class ContactHistoryComponent implements OnInit, OnDestroy {
    contactHistories: IContactHistory[];
    currentAccount: any;
    eventSubscriber: Subscription;
    currentSearch: string;
    private currParticipant: any;

    constructor(
        protected contactHistoryService: ContactHistoryService,
        protected jhiAlertService: JhiAlertService,
        protected dataUtils: JhiDataUtils,
        protected eventManager: JhiEventManager,
        protected activatedRoute: ActivatedRoute,
        protected accountService: AccountService,
        protected participantInfoService: ParticipantInfoService,
        private router: Router
    ) {
        this.currentSearch =
            this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['search']
                ? this.activatedRoute.snapshot.params['search']
                : '';
    }

    loadAll() {
        if (this.currentSearch) {
            this.contactHistoryService
                .search({
                    query: this.currentSearch
                })
                .subscribe(
                    (res: HttpResponse<IContactHistory[]>) => (this.contactHistories = res.body),
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
            return;
        }
        this.contactHistoryService.query(this.currParticipant).subscribe(
            (res: HttpResponse<IContactHistory[]>) => {
                this.contactHistories = res.body;
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
        // console.log(this.router.url);
        // I dont like how this is grabbing the partiicipant ID from the URL
        // TODO: Find a better way to do this.
        const temp = this.router.url.toString().match(/\d+/);
        // console.log(temp);
        this.currParticipant = temp[0];
        console.log(this.currParticipant);
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInContactHistories();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IContactHistory) {
        return item.id;
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    registerChangeInContactHistories() {
        this.eventSubscriber = this.eventManager.subscribe('contactHistoryListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
