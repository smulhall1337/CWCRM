import { Moment } from 'moment';

export interface IParticipant {
    id?: number;
    firstName?: string;
    middleInitial?: string;
    lastName?: string;
    title?: string;
    registrationDate?: Moment;
    address?: string;
    city?: string;
    state?: string;
    country?: string;
    dob?: Moment;
    primaryPhone?: string;
    primaryPhoneType?: string;
    secondaryPhone?: string;
    secondaryPhoneType?: string;
    email?: string;
    zip?: string;
    medicareIdNumber?: string;
    medicaidIdNumber?: string;
    gender?: string;
    contactStatusId?: number;
    contactSubStatusName?: string;
    contactSubStatusId?: number;
    mcoName?: string;
    mcoId?: number;
    referralTypeName?: string;
    referralTypeId?: number;
    referralSourceName?: string;
    referralSourceId?: number;
    assignedToFirstName?: string;
    assignedToId?: number;
    actionId?: number;
}

export class Participant implements IParticipant {
    constructor(
        public id?: number,
        public firstName?: string,
        public middleInitial?: string,
        public lastName?: string,
        public title?: string,
        public registrationDate?: Moment,
        public address?: string,
        public city?: string,
        public state?: string,
        public country?: string,
        public dob?: Moment,
        public primaryPhone?: string,
        public primaryPhoneType?: string,
        public secondaryPhone?: string,
        public secondaryPhoneType?: string,
        public email?: string,
        public zip?: string,
        public medicareIdNumber?: string,
        public medicaidIdNumber?: string,
        public gender?: string,
        public contactStatusId?: number,
        public contactSubStatusName?: string,
        public contactSubStatusId?: number,
        public mcoName?: string,
        public mcoId?: number,
        public referralTypeName?: string,
        public referralTypeId?: number,
        public referralSourceName?: string,
        public referralSourceId?: number,
        public assignedToFirstName?: string,
        public assignedToId?: number,
        public actionId?: number
    ) {}
}
