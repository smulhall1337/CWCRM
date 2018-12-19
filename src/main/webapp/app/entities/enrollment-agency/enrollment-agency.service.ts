import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IEnrollmentAgency } from 'app/shared/model/enrollment-agency.model';

type EntityResponseType = HttpResponse<IEnrollmentAgency>;
type EntityArrayResponseType = HttpResponse<IEnrollmentAgency[]>;

@Injectable({ providedIn: 'root' })
export class EnrollmentAgencyService {
    public resourceUrl = SERVER_API_URL + 'api/enrollment-agencies';
    public resourceSearchUrl = SERVER_API_URL + 'api/_search/enrollment-agencies';

    constructor(protected http: HttpClient) {}

    create(enrollmentAgency: IEnrollmentAgency): Observable<EntityResponseType> {
        return this.http.post<IEnrollmentAgency>(this.resourceUrl, enrollmentAgency, { observe: 'response' });
    }

    update(enrollmentAgency: IEnrollmentAgency): Observable<EntityResponseType> {
        return this.http.put<IEnrollmentAgency>(this.resourceUrl, enrollmentAgency, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IEnrollmentAgency>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IEnrollmentAgency[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IEnrollmentAgency[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
    }
}
