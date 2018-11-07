import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPhysician } from 'app/shared/model/physician.model';

type EntityResponseType = HttpResponse<IPhysician>;
type EntityArrayResponseType = HttpResponse<IPhysician[]>;

@Injectable({ providedIn: 'root' })
export class PhysicianService {
    public resourceUrl = SERVER_API_URL + 'api/physicians';
    public resourceSearchUrl = SERVER_API_URL + 'api/_search/physicians';

    constructor(private http: HttpClient) {}

    create(physician: IPhysician): Observable<EntityResponseType> {
        return this.http.post<IPhysician>(this.resourceUrl, physician, { observe: 'response' });
    }

    update(physician: IPhysician): Observable<EntityResponseType> {
        return this.http.put<IPhysician>(this.resourceUrl, physician, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IPhysician>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IPhysician[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IPhysician[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
    }
}
