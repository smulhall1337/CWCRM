/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { CwcrmTestModule } from '../../../test.module';
import { EmployeeTypeDeleteDialogComponent } from 'app/entities/employee-type/employee-type-delete-dialog.component';
import { EmployeeTypeService } from 'app/entities/employee-type/employee-type.service';

describe('Component Tests', () => {
    describe('EmployeeType Management Delete Component', () => {
        let comp: EmployeeTypeDeleteDialogComponent;
        let fixture: ComponentFixture<EmployeeTypeDeleteDialogComponent>;
        let service: EmployeeTypeService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CwcrmTestModule],
                declarations: [EmployeeTypeDeleteDialogComponent]
            })
                .overrideTemplate(EmployeeTypeDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(EmployeeTypeDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EmployeeTypeService);
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
