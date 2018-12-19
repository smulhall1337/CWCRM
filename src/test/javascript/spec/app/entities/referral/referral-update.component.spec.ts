/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { CwcrmTestModule } from '../../../test.module';
import { ReferralUpdateComponent } from 'app/entities/referral/referral-update.component';
import { ReferralService } from 'app/entities/referral/referral.service';
import { Referral } from 'app/shared/model/referral.model';

describe('Component Tests', () => {
    describe('Referral Management Update Component', () => {
        let comp: ReferralUpdateComponent;
        let fixture: ComponentFixture<ReferralUpdateComponent>;
        let service: ReferralService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CwcrmTestModule],
                declarations: [ReferralUpdateComponent]
            })
                .overrideTemplate(ReferralUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ReferralUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ReferralService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Referral(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.referral = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Referral();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.referral = entity;
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
