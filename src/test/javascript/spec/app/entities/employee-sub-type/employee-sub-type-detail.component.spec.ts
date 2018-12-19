/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CwcrmTestModule } from '../../../test.module';
import { EmployeeSubTypeDetailComponent } from 'app/entities/employee-sub-type/employee-sub-type-detail.component';
import { EmployeeSubType } from 'app/shared/model/employee-sub-type.model';

describe('Component Tests', () => {
    describe('EmployeeSubType Management Detail Component', () => {
        let comp: EmployeeSubTypeDetailComponent;
        let fixture: ComponentFixture<EmployeeSubTypeDetailComponent>;
        const route = ({ data: of({ employeeSubType: new EmployeeSubType(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CwcrmTestModule],
                declarations: [EmployeeSubTypeDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(EmployeeSubTypeDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(EmployeeSubTypeDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.employeeSubType).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
