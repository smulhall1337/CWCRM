export interface IReferral {
    id?: number;
    name?: string;
    participantId?: number;
}

export class Referral implements IReferral {
    constructor(public id?: number, public name?: string, public participantId?: number) {}
}
