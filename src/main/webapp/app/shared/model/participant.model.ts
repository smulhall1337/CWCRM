import { Moment } from 'moment';
import { IReferral } from 'app/shared/model//referral.model';

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
    dob?: string;
    phone?: string;
    email?: string;
    zip?: string;
    manNumber?: number;
    deceased?: boolean;
    created?: Moment;
    updated?: Moment;
    isActive?: boolean;
    altContactInfo?: any;
    contactStatusId?: number;
    contactSubStatusName?: string;
    contactSubStatusId?: number;
    waiverName?: string;
    waiverId?: number;
    mcoName?: string;
    mcoId?: number;
    supportCoordinatorFirstName?: string;
    supportCoordinatorId?: number;
    primaryPhysicianFirstName?: string;
    primaryPhysicianId?: number;
    referrals?: IReferral[];
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
        public dob?: string,
        public phone?: string,
        public email?: string,
        public zip?: string,
        public manNumber?: number,
        public deceased?: boolean,
        public created?: Moment,
        public updated?: Moment,
        public isActive?: boolean,
        public altContactInfo?: any,
        public contactStatusId?: number,
        public contactSubStatusName?: string,
        public contactSubStatusId?: number,
        public waiverName?: string,
        public waiverId?: number,
        public mcoName?: string,
        public mcoId?: number,
        public supportCoordinatorFirstName?: string,
        public supportCoordinatorId?: number,
        public primaryPhysicianFirstName?: string,
        public primaryPhysicianId?: number,
        public referrals?: IReferral[]
    ) {
        this.deceased = this.deceased || false;
        this.isActive = this.isActive || false;
    }
}
