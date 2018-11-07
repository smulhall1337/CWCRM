import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CwcrmSharedModule } from 'app/shared';
import {
    EmployeeSubTypeComponent,
    EmployeeSubTypeDetailComponent,
    EmployeeSubTypeUpdateComponent,
    EmployeeSubTypeDeletePopupComponent,
    EmployeeSubTypeDeleteDialogComponent,
    employeeSubTypeRoute,
    employeeSubTypePopupRoute
} from './';

const ENTITY_STATES = [...employeeSubTypeRoute, ...employeeSubTypePopupRoute];

@NgModule({
    imports: [CwcrmSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        EmployeeSubTypeComponent,
        EmployeeSubTypeDetailComponent,
        EmployeeSubTypeUpdateComponent,
        EmployeeSubTypeDeleteDialogComponent,
        EmployeeSubTypeDeletePopupComponent
    ],
    entryComponents: [
        EmployeeSubTypeComponent,
        EmployeeSubTypeUpdateComponent,
        EmployeeSubTypeDeleteDialogComponent,
        EmployeeSubTypeDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CwcrmEmployeeSubTypeModule {}
