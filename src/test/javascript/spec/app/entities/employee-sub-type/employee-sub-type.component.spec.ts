/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { CwcrmTestModule } from '../../../test.module';
import { EmployeeSubTypeComponent } from 'app/entities/employee-sub-type/employee-sub-type.component';
import { EmployeeSubTypeService } from 'app/entities/employee-sub-type/employee-sub-type.service';
import { EmployeeSubType } from 'app/shared/model/employee-sub-type.model';

describe('Component Tests', () => {
    describe('EmployeeSubType Management Component', () => {
        let comp: EmployeeSubTypeComponent;
        let fixture: ComponentFixture<EmployeeSubTypeComponent>;
        let service: EmployeeSubTypeService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CwcrmTestModule],
                declarations: [EmployeeSubTypeComponent],
                providers: []
            })
                .overrideTemplate(EmployeeSubTypeComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(EmployeeSubTypeComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EmployeeSubTypeService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new EmployeeSubType(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.employeeSubTypes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
