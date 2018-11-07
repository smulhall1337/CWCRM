/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CwcrmTestModule } from '../../../test.module';
import { EnrollmentAgencyDetailComponent } from 'app/entities/enrollment-agency/enrollment-agency-detail.component';
import { EnrollmentAgency } from 'app/shared/model/enrollment-agency.model';

describe('Component Tests', () => {
    describe('EnrollmentAgency Management Detail Component', () => {
        let comp: EnrollmentAgencyDetailComponent;
        let fixture: ComponentFixture<EnrollmentAgencyDetailComponent>;
        const route = ({ data: of({ enrollmentAgency: new EnrollmentAgency(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CwcrmTestModule],
                declarations: [EnrollmentAgencyDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(EnrollmentAgencyDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(EnrollmentAgencyDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.enrollmentAgency).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
