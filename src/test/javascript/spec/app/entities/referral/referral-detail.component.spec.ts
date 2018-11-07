/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CwcrmTestModule } from '../../../test.module';
import { ReferralDetailComponent } from 'app/entities/referral/referral-detail.component';
import { Referral } from 'app/shared/model/referral.model';

describe('Component Tests', () => {
    describe('Referral Management Detail Component', () => {
        let comp: ReferralDetailComponent;
        let fixture: ComponentFixture<ReferralDetailComponent>;
        const route = ({ data: of({ referral: new Referral(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CwcrmTestModule],
                declarations: [ReferralDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ReferralDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ReferralDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.referral).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
