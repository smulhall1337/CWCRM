/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { CwcrmTestModule } from '../../../test.module';
import { ContactStatusDeleteDialogComponent } from 'app/entities/contact-status/contact-status-delete-dialog.component';
import { ContactStatusService } from 'app/entities/contact-status/contact-status.service';

describe('Component Tests', () => {
    describe('ContactStatus Management Delete Component', () => {
        let comp: ContactStatusDeleteDialogComponent;
        let fixture: ComponentFixture<ContactStatusDeleteDialogComponent>;
        let service: ContactStatusService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CwcrmTestModule],
                declarations: [ContactStatusDeleteDialogComponent]
            })
                .overrideTemplate(ContactStatusDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ContactStatusDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ContactStatusService);
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
