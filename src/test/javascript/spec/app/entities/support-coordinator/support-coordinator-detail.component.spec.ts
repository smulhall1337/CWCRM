/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CwcrmTestModule } from '../../../test.module';
import { SupportCoordinatorDetailComponent } from 'app/entities/support-coordinator/support-coordinator-detail.component';
import { SupportCoordinator } from 'app/shared/model/support-coordinator.model';

describe('Component Tests', () => {
    describe('SupportCoordinator Management Detail Component', () => {
        let comp: SupportCoordinatorDetailComponent;
        let fixture: ComponentFixture<SupportCoordinatorDetailComponent>;
        const route = ({ data: of({ supportCoordinator: new SupportCoordinator(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CwcrmTestModule],
                declarations: [SupportCoordinatorDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(SupportCoordinatorDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SupportCoordinatorDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.supportCoordinator).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
