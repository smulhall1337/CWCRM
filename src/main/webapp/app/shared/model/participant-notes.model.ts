export interface IParticipantNotes {
    id?: number;
    notes?: any;
    participantFirstName?: string;
    participantId?: number;
    userFirstName?: string;
    userId?: number;
}

export class ParticipantNotes implements IParticipantNotes {
    constructor(
        public id?: number,
        public notes?: any,
        public participantFirstName?: string,
        public participantId?: number,
        public userFirstName?: string,
        public userId?: number
    ) {}
}
