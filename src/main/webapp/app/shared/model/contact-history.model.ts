import { Moment } from 'moment';

export interface IContactHistory {
    id?: number;
    date?: Moment;
    notes?: any;
    participantId?: number;
    userId?: number;
    contactTypeName?: string;
    contactTypeId?: number;
}

export class ContactHistory implements IContactHistory {
    constructor(
        public id?: number,
        public date?: Moment,
        public notes?: any,
        public participantId?: number,
        public userId?: number,
        public contactTypeName?: string,
        public contactTypeId?: number
    ) {}
}
