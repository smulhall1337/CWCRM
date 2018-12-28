import { Injectable } from '@angular/core';
import { IParticipant } from 'app/shared/model/participant.model';

@Injectable()
export class ParticipantInfoService {
    storedParticipant: IParticipant;
}
