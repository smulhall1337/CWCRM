/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { CwcrmTestModule } from '../../../test.module';
import { EnrollmentAgencyComponent } from 'app/entities/enrollment-agency/enrollment-agency.component';
import { EnrollmentAgencyService } from 'app/entities/enrollment-agency/enrollment-agency.service';
import { EnrollmentAgency } from 'app/shared/model/enrollment-agency.model';

describe('Component Tests', () => {
    describe('EnrollmentAgency Management Component', () => {
        let comp: EnrollmentAgencyComponent;
        let fixture: ComponentFixture<EnrollmentAgencyComponent>;
        let service: EnrollmentAgencyService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CwcrmTestModule],
                declarations: [EnrollmentAgencyComponent],
                providers: []
            })
                .overrideTemplate(EnrollmentAgencyComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(EnrollmentAgencyComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EnrollmentAgencyService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new EnrollmentAgency(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.enrollmentAgencies[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
