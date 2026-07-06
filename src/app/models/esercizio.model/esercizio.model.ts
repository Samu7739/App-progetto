import { Component } from '@angular/core';
import { CategoriaMuscolare } from '../categoria-muscolare.model/categoria-muscolare.model';
export interface Esercizio {
  id: number;
  nome: string;
  descrizione: string | null;
  immagineUrl: string | null;
  videoUrl: string | null;
  categoriaMuscolare: CategoriaMuscolare;
}
@Component({
  selector: 'app-esercizio.model',
  imports: [],
  templateUrl: './esercizio.model.html',
  styleUrl: './esercizio.model.css',
})
export class EsercizioModel {}
