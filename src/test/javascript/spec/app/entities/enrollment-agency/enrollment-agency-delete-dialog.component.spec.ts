/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { CwcrmTestModule } from '../../../test.module';
import { EnrollmentAgencyDeleteDialogComponent } from 'app/entities/enrollment-agency/enrollment-agency-delete-dialog.component';
import { EnrollmentAgencyService } from 'app/entities/enrollment-agency/enrollment-agency.service';

describe('Component Tests', () => {
    describe('EnrollmentAgency Management Delete Component', () => {
        let comp: EnrollmentAgencyDeleteDialogComponent;
        let fixture: ComponentFixture<EnrollmentAgencyDeleteDialogComponent>;
        let service: EnrollmentAgencyService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CwcrmTestModule],
                declarations: [EnrollmentAgencyDeleteDialogComponent]
            })
                .overrideTemplate(EnrollmentAgencyDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(EnrollmentAgencyDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EnrollmentAgencyService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
