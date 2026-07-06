import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';

import { CategoriaMuscolare } from '../../models/categoria-muscolare.model/categoria-muscolare.model';
import { Esercizio } from '../../models/esercizio.model/esercizio.model';
import {
  CreaSchedaCompletaRequest,
  EsercizioSchedaRequest,
  GiornoSchedaRequest
} from '../../models/scheda.model/scheda.model';

import { CategoriaService } from '../../services/categoria.service';
import { EsercizioService } from '../../services/esercizio.service';
import { SchedaService } from '../../services/scheda.service';

interface EsercizioForm {
  categoriaId: number | null;
  eserciziDisponibili: Esercizio[];
  esercizioId: number | null;

  serie: number;
  seriePersonalizzate: number | null;
  usaSeriePersonalizzate: boolean;

  ripetizioniTarget: string;
  ripetizioniPersonalizzate: string;
  usaRipetizioniPersonalizzate: boolean;

  recuperoSecondi: number;
  recuperoPersonalizzato: number | null;
  usaRecuperoPersonalizzato: boolean;

  note: string;
}

interface GiornoForm {
  nome: string;
  ordine: number;
  note: string;
  esercizi: EsercizioForm[];
  paginaEsercizi: number;
  riepilogoAperto:boolean;
}

@Component({
  selector: 'app-crea-scheda',
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './crea-scheda.html',
  styleUrl: './crea-scheda.css'
})
export class CreaScheda implements OnInit {

  stepCorrente = 0;

  nome = '';
  obiettivo = '';
  obiettivoPersonalizzato = '';
  numeroGiorni = 3;
  note = '';

  giorni: GiornoForm[] = [];
  categorie: CategoriaMuscolare[] = [];

  loadingCategorie = false;
  saving = false;
  errore = '';
  successo = '';

  private readonly utenteIdProvvisorio = 1;
  readonly eserciziPerPagina = 3;

  obiettivi: string[] = [
    'Ipertrofia',
    'Forza',
    'Definizione',
    'Dimagrimento',
    'Mantenimento',
    'Ricondizionamento',
    'Personalizzato'
  ];

  giorniSettimana: string[] = [
    'Lunedì',
    'Martedì',
    'Mercoledì',
    'Giovedì',
    'Venerdì',
    'Sabato',
    'Domenica'
  ];

  opzioniNumeroGiorni: number[] = [1, 2, 3, 4, 5, 6, 7];

  opzioniSerie: string[] = [
    '1', '2', '3', '4', '5', '6', '7', '8', '9', '10', 'Personalizza'
  ];

  opzioniRipetizioni: string[] = [
    '4', '6', '8', '10', '12', 'Personalizza'
  ];

  opzioniRecupero = [
    { label: '30 sec', value: 30 },
    { label: '45 sec', value: 45 },
    { label: '60 sec', value: 60 },
    { label: '90 sec', value: 90 },
    { label: '120 sec', value: 120 },
    { label: '180 sec', value: 180 },
    { label: 'Personalizza', value: -1 }
  ];

  constructor(
    private categoriaService: CategoriaService,
    private esercizioService: EsercizioService,
    private schedaService: SchedaService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.caricaCategorie();
    this.generaGiorni();
  }

  caricaCategorie(): void {
    this.loadingCategorie = true;

    this.categoriaService.getCategorie().subscribe({
      next: (categorie) => {
        this.categorie = categorie;
        this.loadingCategorie = false;
      },
      error: (error) => {
        console.error(error);
        this.errore = 'Errore nel caricamento delle categorie.';
        this.loadingCategorie = false;
      }
    });
  }
testoNumeroEsercizi(giorno: GiornoForm): string {
  const numero=giorno.esercizi.length;
  if(numero==1){
    return "Esercizio 1";
  }
  return ` Esercizio ${numero}`;
}
toggleRiepilogoGiorno(giorno:GiornoForm):void{
  giorno.riepilogoAperto = !giorno.riepilogoAperto;
}
eserciziRiepilogoVisibili(giorno:GiornoForm):EsercizioForm[]{
  if(giorno.riepilogoAperto){
    return giorno.esercizi;
  }
  return giorno.esercizi.slice(0,3);
}
  generaGiorni(): void {
    this.giorni = [];

    for (let i = 1; i <= this.numeroGiorni; i++) {
      this.giorni.push({
        riepilogoAperto:false,
        nome: this.giorniSettimana[i - 1] ?? `Giorno ${i}`,
        ordine: i,
        note: '',
        paginaEsercizi: 0,
        esercizi: [
          this.creaEsercizioVuoto()
        ]
      });
    }
  }

  creaEsercizioVuoto(): EsercizioForm {
    return {
      categoriaId: null,
      eserciziDisponibili: [],
      esercizioId: null,

      serie: 3,
      seriePersonalizzate: null,
      usaSeriePersonalizzate: false,

      ripetizioniTarget: '8',
      ripetizioniPersonalizzate: '',
      usaRipetizioniPersonalizzate: false,

      recuperoSecondi: 90,
      recuperoPersonalizzato: null,
      usaRecuperoPersonalizzato: false,

      note: ''
    };
  }

