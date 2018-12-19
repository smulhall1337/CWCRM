/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { CwcrmTestModule } from '../../../test.module';
import { EmployeeSubTypeDeleteDialogComponent } from 'app/entities/employee-sub-type/employee-sub-type-delete-dialog.component';
import { EmployeeSubTypeService } from 'app/entities/employee-sub-type/employee-sub-type.service';

describe('Component Tests', () => {
    describe('EmployeeSubType Management Delete Component', () => {
        let comp: EmployeeSubTypeDeleteDialogComponent;
        let fixture: ComponentFixture<EmployeeSubTypeDeleteDialogComponent>;
        let service: EmployeeSubTypeService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CwcrmTestModule],
                declarations: [EmployeeSubTypeDeleteDialogComponent]
            })
                .overrideTemplate(EmployeeSubTypeDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(EmployeeSubTypeDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EmployeeSubTypeService);
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
