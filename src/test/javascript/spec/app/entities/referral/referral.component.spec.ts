/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { CwcrmTestModule } from '../../../test.module';
import { ReferralComponent } from 'app/entities/referral/referral.component';
import { ReferralService } from 'app/entities/referral/referral.service';
import { Referral } from 'app/shared/model/referral.model';

describe('Component Tests', () => {
    describe('Referral Management Component', () => {
        let comp: ReferralComponent;
        let fixture: ComponentFixture<ReferralComponent>;
        let service: ReferralService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CwcrmTestModule],
                declarations: [ReferralComponent],
                providers: []
            })
                .overrideTemplate(ReferralComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ReferralComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ReferralService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Referral(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.referrals[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
