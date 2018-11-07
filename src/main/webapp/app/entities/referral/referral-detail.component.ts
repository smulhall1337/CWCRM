import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IReferral } from 'app/shared/model/referral.model';

@Component({
    selector: 'jhi-referral-detail',
    templateUrl: './referral-detail.component.html'
})
export class ReferralDetailComponent implements OnInit {
    referral: IReferral;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ referral }) => {
            this.referral = referral;
        });
    }

    previousState() {
        window.history.back();
    }
}
