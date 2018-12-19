/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { CwcrmTestModule } from '../../../test.module';
import { ReferralSourceDeleteDialogComponent } from 'app/entities/referral-source/referral-source-delete-dialog.component';
import { ReferralSourceService } from 'app/entities/referral-source/referral-source.service';

describe('Component Tests', () => {
    describe('ReferralSource Management Delete Component', () => {
        let comp: ReferralSourceDeleteDialogComponent;
        let fixture: ComponentFixture<ReferralSourceDeleteDialogComponent>;
        let service: ReferralSourceService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CwcrmTestModule],
                declarations: [ReferralSourceDeleteDialogComponent]
            })
                .overrideTemplate(ReferralSourceDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ReferralSourceDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ReferralSourceService);
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
