export interface IParticipantNotes {
    id?: number;
    notes?: any;
    participantId?: number;
    userId?: number;
}

export class ParticipantNotes implements IParticipantNotes {
    constructor(public id?: number, public notes?: any, public participantId?: number, public userId?: number) {}
}
