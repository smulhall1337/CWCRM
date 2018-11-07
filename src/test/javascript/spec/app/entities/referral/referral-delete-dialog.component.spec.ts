/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { CwcrmTestModule } from '../../../test.module';
import { ReferralDeleteDialogComponent } from 'app/entities/referral/referral-delete-dialog.component';
import { ReferralService } from 'app/entities/referral/referral.service';

describe('Component Tests', () => {
    describe('Referral Management Delete Component', () => {
        let comp: ReferralDeleteDialogComponent;
        let fixture: ComponentFixture<ReferralDeleteDialogComponent>;
        let service: ReferralService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CwcrmTestModule],
                declarations: [ReferralDeleteDialogComponent]
            })
                .overrideTemplate(ReferralDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ReferralDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ReferralService);
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