  onNumeroGiorniChange(): void {
    this.generaGiorni();
  }

  confermaDatiScheda(): void {
    this.errore = '';

    if (!this.nome.trim()) {
      this.errore = 'Inserisci il nome della scheda.';
      return;
    }

    if (!this.obiettivo) {
      this.errore = 'Seleziona un obiettivo.';
      return;
    }

    if (this.obiettivo === 'Personalizzato' && !this.obiettivoPersonalizzato.trim()) {
      this.errore = 'Inserisci l’obiettivo personalizzato.';
      return;
    }

    if (!this.numeroGiorni || this.numeroGiorni < 1 || this.numeroGiorni > 7) {
      this.errore = 'Seleziona un numero di giorni valido.';
      return;
    }

    this.stepCorrente = 1;
  }

  get giornoCorrente(): GiornoForm | null {
    if (this.stepCorrente <= 0 || this.stepCorrente > this.numeroGiorni) {
      return null;
    }

    return this.giorni[this.stepCorrente - 1];
  }

  get isStepDatiScheda(): boolean {
    return this.stepCorrente === 0;
  }

  get isStepRiepilogo(): boolean {
    return this.stepCorrente === this.numeroGiorni + 1;
  }

  get titoloStep(): string {
    if (this.isStepDatiScheda) {
      return 'Dati scheda';
    }

    if (this.isStepRiepilogo) {
      return 'Riepilogo finale';
    }

    return `Giorno ${this.stepCorrente}`;
  }

  get obiettivoFinale(): string {
    if (this.obiettivo === 'Personalizzato') {
      return this.obiettivoPersonalizzato;
    }

    return this.obiettivo;
  }

  eserciziVisibili(giorno: GiornoForm): EsercizioForm[] {
    const start = giorno.paginaEsercizi * this.eserciziPerPagina;
    const end = start + this.eserciziPerPagina;

    return giorno.esercizi.slice(start, end);
  }

  indiceEsercizioReale(giorno: GiornoForm, indexVisibile: number): number {
    return giorno.paginaEsercizi * this.eserciziPerPagina + indexVisibile;
  }

  haPaginaEserciziPrecedente(giorno: GiornoForm): boolean {
    return giorno.paginaEsercizi > 0;
  }

  haPaginaEserciziSuccessiva(giorno: GiornoForm): boolean {
    const prossimoStart = (giorno.paginaEsercizi + 1) * this.eserciziPerPagina;
    return prossimoStart < giorno.esercizi.length;
  }

  paginaEserciziPrecedente(giorno: GiornoForm): void {
    if (this.haPaginaEserciziPrecedente(giorno)) {
      giorno.paginaEsercizi--;
    }
  }

  paginaEserciziSuccessiva(giorno: GiornoForm): void {
    if (this.haPaginaEserciziSuccessiva(giorno)) {
      giorno.paginaEsercizi++;
    }
  }

  aggiungiEsercizio(giorno: GiornoForm): void {
    giorno.esercizi.push(this.creaEsercizioVuoto());

    const ultimaPagina = Math.floor((giorno.esercizi.length - 1) / this.eserciziPerPagina);
    giorno.paginaEsercizi = ultimaPagina;
  }

  rimuoviEsercizio(giorno: GiornoForm, indexReale: number): void {
    this.errore = '';

    if (giorno.esercizi.length === 1) {
      this.errore = 'Ogni giorno deve avere almeno un esercizio.';
      return;
    }

    giorno.esercizi.splice(indexReale, 1);

    const ultimaPagina = Math.floor((giorno.esercizi.length - 1) / this.eserciziPerPagina);

    if (giorno.paginaEsercizi > ultimaPagina) {
      giorno.paginaEsercizi = ultimaPagina;
    }
  }

  onCategoriaChange(esercizioForm: EsercizioForm): void {
    esercizioForm.esercizioId = null;
    esercizioForm.eserciziDisponibili = [];

    if (!esercizioForm.categoriaId) {
      return;
    }

    this.esercizioService.getEserciziByCategoria(esercizioForm.categoriaId).subscribe({
      next: (esercizi) => {
        esercizioForm.eserciziDisponibili = esercizi;
      },
      error: (error) => {
        console.error(error);
        this.errore = 'Errore nel caricamento degli esercizi.';
      }
    });
  }

  onSerieChange(esercizioForm: EsercizioForm, value: string): void {
    if (value === 'Personalizza') {
      esercizioForm.usaSeriePersonalizzate = true;
      esercizioForm.seriePersonalizzate = null;
      return;
    }

    esercizioForm.usaSeriePersonalizzate = false;
    esercizioForm.serie = Number(value);
  }

  onRipetizioniChange(esercizioForm: EsercizioForm, value: string): void {
    if (value === 'Personalizza') {
      esercizioForm.usaRipetizioniPersonalizzate = true;
      esercizioForm.ripetizioniPersonalizzate = '';
      return;
    }

    esercizioForm.usaRipetizioniPersonalizzate = false;
    esercizioForm.ripetizioniTarget = value;
  }

