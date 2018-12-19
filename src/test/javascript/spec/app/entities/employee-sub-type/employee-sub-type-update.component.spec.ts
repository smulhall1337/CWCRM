/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { CwcrmTestModule } from '../../../test.module';
import { EmployeeSubTypeUpdateComponent } from 'app/entities/employee-sub-type/employee-sub-type-update.component';
import { EmployeeSubTypeService } from 'app/entities/employee-sub-type/employee-sub-type.service';
import { EmployeeSubType } from 'app/shared/model/employee-sub-type.model';

describe('Component Tests', () => {
    describe('EmployeeSubType Management Update Component', () => {
        let comp: EmployeeSubTypeUpdateComponent;
        let fixture: ComponentFixture<EmployeeSubTypeUpdateComponent>;
        let service: EmployeeSubTypeService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CwcrmTestModule],
                declarations: [EmployeeSubTypeUpdateComponent]
            })
                .overrideTemplate(EmployeeSubTypeUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(EmployeeSubTypeUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EmployeeSubTypeService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new EmployeeSubType(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.employeeSubType = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new EmployeeSubType();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.employeeSubType = entity;
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
