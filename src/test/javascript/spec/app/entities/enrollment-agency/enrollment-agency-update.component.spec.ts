/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { CwcrmTestModule } from '../../../test.module';
import { EnrollmentAgencyUpdateComponent } from 'app/entities/enrollment-agency/enrollment-agency-update.component';
import { EnrollmentAgencyService } from 'app/entities/enrollment-agency/enrollment-agency.service';
import { EnrollmentAgency } from 'app/shared/model/enrollment-agency.model';

describe('Component Tests', () => {
    describe('EnrollmentAgency Management Update Component', () => {
        let comp: EnrollmentAgencyUpdateComponent;
        let fixture: ComponentFixture<EnrollmentAgencyUpdateComponent>;
        let service: EnrollmentAgencyService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CwcrmTestModule],
                declarations: [EnrollmentAgencyUpdateComponent]
            })
                .overrideTemplate(EnrollmentAgencyUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(EnrollmentAgencyUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EnrollmentAgencyService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new EnrollmentAgency(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.enrollmentAgency = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new EnrollmentAgency();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.enrollmentAgency = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.create).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));
        });
    });
});