  onRecuperoChange(esercizioForm: EsercizioForm, value: number): void {
    if (Number(value) === -1) {
      esercizioForm.usaRecuperoPersonalizzato = true;
      esercizioForm.recuperoPersonalizzato = null;
      return;
    }

    esercizioForm.usaRecuperoPersonalizzato = false;
    esercizioForm.recuperoSecondi = Number(value);
  }

  confermaGiorno(): void {
    this.errore = '';

    const giorno = this.giornoCorrente;

    if (!giorno) {
      return;
    }

    if (!giorno.nome) {
      this.errore = 'Seleziona il giorno della settimana.';
      return;
    }

    if (giorno.esercizi.length === 0) {
      this.errore = 'Aggiungi almeno un esercizio.';
      return;
    }

    for (const esercizio of giorno.esercizi) {
      if (!this.validaEsercizio(esercizio)) {
        return;
      }
    }

    if (this.stepCorrente === this.numeroGiorni) {
      this.stepCorrente = this.numeroGiorni + 1;
    } else {
      this.stepCorrente++;
    }
  }

  validaEsercizio(esercizio: EsercizioForm): boolean {
    if (!esercizio.categoriaId || !esercizio.esercizioId) {
      this.errore = 'Completa categoria ed esercizio per ogni esercizio.';
      return false;
    }

    if (esercizio.usaSeriePersonalizzate) {
      if (!esercizio.seriePersonalizzate || esercizio.seriePersonalizzate < 1) {
        this.errore = 'Inserisci un numero di serie personalizzato valido.';
        return false;
      }

      esercizio.serie = esercizio.seriePersonalizzate;
    }

    if (!esercizio.serie || esercizio.serie < 1) {
      this.errore = 'Ogni esercizio deve avere almeno una serie.';
      return false;
    }

    if (esercizio.usaRipetizioniPersonalizzate) {
      if (!esercizio.ripetizioniPersonalizzate.trim()) {
        this.errore = 'Inserisci le ripetizioni personalizzate.';
        return false;
      }

      esercizio.ripetizioniTarget = esercizio.ripetizioniPersonalizzate;
    }

    if (!esercizio.ripetizioniTarget.trim()) {
      this.errore = 'Inserisci le ripetizioni target.';
      return false;
    }

    if (esercizio.usaRecuperoPersonalizzato) {
      if (
        esercizio.recuperoPersonalizzato === null ||
        esercizio.recuperoPersonalizzato < 0
      ) {
        this.errore = 'Inserisci un recupero personalizzato valido.';
        return false;
      }

      esercizio.recuperoSecondi = esercizio.recuperoPersonalizzato;
    }

    if (esercizio.recuperoSecondi < 0) {
      this.errore = 'Il recupero non può essere negativo.';
      return false;
    }

    return true;
  }

  tornaIndietro(): void {
    this.errore = '';

    if (this.stepCorrente > 0) {
      this.stepCorrente--;
    }
  }

  vaiAlGiorno(index: number): void {
    this.errore = '';
    this.stepCorrente = index + 1;
  }

  tornaAiDatiScheda(): void {
    this.errore = '';
    this.stepCorrente = 0;
  }

  salvaScheda(): void {
    this.errore = '';
    this.successo = '';

    for (const giorno of this.giorni) {
      if (!giorno.nome) {
        this.errore = 'Controlla tutti i giorni: manca un giorno della settimana.';
        return;
      }

      for (const esercizio of giorno.esercizi) {
        if (!this.validaEsercizio(esercizio)) {
          return;
        }
      }
    }

    const giorniRequest: GiornoSchedaRequest[] = this.giorni.map((giorno) => {
      const categorieIds = Array.from(
        new Set(
          giorno.esercizi
            .map(esercizio => esercizio.categoriaId)
            .filter((id): id is number => id !== null)
        )
      );

      const eserciziRequest: EsercizioSchedaRequest[] = giorno.esercizi.map((esercizio, index) => {
        return {
          esercizioId: esercizio.esercizioId!,
          ordine: index + 1,
          serie: esercizio.serie,
          ripetizioniTarget: esercizio.ripetizioniTarget,
          recuperoSecondi: esercizio.recuperoSecondi,
          note: esercizio.note
        };
      });

      return {
        nome: giorno.nome,
        ordine: giorno.ordine,
        note: giorno.note,
        categorieMuscolariIds: categorieIds,
        esercizi: eserciziRequest
      };
    });

    const request: CreaSchedaCompletaRequest = {
      nome: this.nome,
      obiettivo: this.obiettivoFinale,
      numeroGiorni: this.numeroGiorni,
      note: this.note,
      utenteId: this.utenteIdProvvisorio,
      giorni: giorniRequest
    };

    this.saving = true;

    this.schedaService.creaSchedaCompleta(request).subscribe({
      next: (response) => {
        this.saving = false;
        this.successo = response.messaggio;
        this.router.navigate(['/schede']);
      },
      error: (error) => {
        this.saving = false;
        console.error(error);
        this.errore = 'Errore nel salvataggio della scheda. Controlla backend, CORS o utente id 1.';
      }
    });
  }
}