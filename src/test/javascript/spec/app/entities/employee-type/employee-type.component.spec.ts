/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { CwcrmTestModule } from '../../../test.module';
import { EmployeeTypeComponent } from 'app/entities/employee-type/employee-type.component';
import { EmployeeTypeService } from 'app/entities/employee-type/employee-type.service';
import { EmployeeType } from 'app/shared/model/employee-type.model';

describe('Component Tests', () => {
    describe('EmployeeType Management Component', () => {
        let comp: EmployeeTypeComponent;
        let fixture: ComponentFixture<EmployeeTypeComponent>;
        let service: EmployeeTypeService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CwcrmTestModule],
                declarations: [EmployeeTypeComponent],
                providers: []
            })
                .overrideTemplate(EmployeeTypeComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(EmployeeTypeComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EmployeeTypeService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new EmployeeType(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.employeeTypes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
