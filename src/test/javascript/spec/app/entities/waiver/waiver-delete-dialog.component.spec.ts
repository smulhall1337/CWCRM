/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { CwcrmTestModule } from '../../../test.module';
import { WaiverDeleteDialogComponent } from 'app/entities/waiver/waiver-delete-dialog.component';
import { WaiverService } from 'app/entities/waiver/waiver.service';

describe('Component Tests', () => {
    describe('Waiver Management Delete Component', () => {
        let comp: WaiverDeleteDialogComponent;
        let fixture: ComponentFixture<WaiverDeleteDialogComponent>;
        let service: WaiverService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CwcrmTestModule],
                declarations: [WaiverDeleteDialogComponent]
            })
                .overrideTemplate(WaiverDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(WaiverDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(WaiverService);
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
