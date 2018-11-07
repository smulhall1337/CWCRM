import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IParticipant } from 'app/shared/model/participant.model';

type EntityResponseType = HttpResponse<IParticipant>;
type EntityArrayResponseType = HttpResponse<IParticipant[]>;

@Injectable({ providedIn: 'root' })
export class ParticipantService {
    public resourceUrl = SERVER_API_URL + 'api/participants';
    public resourceSearchUrl = SERVER_API_URL + 'api/_search/participants';

    constructor(private http: HttpClient) {}

    create(participant: IParticipant): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(participant);
        return this.http
            .post<IParticipant>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(participant: IParticipant): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(participant);
        return this.http
            .put<IParticipant>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IParticipant>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IParticipant[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IParticipant[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    protected convertDateFromClient(participant: IParticipant): IParticipant {
        const copy: IParticipant = Object.assign({}, participant, {
            registrationDate:
                participant.registrationDate != null && participant.registrationDate.isValid()
                    ? participant.registrationDate.format(DATE_FORMAT)
                    : null,
            created: participant.created != null && participant.created.isValid() ? participant.created.toJSON() : null,
            updated: participant.updated != null && participant.updated.isValid() ? participant.updated.toJSON() : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.registrationDate = res.body.registrationDate != null ? moment(res.body.registrationDate) : null;
            res.body.created = res.body.created != null ? moment(res.body.created) : null;
            res.body.updated = res.body.updated != null ? moment(res.body.updated) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((participant: IParticipant) => {
                participant.registrationDate = participant.registrationDate != null ? moment(participant.registrationDate) : null;
                participant.created = participant.created != null ? moment(participant.created) : null;
                participant.updated = participant.updated != null ? moment(participant.updated) : null;
            });
        }
        return res;
    }
}
