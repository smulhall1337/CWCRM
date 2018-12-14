import { Moment } from 'moment';
import { IParticipantNotes } from 'app/shared/model//participant-notes.model';
import { IContactHistory } from 'app/shared/model//contact-history.model';
import { IAction } from 'app/shared/model//action.model';

export interface IParticipant {
    id?: number;
    firstName?: string;
    lastName?: string;
    registrationDate?: Moment;
    address1?: string;
    address2?: string;
    city?: string;
    state?: string;
    country?: string;
    dob?: Moment;
    phone?: string;
    email?: string;
    zip?: string;
    mANNumber?: number;
    contactStatusId?: number;
    contactSubStatusName?: string;
    contactSubStatusId?: number;
    waiverName?: string;
    waiverId?: number;
    supportCoordinatorId?: number;
    participantNotes?: IParticipantNotes[];
    contactHistories?: IContactHistory[];
    actions?: IAction[];
}

export class Participant implements IParticipant {
    constructor(
        public id?: number,
        public firstName?: string,
        public lastName?: string,
        public registrationDate?: Moment,
        public address1?: string,
        public address2?: string,
        public city?: string,
        public state?: string,
        public country?: string,
        public dob?: Moment,
        public phone?: string,
        public email?: string,
        public zip?: string,
        public mANNumber?: number,
        public contactStatusId?: number,
        public contactSubStatusName?: string,
        public contactSubStatusId?: number,
        public waiverName?: string,
        public waiverId?: number,
        public supportCoordinatorId?: number,
        public participantNotes?: IParticipantNotes[],
        public contactHistories?: IContactHistory[],
        public actions?: IAction[]
    ) {}
}
