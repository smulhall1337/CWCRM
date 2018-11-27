import { Moment } from 'moment';

export interface IContactHistory {
    id?: number;
    date?: Moment;
    notes?: any;
    contactTypeName?: string;
    contactTypeId?: number;
    participantId?: number;
    userId?: number;
}

export class ContactHistory implements IContactHistory {
    constructor(
        public id?: number,
        public date?: Moment,
        public notes?: any,
        public contactTypeName?: string,
        public contactTypeId?: number,
        public participantId?: number,
        public userId?: number
    ) {}
}
