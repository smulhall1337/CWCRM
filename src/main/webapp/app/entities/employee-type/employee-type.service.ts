import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IEmployeeType } from 'app/shared/model/employee-type.model';

type EntityResponseType = HttpResponse<IEmployeeType>;
type EntityArrayResponseType = HttpResponse<IEmployeeType[]>;

@Injectable({ providedIn: 'root' })
export class EmployeeTypeService {
    public resourceUrl = SERVER_API_URL + 'api/employee-types';
    public resourceSearchUrl = SERVER_API_URL + 'api/_search/employee-types';

    constructor(private http: HttpClient) {}

    create(employeeType: IEmployeeType): Observable<EntityResponseType> {
        return this.http.post<IEmployeeType>(this.resourceUrl, employeeType, { observe: 'response' });
    }

    update(employeeType: IEmployeeType): Observable<EntityResponseType> {
        return this.http.put<IEmployeeType>(this.resourceUrl, employeeType, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IEmployeeType>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IEmployeeType[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IEmployeeType[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
    }
}
