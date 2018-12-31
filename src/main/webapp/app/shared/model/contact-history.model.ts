import { Moment } from 'moment';

export interface IContactHistory {
    id?: number;
    date?: Moment;
    notes?: any;
    participantFirstName?: string;
    participantLastName?: string;
    participantId?: number;
    userFirstName?: string;
    userId?: number;
    contactTypeName?: string;
    contactTypeId?: number;
}

export class ContactHistory implements IContactHistory {
    constructor(
        public id?: number,
        public date?: Moment,
        public notes?: any,
        public participantFirstName?: string,
        public participantLastName?: string,
        public participantId?: number,
        public userFirstName?: string,
        public userId?: number,
        public contactTypeName?: string,
        public contactTypeId?: number
    ) {}
}
