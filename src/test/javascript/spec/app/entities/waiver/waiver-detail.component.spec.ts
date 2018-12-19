/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CwcrmTestModule } from '../../../test.module';
import { WaiverDetailComponent } from 'app/entities/waiver/waiver-detail.component';
import { Waiver } from 'app/shared/model/waiver.model';

describe('Component Tests', () => {
    describe('Waiver Management Detail Component', () => {
        let comp: WaiverDetailComponent;
        let fixture: ComponentFixture<WaiverDetailComponent>;
        const route = ({ data: of({ waiver: new Waiver(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CwcrmTestModule],
                declarations: [WaiverDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(WaiverDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(WaiverDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.waiver).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
