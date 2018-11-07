import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CwcrmSharedModule } from 'app/shared';
import {
    EmployeeTypeComponent,
    EmployeeTypeDetailComponent,
    EmployeeTypeUpdateComponent,
    EmployeeTypeDeletePopupComponent,
    EmployeeTypeDeleteDialogComponent,
    employeeTypeRoute,
    employeeTypePopupRoute
} from './';

const ENTITY_STATES = [...employeeTypeRoute, ...employeeTypePopupRoute];

@NgModule({
    imports: [CwcrmSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        EmployeeTypeComponent,
        EmployeeTypeDetailComponent,
        EmployeeTypeUpdateComponent,
        EmployeeTypeDeleteDialogComponent,
        EmployeeTypeDeletePopupComponent
    ],
    entryComponents: [
        EmployeeTypeComponent,
        EmployeeTypeUpdateComponent,
        EmployeeTypeDeleteDialogComponent,
        EmployeeTypeDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CwcrmEmployeeTypeModule {}
