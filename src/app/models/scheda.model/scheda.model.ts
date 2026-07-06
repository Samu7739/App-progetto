export interface CreaSchedaCompletaRequest {
  nome: string;
  obiettivo: string;
  numeroGiorni: number;
  note: string;
  utenteId: number;
  giorni: GiornoSchedaRequest[];
}

export interface GiornoSchedaRequest {
  nome: string;
  ordine: number;
  note: string;
  categorieMuscolariIds: number[];
  esercizi: EsercizioSchedaRequest[];
}

export interface EsercizioSchedaRequest {
  esercizioId: number;
  ordine: number;
  serie: number;
  ripetizioniTarget: string;
  recuperoSecondi: number;
  note: string;
}

export interface SchedaCreataResponse {
  idScheda: number;
  messaggio: string;
}

export interface SchedaDettaglioResponse {
  id: number;
  nome: string;
  obiettivo: string;
  numeroGiorni: number;
  note: string;
  giorni: GiornoSchedaResponse[];
}

export interface GiornoSchedaResponse {
  id: number;
  nome: string;
  ordine: number;
  note: string;
  categorieMuscolari: string[];
  esercizi: EsercizioSchedaResponse[];
}

export interface EsercizioSchedaResponse {
  id: number;
  esercizioId: number;
  nomeEsercizio: string;
  categoriaMuscolare: string;
  ordine: number;
  serie: number;
  ripetizioniTarget: string;
  recuperoSecondi: number;
  note: string;
}