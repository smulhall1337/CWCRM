import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IAction } from 'app/shared/model/action.model';

type EntityResponseType = HttpResponse<IAction>;
type EntityArrayResponseType = HttpResponse<IAction[]>;

@Injectable({ providedIn: 'root' })
export class ActionService {
    public resourceUrl = SERVER_API_URL + 'api/actions';
    public resourceSearchUrl = SERVER_API_URL + 'api/_search/actions';

    constructor(private http: HttpClient) {}

    create(action: IAction): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(action);
        return this.http
            .post<IAction>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(action: IAction): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(action);
        return this.http
            .put<IAction>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IAction>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IAction[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IAction[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    protected convertDateFromClient(action: IAction): IAction {
        const copy: IAction = Object.assign({}, action, {
            dueDate: action.dueDate != null && action.dueDate.isValid() ? action.dueDate.format(DATE_FORMAT) : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.dueDate = res.body.dueDate != null ? moment(res.body.dueDate) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((action: IAction) => {
                action.dueDate = action.dueDate != null ? moment(action.dueDate) : null;
            });
        }
        return res;
    }
}
