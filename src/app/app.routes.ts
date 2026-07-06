import { Routes } from '@angular/router';

import { Welcome } from './pages/welcome/welcome';
import { Login } from './pages/login/login';
import { Register } from './pages/register/register';
import { Home } from './pages/home/home';

import { Schede } from './pages/schede/schede';
import { CreaScheda } from './pages/crea-scheda/crea-scheda';
import { Tutorial } from './pages/tutorial/tutorial';
import { Progressi } from './pages/progressi/progressi';

export const routes: Routes = [
  { path: '', component: Welcome },
  { path: 'login', component: Login },
  { path: 'register', component: Register },
  { path: 'home', component: Home },

  { path: 'schede', component: Schede },
  { path: 'crea-scheda', component: CreaScheda },
  { path: 'tutorial', component: Tutorial },
  { path: 'progressi', component: Progressi },

  { path: '**', redirectTo: '' }
];