import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IEmployeeType } from 'app/shared/model/employee-type.model';
import { Principal } from 'app/core';
import { EmployeeTypeService } from './employee-type.service';

@Component({
    selector: 'jhi-employee-type',
    templateUrl: './employee-type.component.html'
})
export class EmployeeTypeComponent implements OnInit, OnDestroy {
    employeeTypes: IEmployeeType[];
    currentAccount: any;
    eventSubscriber: Subscription;
    currentSearch: string;

    constructor(
        private employeeTypeService: EmployeeTypeService,
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
            this.employeeTypeService
                .search({
                    query: this.currentSearch
                })
                .subscribe(
                    (res: HttpResponse<IEmployeeType[]>) => (this.employeeTypes = res.body),
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
            return;
        }
        this.employeeTypeService.query().subscribe(
            (res: HttpResponse<IEmployeeType[]>) => {
                this.employeeTypes = res.body;
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
        this.registerChangeInEmployeeTypes();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IEmployeeType) {
        return item.id;
    }

    registerChangeInEmployeeTypes() {
        this.eventSubscriber = this.eventManager.subscribe('employeeTypeListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
